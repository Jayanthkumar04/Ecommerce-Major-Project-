package com.jayanth.ecommerce.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.dto.FilterResponse;
import com.jayanth.ecommerce.dto.OrderResponseDto;


@FeignClient(name="ORDERSAPI")
public interface OrderFeignClient {

        
	@GetMapping("/api/orders/allOrders")
	public List<OrderResponseDto> getAllOrders();
	
	
	@PostMapping("/api/orders/filter")
	public List<FilterResponse> getFilteredOrders(@RequestBody(required = false) FilterRequest filterReq);
	
	
	
}
