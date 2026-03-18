package com.org.jayanth.dto;

public class ForgotPasswordResponse {

	
	private String url;
	
	private String tempPassword;

	public ForgotPasswordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPasswordResponse(String url, String tempPassword) {
		super();
		this.url = url;
		this.tempPassword = tempPassword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	@Override
	public String toString() {
		return "ForgotPasswordResponse [url=" + url + ", tempPassword=" + tempPassword + "]";
	}
	
	
}
