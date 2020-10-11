package com.hsbc.easset.models;

import java.time.LocalDateTime;

public class User {
	private int uniqueId;
	private String name;
	private RoleType role;
	private long telphoneNumber;
	private String emailId;
	private String password;
	private String username;
	private LocalDateTime lastLogin;
	public int getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(int uniqueId) {
			this.uniqueId = uniqueId;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
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
