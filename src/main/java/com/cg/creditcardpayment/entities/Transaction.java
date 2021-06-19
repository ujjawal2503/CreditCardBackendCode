package com.cg.creditcardpayment.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
*TransactionEntity
* The Transaction program implements an application such that
* the data of the transaction is sent to the database
*/
@Entity
public class Transaction {
	/**
	* This a local variable: {@link #transactionId} defines the unique id of the transaction 
	* @HasGetter
	* @HasSetter
	*/
	@Id
	@Column(name = "trans_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	/**
	* This a local variable: {@link #status} defines the status of the transaction
	* @HasGetter
	* @HasSetter
	*/
	@Column(name = "status")
	@Size(min = 4,max = 15,message = "Please provide a valid status")
	private String status;
	/**
	* This a local variable: {@link #amount} defines the amount of transaction made
	* @HasGetter
	* @HasSetter
	*/
	@Column(name = "amount", nullable = false)
	@Positive(message = "Please provide a positive amount")
	private Double amount;
	/**
	* This a local variable: {@link #transactionDate} defines date of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@PastOrPresent(message = "Please provide a valid date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate transactionDate;
	/**
	* This a local variable: {@link #transactionTime} defines the time of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime transactionTime;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_number")
	private CreditCard creditCard;
    
	//Default Constructor
	public Transaction() {
		super();
	}


	/**
	 * @param transactionId
	 * @param status
	 * @param amount
	 * @param transactionDate
	 * @param transactionTime
	 */
	public Transaction(Long transactionId,
			@Size(min = 4, max = 15, message = "Please provide a valid status") String status,
			@Positive(message = "Please provide a positive amount") Double amount,
			@PastOrPresent(message = "Please provide a valid date") LocalDate transactionDate,
			LocalTime transactionTime) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
	}


	/**
	 * @param transactionId
	 * @param status
	 * @param amount
	 * @param transactionDate
	 * @param transactionTime
	 * @param creditCard
	 */
	public Transaction(Long transactionId,
			@Size(min = 4, max = 15, message = "Please provide a valid status") String status,
			@Positive(message = "Please provide a positive amount") Double amount,
			@PastOrPresent(message = "Please provide a valid date") LocalDate transactionDate,
			LocalTime transactionTime, CreditCard creditCard) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.creditCard = creditCard;
	}


	/**
	 * @return the transactionId
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the status of the Transaction
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the transactionDate
	 */
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the transactionTime
	 */
	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
