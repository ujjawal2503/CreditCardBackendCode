package com.cg.creditcardpayment.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

/**
* CustomerEntity
* The Customer program implements an application such that
* the data of the customer is sent to the database 
*/
@Entity
public class Customer {
	/**
	 * This a local variable: {@link #userId} defines the unique Id for Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@NotNull(message = "User name can't be null")
	@Size(min = 4,max = 10,message = "Please provide a valid user id")
	private String username;
    /**
	 * This a local variable: {@link #name} defines the user name of Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message = "Name can't be null")
	@Size(min = 2,max=20,message = "Please provide a valid Customer Name")
	private String name;
	/**
	 * This a local variable: {@link #email} defines the user Email of the Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(unique = true)
	@NotNull(message = "Email can't be null")
	@Email(message = "Please provide a valid email")
	private String email;
	/**
	 * This a local variable: {@link #contactNo} defines the customer mobile number. 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(unique = true)
	@NotNull(message = "ContactNo can't be null")
	@Size(min = 10,max = 10,message = "Please provide 10 digit mobile number")
	private String contactNo;
	/**
	 * This a local variable: {@link #dob} defines the Date of Birth of the Customer which should not be in past
	 * @HasGetter
	 * @HasSetter
	 */
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	/**
	 * This a local variable: {@link #address} defines the address of the Customer which should not be Null
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message = "Address can't be null")
	@Size(min = 3,max = 30,message = "Please provide valid Address")
	private String address;
	
	@NotNull(message = "Password can't be null")
	@Size(min = 4,max = 16,message = "Please provide valid Password")
	private String password;
		
	@JsonManagedReference(value="customer")
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Statement> statements;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="username"),
	inverseJoinColumns=@JoinColumn(name="account_number"))
	private Set<Account> accounts;
	
	@JsonManagedReference(value="credit-customer")
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<CreditCard> creditCards;
	
	//Default constructor
	public Customer() {
		super();
	}
	
	
	/**
	 * @param userId
	 * @param name
	 * @param email
	 * @param contactNo
	 * @param dob
	 * @param address
	 */
	public Customer(
			@NotNull(message = "User id can't be null") @Size(min = 4, max = 10, message = "Please provide a valid user id") String username,
			@NotNull(message = "Name can't be null") @Size(min = 2, max = 20, message = "Please provide a valid Customer Name") String name,
			@NotNull(message = "Email can't be null") @Email(message = "Please provide a valid email") String email,
			@NotNull(message = "ContactNo can't be null") @Size(min = 10, max = 10, message = "Please provide 10 digit mobile number") String contactNo,
			@Past LocalDate dob,
			@NotNull(message = "Address can't be null") @Size(min = 3, max = 30, message = "Please provide valid Address") String address,
			@NotNull(message = "Password can't be null") @Size(min = 4, max = 16, message = "Please provide valid Password") String password) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
		this.password = password;
	}
	

	public Customer(
			@NotNull(message = "User id can't be null") @Size(min = 4, max = 10, message = "Please provide a valid user id") String username,
			@NotNull(message = "Name can't be null") @Size(min = 2, max = 20, message = "Please provide a valid Customer Name") String name,
			@NotNull(message = "Email can't be null") @Email(message = "Please provide a valid email") String email,
			@NotNull(message = "ContactNo can't be null") @Size(min = 10, max = 10, message = "Please provide 10 digit mobile number") String contactNo,
			@Past LocalDate dob,
			@NotNull(message = "Address can't be null") @Size(min = 3, max = 30, message = "Please provide valid Address") String address,
			@NotNull(message = "Password can't be null") @Size(min = 4, max = 16, message = "Please provide valid Password") String password,
			List<Statement> statements, Set<Account> accounts, List<CreditCard> creditCards) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
		this.password = password;
		this.statements = statements;
		this.accounts = accounts;
		this.creditCards = creditCards;
	}


	public Set<Account> getAccounts() {
		return accounts;
	}
	

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
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

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	
}
