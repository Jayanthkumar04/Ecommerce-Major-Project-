package com.org.jayanth.dto;

public class ResetPasswordResponse {

	private String message;

	
	public ResetPasswordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ResetPasswordResponse(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "ResetPasswordResponse [message=" + message + "]";
	}
	
	
}
