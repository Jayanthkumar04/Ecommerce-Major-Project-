package com.org.jayanth.entity;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
=======
>>>>>>> c1ceaf78c74f07acefc55d52dac55afe4c71b5c7

@Entity
public class Users {

<<<<<<< HEAD
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String role;

	private boolean resetPassword;
	
	
	private boolean firstTimeLogin=true;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public Users(Long id, String name, String password, String email, String role, boolean resetPassword,
			boolean firstTimeLogin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
		this.resetPassword = resetPassword;
		this.firstTimeLogin = firstTimeLogin;
	}






	public boolean isResetPassword() {
		return resetPassword;
	}






	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}



	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}






	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", resetPassword=" + resetPassword + ", firstTimeLogin=" + firstTimeLogin + "]";
	}
	
	



	
	
	
	
	
=======
>>>>>>> c1ceaf78c74f07acefc55d52dac55afe4c71b5c7
	
}
