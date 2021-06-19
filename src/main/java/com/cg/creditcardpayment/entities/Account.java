package com.cg.creditcardpayment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
* AccountEntity
* The Account program implements an application such that
* the data of the account is sent to the database
*/
@Entity
public class Account {

   /**
	 * This a local variable: {@link #accountNumber} defines the unique account number
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Min(value = 10000000)@Max(value = 99999999)
	private Long accountNumber;
   /**
	 * This a local variable: {@link #accountName} defines the name of the Bank
	 * @HasGetter
	 * @HasSetter
    */
	@Column
	@NotNull(message = "Account Name is Required")
	@Size(min = 2, max = 30, message = "Please enter correct name")
	private String accountName;
   /**
	 * This a local variable: {@link #balance} defines the balance amount remaining in the Bank account
	 * @HasGetter
	 * @HasSetter
    */
	@Column
	@PositiveOrZero(message = "Balance should be zero or more")
	private double balance;
	/**
	 * This a local variable: {@link #balance} defines the type of the account in the Bank 
	 * @HasGetter
	 * @HasSetter
    */	
	@Column
	@NotNull(message = "Account type is required")
	@Size(min = 2, max = 30, message = "Please enter correct account type")
	private String type;
	
	//Default Constructor
	public Account() {
		super(); 
	}
	/**
	 * Parameterised Constructor
	 * @param accountNumber 
	 * @param accountName
	 * @param balance
	 * @param type
	 */
	
	public Account(@Min(10000000) @Max(99999999) Long accountNumber,
			@NotNull(message = "Account Name is Required") @Size(min = 2, max = 30, message = "Please enter correct name") String accountName,
			@PositiveOrZero(message = "Balance should be zero or more") double balance,
			@NotNull(message = "Account type is required") @Size(min = 2, max = 30, message = "Please enter correct account type") String type) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.type = type;
	}
    /**
     * 
     * Setters and Getters for all the local variable
     * 
     */
	

	public Long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
