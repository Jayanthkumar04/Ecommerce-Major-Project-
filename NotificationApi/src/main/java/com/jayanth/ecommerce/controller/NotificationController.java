package com.jayanth.ecommerce.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jayanth.ecommerce.dto.NotificationRequestDto;
import com.jayanth.ecommerce.service.EmailServiceImpl;
import com.jayanth.ecommerce.service.InvoiceService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping("/sendNotification")
	public void sendNotification(@RequestParam("toEmail") String toEmail,@RequestParam("subject") String  subject,@RequestParam("body") String body) throws Exception
	{
		emailService.sendOrderConfirmation(toEmail, subject, body);
	}
	
	
	@PostMapping("/generateInvoice")
	public String generateInvoice(@RequestBody NotificationRequestDto dto)
	{
		
		String invoiceUrl = invoiceService.invokeGenerateInvoice(dto);
		
		return invoiceUrl;
		
	}
	
	
}
