package com.hsbc.easset.models;

public class User {
	private String name;
	private RoleType role;
	private long telphoneNumber;
	private String emailId;
	private String password;
	private String username;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	public long getTelphoneNumber() {
		return telphoneNumber;
	}
	public void setTelphoneNumber(long number) {
		this.telphoneNumber = number;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailID) {
		this.emailId = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
