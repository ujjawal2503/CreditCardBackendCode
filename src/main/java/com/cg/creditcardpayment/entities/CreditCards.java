package com.cg.creditcardpayment.entities;

import java.time.LocalDate;


public class CreditCards {

	private String cardNumber;
	/**
	 * This a local variable: {@link #cardName} defines the name of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */

	private String cardName;
   /**
	 * This a local variable: {@link #cardType} defines the type of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	
	private String cardType;
   /**
	 * This a local variable: {@link #expiryDate} defines the expire date of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
    private LocalDate expiryDate;
	/**
	 *This a local variable: {@link #bankName} defines the bank name of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */

	private String bankName;
	/**
	 * This a local variable: {@link #cvv} defines the cvv present on the credit card and it should not be null
	 * @HasGetter
	 * @HasSetter
	 */

    private Integer cvv;
	/**
	 * This a local variable: {@link #creditLimit} defines the maximum limit that can be used by the user
	 * @HasGetter
	 * @HasSetter
	 */
	
	private Double creditLimit;
	/**
	 * This a local variable: {@link #usedLimit} defines the limit used by the customer from the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
 
	private Double usedLimit;
	
	
	
	private String  username;



	/**
	 * 
	 */
	public CreditCards() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param cardNumber
	 * @param cardName
	 * @param cardType
	 * @param expiryDate
	 * @param bankName
	 * @param cvv
	 * @param creditLimit
	 * @param usedLimit
	 * @param customerName
	 */
	public CreditCards(CreditCard creditCard) {
		super();
		this.cardNumber = creditCard.getCardNumber();
		this.cardName = creditCard.getCardName();
		this.cardType = creditCard.getCardType();
		this.expiryDate = creditCard.getExpiryDate();
		this.bankName = creditCard.getBankName();
		this.cvv = creditCard.getCvv();
		this.creditLimit = creditCard.getCreditLimit();
		this.usedLimit = creditCard.getUsedLimit();
		this.username = creditCard.getCustomer().getUsername();
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public String getCardName() {
		return cardName;
	}



	public void setCardName(String cardName) {
		this.cardName = cardName;
	}



	public String getCardType() {
		return cardType;
	}



	public void setCardType(String cardType) {
		this.cardType = cardType;
	}



	public LocalDate getExpiryDate() {
		return expiryDate;
	}



	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public Integer getCvv() {
		return cvv;
	}



	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}



	public Double getCreditLimit() {
		return creditLimit;
	}



	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}



	public Double getUsedLimit() {
		return usedLimit;
	}



	public void setUsedLimit(Double usedLimit) {
		this.usedLimit = usedLimit;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}




	
}
