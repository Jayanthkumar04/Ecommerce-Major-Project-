package com.jayanth.ecommerce.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jayanth.ecommerce.client.OrderFeignClient;
import com.jayanth.ecommerce.dto.NotificationRequestDto;
import com.jayanth.ecommerce.dto.PageResponseDto;

@Service
public class OrderScheduler {

	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private OrderFeignClient orderFeignClient;
	
	@Scheduled(cron = "0 09 19 * * ?",zone = "Asia/Kolkata")
	public void sendDeliveryReminder()
	{
		System.out.println("Running Delivery Reminder........");
		
		LocalDate today = LocalDate.now();
		
		LocalDateTime start = today.atStartOfDay(); // 00:00
		
        LocalDateTime end = today.atTime(23, 59, 59); // 23:59
      
        int page = 0,size=50;
        
        PageResponseDto<NotificationRequestDto> response;
        
        
        
        do {
         response = orderFeignClient.getOrdersForDeliveryToday(page, size);
        
        for (NotificationRequestDto order : response.getContent()) {

            try {
                String subject = "Out for Delivery 🚚";

                String body = "Hi,\n\nYour order is out for delivery today.\n"
                        + "Please be available to receive it before 9 PM.\n\n"
                        + "Tracking Number: " + order.getOrderTrackingNum();

                emailServiceImpl.sendOrderConfirmation(
                        order.getEmail(),
                        subject,
                        body
                        
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        page++;
        }while(!response.isLast());

	}
	
	
	@Scheduled(cron = "0 10 19 * * ?", zone = "Asia/Kolkata") // 10 AM daily
	public void sendPaymentReminder() {

	    System.out.println("Running payment reminder...");
	    
	    int page =0;
	    
	    int size =50;
	    
	    
	    PageResponseDto<NotificationRequestDto> response;
	    
	    do {

	    response = orderFeignClient.getOrdersByStatus("created",page,size);

	    for (NotificationRequestDto order : response.getContent()) {

	        try {
	            String subject = "Payment Reminder 💳";

	            String body = "Hi,\n\nYour payment is still pending.\n"
	                    + "Please complete your payment to confirm your order.\n\n"
	                    + "Order ID: " + order.getOrderId() + "\n"
	                    + "Tracking Number: " + order.getOrderTrackingNum();

	            emailServiceImpl.sendOrderConfirmation(
	                    order.getEmail(),
	                    subject,
	                    body
	            );

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    page++;
	}while(!response.isLast());
	    
	}
}
