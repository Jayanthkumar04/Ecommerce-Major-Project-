package com.jayanth.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CustomerApi")
public interface CustomerFeignClient {

	@GetMapping("/users/allUsers")
	public int noOfUsers();
	
}
