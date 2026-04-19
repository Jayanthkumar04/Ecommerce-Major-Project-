package com.org.jayanth.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.org.jayanth.entity.Order;
import com.org.jayanth.repo.OrderRepo;

@Service
public class OrderScheduler {

	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Scheduled(cron = "0 53 12 * * ?",zone = "Asia/Kolkata")
	public void sendDeliveryReminder()
	{
		System.out.println("Running Delivery Reminder........");
		
		LocalDate today = LocalDate.now();
		
		LocalDateTime start = today.atStartOfDay(); // 00:00
		
        LocalDateTime end = today.atTime(23, 59, 59); // 23:59
      
        int page = 0,size=50;
        
        Page<Order> orderPage;
        
        do {
         orderPage = orderRepo.findOrdersForDeliveryToday(start, end, "PAID",PageRequest.of(page, size));
        
        for (Order order : orderPage.getContent()) {

            try {
                String subject = "Out for Delivery 🚚";

                String body = "Hi,\n\nYour order is out for delivery today.\n"
                        + "Please be available to receive it before 9 PM.\n\n"
                        + "Tracking Number: " + order.getOrderTrackingNum();

                emailServiceImpl.sendOrderConfirmation(
                        order.getEmail(),
                        subject,
                        body,
                        null
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }while(orderPage.hasNext());

	}
	
	
	@Scheduled(cron = "0 42 12 * * ?", zone = "Asia/Kolkata") // 10 AM daily
	public void sendPaymentReminder() {

	    System.out.println("Running payment reminder...");
	    
	    int page =0;
	    
	    int size =50;
	    
	    
	    Page<Order> orderPage;
	    
	    do {

	    orderPage = orderRepo.findByOrderStatus("created",PageRequest.of(page,size));

	    for (Order order : orderPage.getContent()) {

	        try {
	            String subject = "Payment Reminder 💳";

	            String body = "Hi,\n\nYour payment is still pending.\n"
	                    + "Please complete your payment to confirm your order.\n\n"
	                    + "Order ID: " + order.getOrderId() + "\n"
	                    + "Tracking Number: " + order.getOrderTrackingNum();

	            emailServiceImpl.sendOrderConfirmation(
	                    order.getEmail(),
	                    subject,
	                    body,
	                    null
	            );

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    page++;
	}while(orderPage.hasNext());
	    
	}
}
