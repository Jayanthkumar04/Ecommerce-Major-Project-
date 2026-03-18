package com.jayanth.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayanth.ecommerce.dto.AdminDashboardDto;
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
	
	
}
