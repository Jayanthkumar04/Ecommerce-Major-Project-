package com.org.jayanth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CustomerApi")
public interface CustomerApiClient {

	
	@GetMapping("/users/userExists")
	public boolean userExists(@RequestParam String email);
	
}
