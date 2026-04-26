package com.jayanth.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayanth.ecommerce.dto.NotificationRequestDto;
import com.jayanth.ecommerce.dto.PageResponseDto;

@FeignClient(name="ORDERSAPI")
public interface OrderFeignClient {

	
	@GetMapping("/api/orders/delivery-today")
    PageResponseDto<NotificationRequestDto> getOrdersForDeliveryToday(
            @RequestParam int page,
            @RequestParam int size
    );
	
	
	@GetMapping("/api/orders/status/{status}")
    PageResponseDto<NotificationRequestDto> getOrdersByStatus(
            @PathVariable String status,
            @RequestParam int page,
            @RequestParam int size
    );
}
