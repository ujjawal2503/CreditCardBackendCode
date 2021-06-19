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
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
* PaymentEntity
* The Payment program implements an application such that
* the data of the payment is sent to the database
*/
@Entity
public class Payment {
	/**
	 * This a local variable: {@link #paymentId} defines the unique Id for each Payment
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
	/**
	 * This a local variable: {@link #paymentMethod} defines the payment method to make payment
	 * @HasGetter
	 * @HasSetter
	 */	
	@Column(nullable=false)
	@Size(min = 3,max = 15,message = "Please provide correct payment method")
	private String paymentMethod;
	/**
	 * This a local variable: {@link #paidDate} defines the payment date
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="paid_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paidDate;
	/**
	 * This a local variable: {@link #paidTime} defines the payment time
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="paid_time")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime paidTime;
	/**
	 * This a local variable: {@link #amount} defines the amount to be paid by Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="amount",nullable=false)
	@Positive(message = "Please provide postive amount")
	private Double amount;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="card_number")
	private CreditCard card;
	public Payment() {
		super();
		
	}
	
	/**
	 * @param paymentId
	 * @param paymentMethod
	 * @param paidDate
	 * @param paidTime
	 * @param amount
	 * @param card
	 */
	public Payment(Long paymentId,
			@Size(min = 3, max = 15, message = "Please provide correct payment method") String paymentMethod,
			LocalDate paidDate, LocalTime paidTime, @Positive(message = "Please provide postive amount") Double amount,
			CreditCard card) {
		super();
		this.paymentId = paymentId;
		this.paymentMethod = paymentMethod;
		this.paidDate = paidDate;
		this.paidTime = paidTime;
		this.amount = amount;
		this.card = card;
	}

	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	
   public CreditCard getCard() {
		return card;
	}
	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return the paidDate
	 */
	public LocalDate getPaidDate() {
		return paidDate;
	}
	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}
	/**
	 * @return the paidTime
	 */
	public LocalTime getPaidTime() {
		return paidTime;
	}
	/**
	 * @param paidTime the paidTime to set
	 */
	public void setPaidTime(LocalTime paidTime) {
		this.paidTime = paidTime;
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

}
