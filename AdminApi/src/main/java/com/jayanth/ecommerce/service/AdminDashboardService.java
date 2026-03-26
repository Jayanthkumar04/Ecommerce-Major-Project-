package com.jayanth.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayanth.ecommerce.dto.AdminDashboardDto;
import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.entity.AdminDashboard;
import com.jayanth.ecommerce.entity.Order;
import com.jayanth.ecommerce.repo.AdminDashboardRepo;
import com.jayanth.ecommerce.repo.OrderRepo;

@Service
public class AdminDashboardService {

	
	@Autowired
	private OrderRepo orderRepo;
	
	public AdminDashboardDto getDashboardDetails()
	{
	
		List<Order> allOrders = orderRepo.findAll();
		
		Integer totalOrders = allOrders.size();
		
		Integer totalCustomers = 10;
		
		Double totalAmountCollected = allOrders.stream().mapToDouble(Order::getTotalPrice).sum();
		
		Integer totalProducts = allOrders.stream().mapToInt(Order::getTotalQuantity).sum();
		
		AdminDashboardDto dto = new AdminDashboardDto(totalCustomers, totalOrders, totalAmountCollected, totalProducts);
		return dto;

	}
	
	
	public List<Order> getOrderDetailsOnSearch(FilterRequest req)
	{
		System.out.println(req);
		if(req == null) {
			return orderRepo.findAll();
		}
		
		
		return orderRepo.findAll(OrderSpecification.filterOrders(req));
	}
	
}
