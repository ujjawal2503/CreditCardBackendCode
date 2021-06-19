package com.cg.creditcardpayment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Admin {

	@Id
	@Column(unique= true)
	@NotNull(message = "username cannot be null")
	@Size(min = 4)
	private String username;

	@NotNull(message = "password cannot be null")
	@Size(min = 4, max = 10, message = "Password must be greater than or equal to 5 characters and less than 10 characters")
	@JsonIgnore
	private String password;
	
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 4, max = 15, message = "Name must be greater than or equal to 5 characters and less than 15 characters")
	@JsonIgnore
	private String name;

	
	public Admin() {
		super();
	}

	public Admin( @NotNull(message = "username cannot be null") @Size(min = 4) String username,
			@NotNull(message = "password cannot be null") @Size(min = 4, max = 10, message = "Password must be greater than or equal to 5 characters and less than 10 characters") String password,
			@NotNull(message = "Name cannot be null")@Size(min = 4, max = 15, message = "Name must be greater than or equal to 5 characters and less than 15 characters") String name) {
		super();
		this.username = username;
		this.password = password;
		this.name=name;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}