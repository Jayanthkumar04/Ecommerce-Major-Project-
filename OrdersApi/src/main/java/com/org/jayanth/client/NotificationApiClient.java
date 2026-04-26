package com.org.jayanth.client;

import java.io.File;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.jayanth.dto.OrderResponseDto;
import com.org.jayanth.entity.Order;

@FeignClient("NotificationApi")
public interface NotificationApiClient {

	@PostMapping("/notification/sendNotification")
	void sendNotification(@RequestParam("toEmail") String toEmail,@RequestParam("subject") String  subject,@RequestParam("body") String body);
	
	
	@PostMapping("/notification/generateInvoice")
	public String generateInvoice(@RequestBody OrderResponseDto dto);
	
}
