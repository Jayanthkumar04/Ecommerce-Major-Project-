package com.org.jayanth.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.jayanth.dto.CheckoutRequest;
import com.org.jayanth.dto.CheckoutResponse;
import com.org.jayanth.dto.OrderItemDto;
import com.org.jayanth.entity.Order;
import com.org.jayanth.entity.OrderItems;
import com.org.jayanth.entity.ShippingAddress;
import com.org.jayanth.repo.OrderItemsRepo;
import com.org.jayanth.repo.OrderRepo;
import com.org.jayanth.repo.ShippingAddressRepo;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderItemsRepo orderItemsRepo;
	
	
	@Autowired
	private ShippingAddressRepo addressRepo;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Value("${razorpay.key.id}")
	private String razorpayKey;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	private RazorpayClient client;
	
	
	@Transactional
	public CheckoutResponse createOrder(CheckoutRequest request) throws Exception{
		
		System.out.println("create order "+request);
      
        ShippingAddress shippingAddress = request.getShippingAddress();
        
        addressRepo.save(shippingAddress);
        
        
        Order order = new Order();
        
        order.setEmail(request.getUser().getEmail());
        
        order.setShippingAddress(shippingAddress);
        
        order.setTotalPrice(request.getTotalPrice());
        
        order.setTotalQuantity(request.getTotalQuantity());
        
        order.setOrderStatus("CREATED");
        
        order.setDateCreated(LocalDateTime.now());
        
        JSONObject orderRequest = new JSONObject();
         
         orderRequest.put("amount",request.getTotalPrice() * 100);
         
         orderRequest.put("currency", "INR");
         
         orderRequest.put("receipt", request.getUser().getEmail());
         
         this.client = new RazorpayClient(razorpayKey,razorpaySecret);
         
         com.razorpay.Order razorpayOrder = client.orders.create(orderRequest);
         
         order.setRazorpayPaymentId(razorpayOrder.get("id"));
         
         order.setRazorpayOrderId(razorpayOrder.get("id"));
         
         order.setOrderStatus(razorpayOrder.get("status"));
         

         String orderTrackingNum = UUID.randomUUID().toString();
         order.setOrderTrackingNum(orderTrackingNum);
         
         Order savedOrder = orderRepo.save(order);
         
         for (OrderItemDto itemDto : request.getOrderItems()) {
        	 OrderItems item = new OrderItems();
        	    item.setProductId(itemDto.getProductId());
        	    item.setQuantity(itemDto.getQuantity());
        	    item.setUnitPrice(itemDto.getUnitPrice());
        	    item.setImageUrl(itemDto.getImageUrl());
        	    item.setName(itemDto.getName());
        	    item.setOrder(savedOrder);
                
        	    orderItemsRepo.save(item);
         }
         
         CheckoutResponse response = new CheckoutResponse();
         response.setOrderId(savedOrder.getOrderId());
         response.setRazorpayId(savedOrder.getRazorpayPaymentId());
         response.setAmount(savedOrder.getTotalPrice());
         response.setOrderTrackingNum(orderTrackingNum);

         return response;
	     
	
	
	}
	


	public CheckoutResponse verifyPayment(String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature) throws RazorpayException,Exception {

boolean isValid = com.razorpay.Utils.verifyPaymentSignature(
new JSONObject()
.put("razorpay_order_id", razorpayOrderId)   // ✅ MUST
.put("razorpay_payment_id", razorpayPaymentId)
.put("razorpay_signature", razorpaySignature),
razorpaySecret
);

if (!isValid) {
throw new RuntimeException("Invalid payment signature");
}

RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

Payment payment = razorpayClient.payments.fetch(razorpayPaymentId);

String status = payment.get("status");

if (!"captured".equalsIgnoreCase(status)) {
throw new RuntimeException("Payment not successful");
}

// ✅ IMPORTANT: find using razorpay_order_id
Order order = orderRepo.findByRazorpayOrderId(razorpayOrderId);

if (order == null) {
throw new RuntimeException("Order not found");
}

order.setOrderStatus("PAID");
order.setRazorpayPaymentId(razorpayPaymentId);
order.setDeliveryDate(LocalDateTime.now().plusDays(2));

orderRepo.save(order);

try {
    File invoice = invoiceService.generateInvoice(order);

    String subject = "Order Confirmed - " + order.getOrderTrackingNum();

    String body = "Hi,\n\nYour order is successfully placed.\n\n"
            + "Order ID: " + order.getOrderId() + "\n"
            + "Tracking Number: " + order.getOrderTrackingNum() + "\n"
            + "Amount: ₹" + order.getTotalPrice() + "\n\n"
            + "Thank you for shopping with us!";

    emailServiceImpl.sendOrderConfirmation(
        order.getEmail(),
        subject,
        body,
        invoice
    );

} catch (Exception e) {
    e.printStackTrace();
}
return new CheckoutResponse(
order.getOrderId(),
order.getRazorpayPaymentId(),
order.getOrderTrackingNum(),
order.getTotalPrice()
);




}

	public CheckoutResponse retryPayment(Long orderId)
	{
	    Order order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));
		
		
		
		if (!"created".equals(order.getOrderStatus())) {
	        throw new RuntimeException("Payment already completed or invalid state");
	    }
		
		CheckoutResponse response = new CheckoutResponse();
	    response.setOrderId(order.getOrderId());
	    response.setRazorpayId(order.getRazorpayOrderId()); // 🔥 reuse same
	    response.setAmount(order.getTotalPrice());
	    response.setOrderTrackingNum(order.getOrderTrackingNum());
	    
	    return response;
	    
		
		
		
		
	}

}
