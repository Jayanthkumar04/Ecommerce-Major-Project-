package com.jayanth.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayanth.ecommerce.dto.AdminDashboardDto;
import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.entity.Order;
import com.jayanth.ecommerce.service.AdminDashboardService;

@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
@RestController
public class AdminDashboardController {

	
	@Autowired
	private AdminDashboardService service;
	
	@GetMapping
	public ResponseEntity<AdminDashboardDto> getAdminDashboardDetails()
	{
		AdminDashboardDto dto = service.getDashboardDetails();		
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	
	@GetMapping("/filter")
	public ResponseEntity<List<Order>> getFilteredOrders(@RequestBody(required = false) FilterRequest filterReq)
	{
	
	  List<Order> order = service.getOrderDetailsOnSearch(filterReq);
	  
	  return ResponseEntity.status(HttpStatus.OK).body(order);
	
	}
	
	
}
