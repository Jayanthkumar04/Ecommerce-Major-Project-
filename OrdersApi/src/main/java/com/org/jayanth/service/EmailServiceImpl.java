package com.org.jayanth.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendOrderConfirmation(String toEmail,String subject,String body,File attachment) throws Exception
	{
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);

        if (attachment != null) {
            helper.addAttachment(attachment.getName(), attachment);
        }

        mailSender.send(message);
		
	}
}
