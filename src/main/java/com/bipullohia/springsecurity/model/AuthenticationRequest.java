package com.bipullohia.springsecurity.model;

public class AuthenticationRequest {

	private String username;
	private String pwd;
	
	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
