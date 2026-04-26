package com.jayanth.ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayanth.ecommerce.dto.AdminDashboardDto;
import com.jayanth.ecommerce.dto.FilterRequest;
import com.jayanth.ecommerce.dto.FilterResponse;
import com.jayanth.ecommerce.service.AdminDashboardService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/admin")
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
	
	
	@PostMapping("/filter")
	public ResponseEntity<List<FilterResponse>> getFilterResponse(@RequestBody(required = false) FilterRequest request)
	{
		List<FilterResponse> response = service.filteredOrders(request);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

	
	
}
