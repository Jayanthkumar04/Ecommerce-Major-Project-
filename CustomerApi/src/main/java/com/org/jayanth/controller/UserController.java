package com.org.jayanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.jayanth.dto.FilterRequest;
import com.org.jayanth.dto.FilterResponse;
import com.org.jayanth.dto.ForgotPasswordDto;
import com.org.jayanth.dto.ForgotPasswordResponse;
import com.org.jayanth.dto.LoginDto;
import com.org.jayanth.dto.LoginSuccessDto;
import com.org.jayanth.dto.RegisterDto;
import com.org.jayanth.dto.RegisterResponseDto;
import com.org.jayanth.dto.ResetPasswordDto;
import com.org.jayanth.dto.ResetPasswordResponse;
import com.org.jayanth.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")

public class UserController {

	@Autowired
	private UserServiceImpl userService;
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody RegisterDto dto)
	{
		RegisterResponseDto response = userService.registerUser(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginSuccessDto> loginUser(@RequestBody LoginDto dto)
	{
		LoginSuccessDto ans = userService.loginUser(dto);
		
		System.out.println("ans "+ans);
		return ResponseEntity.status(HttpStatus.OK).body(ans);
		
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordDto dto)
	{
		ForgotPasswordResponse response = userService.forgotPassword(dto.getEmail());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordDto dto)
	{
		ResetPasswordResponse response = userService.resetPassword(dto.getEmail(), dto.getOldPassword(), dto.getNewPassword());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping("/userExists")
	public boolean userExists(@RequestParam("email") String email)
	{
		
		return userService.userExists(email);
		
	}
    
	
	@GetMapping("/allUsers")
	public int noOfUsers()
	{
		return userService.noOfUsers();
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<FilterResponse>> getFilterResponse(@RequestBody(required = false) FilterRequest request)
	{
		List<FilterResponse> response = userService.getFilteredOrders(request);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	

}
