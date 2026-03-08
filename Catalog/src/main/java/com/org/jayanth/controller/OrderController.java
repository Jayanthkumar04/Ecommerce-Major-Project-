package com.org.jayanth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.jayanth.dto.CheckoutRequest;
import com.org.jayanth.dto.CheckoutResponse;
import com.org.jayanth.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	@Autowired
    private OrderServiceImpl orderService;
	
	@PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest request) throws Exception {

        CheckoutResponse response = orderService.createOrder(request);

        return ResponseEntity.ok(response);
    }
	
	
	@PostMapping("/verify-payment")
    public ResponseEntity<CheckoutResponse> verifyPayment(@RequestBody Map<String, String> payload) throws Exception {

        CheckoutResponse response = orderService.verifyPayment(payload.get("razorpay_order_id"),payload.get("razorpay_payment_id"),payload.get("razorpay_signature"));

        return ResponseEntity.ok(response);
    }
	
}
