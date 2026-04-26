package com.jayanth.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayanth.ecommerce.client.CustomerFeignClient;
import com.jayanth.ecommerce.client.OrderFeignClient;
import com.jayanth.ecommerce.dto.AdminDashboardDto;
import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.dto.FilterResponse;
import com.jayanth.ecommerce.dto.OrderResponseDto;
import com.jayanth.ecommerce.entity.AdminDashboard;
import com.jayanth.ecommerce.entity.Order;
import com.jayanth.ecommerce.repo.AdminDashboardRepo;
import com.jayanth.ecommerce.repo.OrderRepo;

@Service
public class AdminDashboardService {

	
	
	@Autowired
	private OrderFeignClient orderClient;
	
	@Autowired
	private CustomerFeignClient customerClient;
	
	
	
	
	public AdminDashboardDto getDashboardDetails()
	{
	
		List<OrderResponseDto> allOrders = orderClient.getAllOrders();
		
		
		Integer totalOrders = allOrders.size();
		
		Integer totalCustomers = customerClient.noOfUsers();
		
		Double totalAmountCollected = allOrders.stream().mapToDouble(o->o.getTotalPrice()).sum();
		
		Long totalProducts = (long) allOrders.stream().mapToLong(o->o.getTotalQuantity()).sum();
		
		AdminDashboardDto dto = new AdminDashboardDto(totalCustomers, totalOrders, totalAmountCollected, totalProducts);
		return dto;

	}
	
	public List<FilterResponse> filteredOrders(FilterRequest req)
	{
		
		List<FilterResponse> response = orderClient.getFilteredOrders(req);
		
		return response;
		
	}
	
	

	

	
}
