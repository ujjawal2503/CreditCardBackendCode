package com.cg.creditcardpayment.request;

public class LoginRequest {

	private String username;

	private String password;

	private String Role;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		Role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	
	
}