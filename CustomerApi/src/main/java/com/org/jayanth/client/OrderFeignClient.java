package com.org.jayanth.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.jayanth.dto.FilterRequest;
import com.org.jayanth.dto.FilterResponse;



@FeignClient(name="ORDERSAPI")
public interface OrderFeignClient {

	
	@PostMapping("/api/orders/filter")
	public List<FilterResponse> getFilteredOrders(@RequestBody(required = false) FilterRequest filterReq);
	
	
	
}
