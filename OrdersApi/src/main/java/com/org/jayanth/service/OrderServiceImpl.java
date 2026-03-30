package com.org.jayanth.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${razorpay.key.id}")
	private String razorpayKey;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	private RazorpayClient client;
	
	
	@Transactional
	public CheckoutResponse createOrder(CheckoutRequest request) throws Exception{
		
		
      
        ShippingAddress shippingAddress = request.getShippingAddress();
        
        addressRepo.save(shippingAddress);
        
        
        Order order = new Order();
        
        order.setEmail(request.getEmail());
        
        order.setShippingAddress(shippingAddress);
        
        order.setTotalPrice(request.getTotalPrice());
        
        order.setTotalQuantity(request.getTotalQuantity());
        
        order.setOrderStatus("CREATED");
        
        order.setDateCreated(LocalDateTime.now());
        
        JSONObject orderRequest = new JSONObject();
         
         orderRequest.put("amount",request.getTotalPrice() * 100);
         
         orderRequest.put("currency", "INR");
         
         orderRequest.put("receipt", request.getEmail());
         
         this.client = new RazorpayClient(razorpayKey,razorpaySecret);
         
         com.razorpay.Order razorpayOrder = client.orders.create(orderRequest);
         
         order.setRazorPaymentId(razorpayOrder.get("id"));
         
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
        	    item.setOrder(savedOrder);

        	    orderItemsRepo.save(item);
         }
         
         CheckoutResponse response = new CheckoutResponse();
         response.setOrderId(savedOrder.getOrderId());
         response.setRazorpayId(savedOrder.getRazorPaymentId());
         response.setAmount(savedOrder.getTotalPrice());
         response.setOrderTrackingNum(orderTrackingNum);

         return response;
	     
	
	
	}
	


public CheckoutResponse verifyPayment(String razorpayOrderId,String razorpayPaymentId,String razorpaySignature) throws RazorpayException{
		
		boolean generatedSignature = 
		        com.razorpay.Utils.verifyPaymentSignature(
		        		new JSONObject()
		                .put("razorpay_order_id", razorpayOrderId)
		                .put("razorpay_payment_id", razorpayPaymentId)
		                .put("razorpay_signature", razorpaySignature),
		            razorpaySecret
		        		);
		
		if(!generatedSignature) {
		
			throw new RuntimeException("invalid payment signature");
		}
		
		RazorpayClient razorpayClient = new RazorpayClient(razorpayKey,razorpaySecret);
		
		Payment payment = razorpayClient.payments.fetch(razorpayPaymentId);
		
        String status = payment.get("status");
		
		if (!"captured".equalsIgnoreCase(status)) {
            throw new RuntimeException("Payment not successful");
        }
		
		
		Order order = orderRepo.findByRazorOrderId(razorpayOrderId);

		if (order == null) {
            throw new RuntimeException("Order not found");
        }
		
	   LocalDateTime deliveryDate = LocalDateTime.now().plusDays(2);
	   order.setDeliveryDate(deliveryDate);
		order.setOrderStatus("PAID");
		order.setRazorPaymentId(razorpayPaymentId);

		orderRepo.save(order);
	    
	    CheckoutResponse response = new CheckoutResponse(order.getOrderId(),order.getRazorPaymentId(),order.getOrderTrackingNum(),order.getTotalPrice());
	    
	    return response;
	}
}
