package com.cg.creditcardpayment.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
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

public class Payments {
		/**
		 * This a local variable: {@link #paymentId} defines the unique Id for each Payment
		 * @HasGetter
		 * @HasSetter
		 */
		
		private Long paymentId;
		/**
		 * This a local variable: {@link #paymentMethod} defines the payment method to make payment
		 * @HasGetter
		 * @HasSetter
		 */	
		
		private String paymentMethod;
		/**
		 * This a local variable: {@link #paidDate} defines the payment date
		 * @HasGetter
		 * @HasSetter
		 */
		
		private LocalDate paidDate;
		/**
		 * This a local variable: {@link #paidTime} defines the payment time
		 * @HasGetter
		 * @HasSetter
		 */
		
		private LocalTime paidTime;
		/**
		 * This a local variable: {@link #amount} defines the amount to be paid by Customer
		 * @HasGetter
		 * @HasSetter
		 */
		
		private Double amount;

	
		private String card;
		
		

		public Payments() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Payments(Payment payment) {
			super();
			this.paymentId = payment.getPaymentId();
			this.paymentMethod = payment.getPaymentMethod();
			this.paidDate = payment.getPaidDate();
			this.paidTime = payment.getPaidTime();
			this.amount = payment.getAmount();
			this.card = payment.getCard().getCardNumber();
		}


		public Long getPaymentId() {
			return paymentId;
		}


		public void setPaymentId(Long paymentId) {
			this.paymentId = paymentId;
		}


		public String getPaymentMethod() {
			return paymentMethod;
		}


		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}


		public LocalDate getPaidDate() {
			return paidDate;
		}


		public void setPaidDate(LocalDate paidDate) {
			this.paidDate = paidDate;
		}


		public LocalTime getPaidTime() {
			return paidTime;
		}


		public void setPaidTime(LocalTime paidTime) {
			this.paidTime = paidTime;
		}


		public Double getAmount() {
			return amount;
		}


		public void setAmount(Double amount) {
			this.amount = amount;
		}


		public String getCard() {
			return card;
		}


		public void setCard(String card) {
			this.card = card;
		}
		
		

}
