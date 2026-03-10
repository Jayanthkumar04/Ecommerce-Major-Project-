package com.org.jayanth.dto;

public class LoginSuccessDto {

	private String name;
	
	private String email;
	
	private boolean firstLogin;
	
	private String message;

	public LoginSuccessDto() {
		super();
		// TODO Auto-generated constructor stub
	}

    

	public LoginSuccessDto(String name, String email, boolean firstLogin, String message) {
		super();
		this.name = name;
		this.email = email;
		this.firstLogin = firstLogin;
		this.message = message;
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

	@Override
	public String toString() {
		return "LoginSuccessDto [name=" + name + ", email=" + email + ", firstLogin=" + firstLogin + ", message="
				+ message + "]";
	}
	
	
	
}
