package com.hsbc.easset.converter;

public class User {

	private String id;
	  private String name;
	  private String telNo;
	  private String role;
	  private String mailId;
	  private String UserName;
	  private String password;

	  public User()
	  {
		  
	  }

	public User(String id, String name, String telNo, String role, String mailId, String userName, String password) {
		super();
		this.id = id;
		this.name = name;
		this.telNo = telNo;
		this.role = role;
		this.mailId = mailId;
		UserName = userName;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", telNo=" + telNo + ", role=" + role + ", mailId=" + mailId
				+ ", UserName=" + UserName + ", password=" + password + "]";
	}
	  
	
}
