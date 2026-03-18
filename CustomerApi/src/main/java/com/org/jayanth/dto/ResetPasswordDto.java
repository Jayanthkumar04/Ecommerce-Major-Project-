package com.org.jayanth.dto;

public class ResetPasswordDto {

	
	private String email;
	private String oldPassword;
	
	private String newPassword;

	public ResetPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResetPasswordDto(String oldPassword, String newPassword,String email) {
		super();
		this.email	 	 = email;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString() {
		return "ResetPasswordDto [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ",email = "+email+"]";
	}
	
	
	
}
