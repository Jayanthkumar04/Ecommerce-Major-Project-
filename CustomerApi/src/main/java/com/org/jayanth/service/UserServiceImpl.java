package com.org.jayanth.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.jayanth.client.NotificationApiClient;
import com.org.jayanth.client.OrderFeignClient;
import com.org.jayanth.dto.FilterRequest;
import com.org.jayanth.dto.FilterResponse;
import com.org.jayanth.dto.ForgotPasswordResponse;
import com.org.jayanth.dto.LoginDto;
import com.org.jayanth.dto.LoginSuccessDto;
import com.org.jayanth.dto.RegisterDto;
import com.org.jayanth.dto.RegisterResponseDto;
import com.org.jayanth.dto.ResetPasswordResponse;
import com.org.jayanth.entity.Users;
import com.org.jayanth.exception.UserNotFoundException;
import com.org.jayanth.repo.UserRepo;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NotificationApiClient notificationClient;
	
	
	@Autowired
	private OrderFeignClient orderClient;
	
	
	public RegisterResponseDto registerUser(RegisterDto dto)
	{
		
		Users user = new Users();
		
		Users findUser = findByEmail(dto.getEmail());
		
		if(findUser != null) throw new RuntimeException("user already exist");
		String tempPassword = passwordGenerator();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(tempPassword);
		user.setRole("ROLE_USER");
		user.setFirstTimeLogin(true);		
		user.setResetPassword(false);

		userRepo.save(user);
		notificationClient.sendNotification(dto.getEmail(),"REGISTRATION IS SUCCESSFULL ", "GOOD DAY "+dto.getName()+"\n Your password is:"+user.getPassword()+" please reset it");
		return new RegisterResponseDto(dto.getName(), dto.getEmail(),true, user.getRole(), "USER REGISTERED SUCCESSFULLY PLEASE RESET THE PASSWORD MAIL HAS BEEN SENT");
		
	}
	
	public LoginSuccessDto loginUser(LoginDto dto)
	{
		Users user = userRepo.findByEmail(dto.getEmail());
		
	   if(user == null) throw new UserNotFoundException("user not found");
		
		if(user.getPassword().equals(dto.getPassword()))
		{
			LoginSuccessDto ans = new LoginSuccessDto();
			
			ans.setEmail(dto.getEmail());
			ans.setName(user.getName());
			if(user.isFirstTimeLogin() == true) {
			ans.setFirstLogin(true);
			ans.setMessage("password is not yet reset please reset it");
			ans.setRole(user.getRole());
						}
			else {
				ans.setFirstLogin(false);
				ans.setMessage("Login is successfull happy shopping");
				ans.setRole(user.getRole());
			}
			
			 System.out.println(user);
				
			return ans;
		}
		else {
			throw new UserNotFoundException("user not found with email =>"+dto.getEmail());
		}
		
		
		
	}
	
	public ForgotPasswordResponse forgotPassword(String email)
	{
		
		String resetPassword = passwordGenerator();
		String link = "http://localhost:4200/change-password/"+email;
		
		
		Users user = findByEmail(email);
		
		user.setResetPassword(true);
		
		user.setPassword(resetPassword);
		
		notificationClient.sendNotification(email, "REQUEST FOR RESET PASSWORD : ", "Please use the below password to reset Password: \n"+resetPassword+"\n The link to reset your password is :"+link);
		
	    userRepo.save(user);
	    
	    return new ForgotPasswordResponse(link, resetPassword);
	    
	}
	
	public ResetPasswordResponse resetPassword(String email,String oldPassword,String newPassword)
	{
		
		Users user = findByEmail(email);
		
		if(user == null) throw new UserNotFoundException("user not found");
		
		user.setResetPassword(true);
		
		if(!user.getPassword().equals(oldPassword)) throw new UserNotFoundException("password is incorrect");
		
		user.setPassword(newPassword);

		user.setResetPassword(false);
		
		user.setFirstTimeLogin(false);
		userRepo.save(user);
		
		return new ResetPasswordResponse("password reset is done successfully");
		
	}
	
	
	public Users findByEmail(String email)
	{
		return userRepo.findByEmail(email);
	}
	public String passwordGenerator()
	{
		Random rand = new Random();
		
		return "TMP"+(1000+rand.nextInt(90000));
	}

	public boolean userExists(String email) {
		
		
		return findByEmail(email) != null;
	}

	public int noOfUsers() {
		
		List<Users> user = userRepo.findAll();
		
		return user.size();
	}	
	
	
	public List<FilterResponse> getFilteredOrders(FilterRequest request)
	{
		
		return orderClient.getFilteredOrders(request);
		
	}
	
	
	
	
	
	
}
