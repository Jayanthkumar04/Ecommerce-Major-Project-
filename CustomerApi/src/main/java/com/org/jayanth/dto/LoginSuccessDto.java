package com.org.jayanth.dto;

public class LoginSuccessDto {

	private String name;
	
	private String email;
	
	private boolean firstLogin;
	
	private String message;
	
	private String role;

	public LoginSuccessDto() {
		super();
		// TODO Auto-generated constructor stub
	}

    

	public LoginSuccessDto(String name, String email, boolean firstLogin, String message,String role) {
		super();
		this.name = name;
		this.email = email;
		this.firstLogin = firstLogin;
		this.message = message;
		this.role   = role;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public boolean isFirstLogin() {
		return firstLogin;
	}



	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "LoginSuccessDto [name=" + name + ", email=" + email + ", firstLogin=" + firstLogin + ", message="
				+ message + ", role=" + role + "]";
	}
	
	

	
	
	
	
}
