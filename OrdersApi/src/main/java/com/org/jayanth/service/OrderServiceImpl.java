package com.org.jayanth.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.jayanth.client.CustomerApiClient;
import com.org.jayanth.client.NotificationApiClient;
import com.org.jayanth.dto.CheckoutRequest;
import com.org.jayanth.dto.CheckoutResponse;
import com.org.jayanth.dto.FilterRequest;
import com.org.jayanth.dto.FilterResponse;
import com.org.jayanth.dto.OrderItemDto;
import com.org.jayanth.dto.OrderResponseDto;
import com.org.jayanth.entity.Order;
import com.org.jayanth.entity.OrderItems;
import com.org.jayanth.entity.ShippingAddress;
import com.org.jayanth.repo.OrderItemsRepo;
import com.org.jayanth.repo.OrderRepo;
import com.org.jayanth.repo.ShippingAddressRepo;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderItemsRepo orderItemsRepo;
	
	
	@Autowired
	private ShippingAddressRepo addressRepo;
	
	
	
	@Value("${razorpay.key.id}")
	private String razorpayKey;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	private RazorpayClient client;
	
	
	@Autowired
	private CustomerApiClient customerClient;
	
	
	@Autowired
	private NotificationApiClient notificationClient;
	
	@Transactional
	public CheckoutResponse createOrder(CheckoutRequest request) throws Exception{
		
		
		boolean userExists = customerClient.userExists(request.getUser().getEmail());
		
		if(!userExists)
		{
			throw new RuntimeException("can't create order because user doesnot exist");
		}
		
		System.out.println("create order "+request);
      
        ShippingAddress shippingAddress = request.getShippingAddress();
        
        addressRepo.save(shippingAddress);
        
        
        Order order = new Order();
        System.out.println("email in create order"+request.getUser().getEmail());
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




String fileUrl = notificationClient.generateInvoice(mapToDto(order));

order.setInvoiceUrl(fileUrl);

order.setInvoiceStatus("GENERATED");
orderRepo.save(order);
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



	public Page<OrderResponseDto> getOrdersForDeliveryToday(int page, int size) {
		
		LocalDate today = LocalDate.now();
		
		LocalDateTime start = today.atStartOfDay();//12 am
		
		LocalDateTime end = today.atTime(23,59,59);
		
		Page<Order> orderPage = orderRepo.findOrdersForDeliveryToday(
                start,
                end,
                "PAID",
                PageRequest.of(page, size)
        );
		
        return orderPage.map(this::mapToDto);
	}



	public Page<OrderResponseDto> getOrdersByStatus(String status, int page, int size) {

        Page<Order> orderPage = orderRepo.findByOrderStatus(
                status,
                PageRequest.of(page, size)
        );
    
        
        return orderPage.map(this::mapToDto);
	
	}
	
	
	public List<OrderResponseDto> getAllOrders() {
		
		List<Order> orders = orderRepo.findAll();
		
		List<OrderResponseDto> allOrders = new ArrayList<>();
		
		for(Order o:orders)
		{
			OrderResponseDto temp = mapToDto(o);
			
			allOrders.add(temp);
		}
		
		return allOrders;
		
	}
	
	// ✅ 3. Mapping Logic (VERY IMPORTANT)
    private OrderResponseDto mapToDto(Order order) {

        OrderResponseDto dto = new OrderResponseDto();

        dto.setOrderId(order.getOrderId());
        dto.setOrderTrackingNum(order.getOrderTrackingNum());
        dto.setEmail(order.getEmail());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalQuantity(order.getTotalQuantity());
        dto.setDeliveryDate(order.getDeliveryDate());

        // Map items
        List<OrderItemDto> items = order.getOrderItems()
                .stream()
                .map(this::mapItemToDto)
                .collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }
    
    private OrderItemDto mapItemToDto(OrderItems item) {

        OrderItemDto dto = new OrderItemDto();

        dto.setImageUrl(item.getImageUrl());
        dto.setProductId(item.getProductId());
        dto.setName(item.getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        

        return dto;
    }


	
	public List<FilterResponse> getOrderDetailsOnSearch(FilterRequest req)
	{
		System.out.println(req);
		
		List<FilterResponse> response = new ArrayList<>();
		
		List<Order> orders = orderRepo.findAll(OrderSpecification.filterOrders(req));
		
		
		if(req == null) {
			
			for(Order o:orders)
			{
				List<OrderItemDto> dto = new ArrayList<>();
				for(OrderItems oi:o.getOrderItems())
				{
					dto.add(new OrderItemDto(oi.getProductId(), oi.getQuantity(), oi.getUnitPrice(), oi.getImageUrl(), oi.getName()));
				}
				
				response.add(new FilterResponse(o.getOrderId(),o.getEmail(),o.getDateCreated(), o.getOrderStatus(), o.getOrderTrackingNum(), o.getRazorpayPaymentId(), o.getDeliveryDate(),o.getInvoiceUrl(),dto));
			}
			
			return response;
			
		}
		
		


		
		for(Order o:orders)
		{	
			
			List<OrderItemDto> dto = new ArrayList<>();
			for(OrderItems oi:o.getOrderItems())
			{
				dto.add(new OrderItemDto(oi.getProductId(), oi.getQuantity(), oi.getUnitPrice(), oi.getImageUrl(), oi.getName()));
			}
			
			
			response.add(new FilterResponse(o.getOrderId(),o.getEmail(),o.getDateCreated(), o.getOrderStatus(), o.getOrderTrackingNum(), o.getRazorpayPaymentId(), o.getDeliveryDate(),o.getInvoiceUrl(),dto));
		}
		
		
		return response;
		
	}



	

}
