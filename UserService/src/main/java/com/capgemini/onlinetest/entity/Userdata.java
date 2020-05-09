package com.capgemini.onlinetest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="userr")
public class Userdata {
	@Id
	private int userid;
	@Column(length=20)
	private String username;
	
	@OneToOne(targetEntity=TestOnline.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="testid", referencedColumnName="testId") 
	private TestOnline usertest;
	@Size(min=3, max=20, message="password must be minimum 8 characters")
	private String userPassword;
	@Min(value=4)
	private long userPhoneno;
	@Email(message="enter proper email id")
	private String userEmail;
	@NotBlank(message="usertype cannot be empty")
	private String userType;
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//@JsonIgnore
	public TestOnline getUsertest() {
		return usertest;
	}
	public void setUsertest(TestOnline usertest) {
		this.usertest = usertest;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public long getUserPhoneno() {
		return userPhoneno;
	}
	public void setUserPhoneno(long userPhoneno) {
		this.userPhoneno = userPhoneno;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
