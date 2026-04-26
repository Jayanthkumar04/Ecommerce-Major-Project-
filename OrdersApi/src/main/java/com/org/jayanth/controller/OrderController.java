package com.org.jayanth.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.jayanth.dto.CheckoutRequest;
import com.org.jayanth.dto.CheckoutResponse;
import com.org.jayanth.dto.FilterRequest;
import com.org.jayanth.dto.FilterResponse;
import com.org.jayanth.dto.OrderResponseDto;
import com.org.jayanth.entity.Order;
import com.org.jayanth.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200")

public class OrderController {

	@Autowired
    private OrderServiceImpl orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest request) throws Exception {


		logger.info("Received checkout request for ");
        CheckoutResponse response = orderService.createOrder(request);

        return ResponseEntity.ok(response);
    }
	
	
	@PostMapping("/verify-payment")
    public ResponseEntity<CheckoutResponse> verifyPayment(@RequestBody Map<String, String> payload) throws Exception {

		System.out.println("verify payment controller");
        CheckoutResponse response = orderService.verifyPayment(payload.get("razorpay_order_id"),payload.get("razorpay_payment_id"),payload.get("razorpay_signature"));

        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/retry/{orderId}")
	public ResponseEntity<CheckoutResponse> retryPayment(@PathVariable("orderId") Long orderId)
	{
		CheckoutResponse response = orderService.retryPayment(orderId);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@GetMapping("/delivery-today")
    public ResponseEntity<Page<OrderResponseDto>> getOrdersForDeliveryToday(@RequestParam int page,@RequestParam int size)
    {
		
		Page<OrderResponseDto> orders = orderService.getOrdersForDeliveryToday(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
	
	@GetMapping("/status/{status}")
	public ResponseEntity<Page<OrderResponseDto>> getOrdersByStatus(@PathVariable("status") String status,@RequestParam int page,@RequestParam int size)
	{
	    Page<OrderResponseDto> orders = orderService.getOrdersByStatus(status, page, size);

		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<List<OrderResponseDto>> getAllOrders()
	{
		
		List<OrderResponseDto> orders = orderService.getAllOrders();
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<FilterResponse>> getFilteredOrders(@RequestBody(required = false) FilterRequest filterReq)
	{
	
	  List<FilterResponse> order = orderService.getOrderDetailsOnSearch(filterReq);
	    
	  return ResponseEntity.status(HttpStatus.OK).body(order);
	  
	}
	
	
}
