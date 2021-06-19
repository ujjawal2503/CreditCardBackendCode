package com.cg.creditcardpayment.entities;

import java.time.LocalDate;
import java.time.LocalTime;



public class Transactions {

	private Long transactionId;
	/**
	* This a local variable: {@link #status} defines the status of the transaction
	* @HasGetter
	* @HasSetter
	*/
	
	private String status;
	/**
	* This a local variable: {@link #amount} defines the amount of transaction made
	* @HasGetter
	* @HasSetter
	*/

	private Double amount;
	/**
	* This a local variable: {@link #transactionDate} defines date of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	
	private LocalDate transactionDate;
	/**
	* This a local variable: {@link #transactionTime} defines the time of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	
	private LocalTime transactionTime;


	private String creditCardNumber;


	/**
	 * 
	 */
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionId
	 * @param status
	 * @param amount
	 * @param transactionDate
	 * @param transactionTime
	 */
	public Transactions(Transaction transaction) {
		super();
		this.transactionId = transaction.getTransactionId();
		this.status = transaction.getStatus();
		this.amount = transaction.getAmount();
		this.transactionDate = transaction.getTransactionDate();
		this.transactionTime = transaction.getTransactionTime();
		this.creditCardNumber= transaction.getCreditCard().getCardNumber();
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	public LocalTime getTransactionTime() {
		return transactionTime;
	}


	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}


	public String getCreditCardNumber() {
		return creditCardNumber;
	}


	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	
	
}
