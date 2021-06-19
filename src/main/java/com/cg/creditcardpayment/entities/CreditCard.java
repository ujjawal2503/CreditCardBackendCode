package com.cg.creditcardpayment.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
* CreditCardEntity
* The CreditCard program implements an application such that
* the data of the creditcards is sent to the database
*/
@Entity
public class CreditCard {
	/**
	 * This a local variable: {@link #cardNumber} defines the unique Number for Credit Card
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@NotNull(message = "Card Number can't be null")
	@Size(min = 12,max = 12,message = "Please provide a valid Credit Card Number")
	private String cardNumber;
	/**
	 * This a local variable: {@link #cardName} defines the name of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@Column(name="card_name", nullable=false)
	@Size(min=2,max=30,message = "Please provide a valid Card Name")
	private String cardName;
   /**
	 * This a local variable: {@link #cardType} defines the type of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@Column(name="card_type",nullable=false)
	@Size(min=2,max = 30,message = "Please provide a valid Card Type")
	private String cardType;
   /**
	 * This a local variable: {@link #expiryDate} defines the expire date of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="expiry_date", nullable=false)
	@Future(message = "Expiry date should be near future")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
	/**
	 *This a local variable: {@link #bankName} defines the bank name of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="bank_name", nullable=false)
	@Size(min = 2,max = 30,message = "Please provide a valid bank name")
	private String bankName;
	/**
	 * This a local variable: {@link #cvv} defines the cvv present on the credit card and it should not be null
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="cvv", nullable=false)
	@Min(value=100)@Max(value = 999)
    private Integer cvv;
	/**
	 * This a local variable: {@link #creditLimit} defines the maximum limit that can be used by the user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="credit_limit",nullable=false)
	@Positive(message = "Credit limit should be positive")
	private Double creditLimit;
	/**
	 * This a local variable: {@link #usedLimit} defines the limit used by the customer from the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
    @Column(name="used_limit")
	@PositiveOrZero(message = "Used limit should be zero or positive")
	private Double usedLimit;
	
	
	@JsonBackReference(value="credit-customer")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private Customer customer;


	
	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<Transaction> transaction;
	

	@JsonManagedReference(value = "credit-card")
	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<Statement> statement;


	@OneToMany(mappedBy="card",cascade=CascadeType.ALL)
	private List<Payment> payments;
	
	/**
	 * Default Constructor
	 */
	public CreditCard() {
		/* Default Constructor */
	}
	



	public CreditCard(
			@NotNull(message = "Card Number can't be null") @Size(min = 12, max = 12, message = "Please provide a valid Credit Card Number") String cardNumber,
			@Size(min = 2, max = 30, message = "Please provide a valid Card Name") String cardName,
			@Size(min = 2, max = 30, message = "Please provide a valid Card Type") String cardType,
			@Future(message = "Expiry date should be near future") LocalDate expiryDate,
			@Size(min = 2, max = 30, message = "Please provide a valid bank name") String bankName,
			@Min(100) @Max(999) Integer cvv, @Positive(message = "Credit limit should be positive") Double creditLimit,
			@PositiveOrZero(message = "Used limit should be zero or positive") Double usedLimit) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.creditLimit = creditLimit;
		this.usedLimit = usedLimit;
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
	 * @param customer
	 */
	public CreditCard(
			@NotNull(message = "Card Number can't be null") @Size(min = 12, max = 12, message = "Please provide a valid Credit Card Number") String cardNumber,
			@Size(min = 2, max = 30, message = "Please provide a valid Card Name") String cardName,
			@Size(min = 2, max = 30, message = "Please provide a valid Card Type") String cardType,
			@Future(message = "Expiry date should be near future") LocalDate expiryDate,
			@Size(min = 2, max = 30, message = "Please provide a valid bank name") String bankName,
			@Min(100) @Max(999) Integer cvv, @Positive(message = "Credit limit should be positive") Double creditLimit,
			@PositiveOrZero(message = "Used limit should be zero or positive") Double usedLimit, Customer customer) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.creditLimit = creditLimit;
		this.usedLimit = usedLimit;
		this.customer = customer;
	}




	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	/**
	 * @return the expiryDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the cvv
	 */
	public Integer getCvv() {
		return cvv;
	}

	/**
	 * @param cvv the cvv to set
	 */
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	/**
	 * @return the usedLimit
	 */
	public Double getUsedLimit() {
		return usedLimit;
	}

	/**
	 * @param usedLimit the usedLimit to set
	 */
	public void setUsedLimit(Double usedLimit) {
		this.usedLimit = usedLimit;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the transaction
	 */
	public Set<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the statement
	 */
	public Set<Statement> getStatement() {
		return statement;
	}

	/**
	 * @param statement the statement to set
	 */
	public void setStatement(Set<Statement> statement) {
		this.statement = statement;
	}

	/**
	 * @return the payments
	 */
	public List<Payment> getPayments() {
		return payments;
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	/**
	 * @return creditLimit in Double
	 */
	public Double getCreditLimit() {
		return creditLimit;
	}

	
}
