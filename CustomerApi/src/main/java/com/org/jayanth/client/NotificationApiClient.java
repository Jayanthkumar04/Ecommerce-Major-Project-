package com.org.jayanth.client;

import java.io.File;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("NotificationApi")
public interface NotificationApiClient {

	@PostMapping("/notification/sendNotification")
	void sendNotification(@RequestParam("toEmail") String toEmail,@RequestParam("subject") String  subject,@RequestParam("body") String body);
}
