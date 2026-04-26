package com.jayanth.ecommerce.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;
	
	@Async("emailExecutor")
	public void sendOrderConfirmation(String toEmail,String subject,String body) throws Exception
	{
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);


        mailSender.send(message);
		
	}
}
