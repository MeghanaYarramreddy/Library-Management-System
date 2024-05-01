package com.lms.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {

	@Id
	private String adminEmail;
	private String password;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String adminEmail, String password) {
		super();
		this.adminEmail = adminEmail;
		this.password = password;
	}

	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
