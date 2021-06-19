package com.cg.creditcardpayment.entities;

import java.time.LocalDate;

public class Customers {
	/**
	 * This a local variable: {@link #userId} defines the unique Id for Customer
	 * @HasGetter
	 * @HasSetter
	 */
	
	private String username;
    /**
	 * This a local variable: {@link #name} defines the user name of Customer
	 * @HasGetter
	 * @HasSetter
	 */
	
	private String name;
	/**
	 * This a local variable: {@link #email} defines the user Email of the Customer
	 * @HasGetter
	 * @HasSetter
	 */
	
	private String email;
	/**
	 * This a local variable: {@link #contactNo} defines the customer mobile number. 
	 * @HasGetter
	 * @HasSetter
	 */
	
	private String contactNo;
	/**
	 * This a local variable: {@link #dob} defines the Date of Birth of the Customer which should not be in past
	 * @HasGetter
	 * @HasSetter
	 */
	
	private LocalDate dob;
	/**
	 * This a local variable: {@link #address} defines the address of the Customer which should not be Null
	 * @HasGetter
	 * @HasSetter
	 */
	private String address;
	
	private String password;
	
	public Customers()
	{
		super();
	}
	
	public Customers(Customer customer) {
		super();
		this.username = customer.getUsername();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.contactNo = customer.getContactNo();
		this.dob = customer.getDob();
		this.address = customer.getAddress();
		this.password=customer.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
	