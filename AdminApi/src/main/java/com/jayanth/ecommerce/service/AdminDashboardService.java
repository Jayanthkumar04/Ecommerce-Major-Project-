package com.jayanth.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayanth.ecommerce.dto.AdminDashboardDto;
import com.jayanth.ecommerce.entity.AdminDashboard;
import com.jayanth.ecommerce.repo.AdminDashboardRepo;

@Service
public class AdminDashboardService {

	@Autowired
	private AdminDashboardRepo repo;
	
	public AdminDashboardDto getDashboardDetails()
	{
	    AdminDashboard details = repo.findById(1L).orElseThrow();
		AdminDashboardDto dto = new AdminDashboardDto(details.getTotalCustomers(), details.getTotalOrders(), details.getTotalAmountCollected(), details.getTotalProducts());
		
		return dto;
		
		
	}
}
