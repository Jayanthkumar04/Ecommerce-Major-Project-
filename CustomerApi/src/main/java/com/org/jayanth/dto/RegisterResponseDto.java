package com.org.jayanth.dto;

public class RegisterResponseDto {

	private String name;
	private String email;
	private boolean registration;
	private String role;
	private String message;
	public RegisterResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterResponseDto(String name, String email, boolean registration, String role, String message) {
		super();
		this.name = name;
		this.email = email;
		this.registration = registration;
		this.role = role;
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
	public boolean isRegistration() {
		return registration;
	}
	public void setRegistration(boolean registration) {
		this.registration = registration;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "RegisterResponseDto [name=" + name + ", email=" + email + ", registration=" + registration + ", role="
				+ role + ", message=" + message + "]";
	}
	
	
}
