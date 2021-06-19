package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Statement;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.PaymentException;
import com.cg.creditcardpayment.exceptions.StatementException;


public interface IPaymentService {
	
	public Payment updatePayment(Payment payment) throws PaymentException;
	public Payment addPayment(Payment payment) throws PaymentException;
	public Payment deletePaymentById(Long paymentId) throws PaymentException;
	public Payment findPaymentById(Long paymentId) throws PaymentException;
	public List<Statement> pendingBills(String cardNumber) throws CreditCardException;
	public List<Payment> paymentHistory (String cardNumber) throws CreditCardException;
	public Payment payForCreditCard(Payment pay, String cardNumber) throws PaymentException, CreditCardException, StatementException;
	public List<Payment> findAllPayments();
	public boolean existsById(Long paymentId);


}
