package com.cg.creditcardpayment.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.CreditCardRepository;

import com.cg.creditcardpayment.dao.PaymentRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Statement;

import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.PaymentException;
import com.cg.creditcardpayment.exceptions.StatementException;

@Service
public class PaymentServices implements IPaymentService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private CreditCardRepository creditCardRepo;

	@Autowired
	private IPaymentService paymentService;

	@Override
	public boolean existsById(Long paymentId) //This method is used to check paymentId exists or not
	{
		logger.info("Called existsById() method of PayamentServices");
		return paymentRepo.existsById(paymentId);
	}

	@Override
	public Payment updatePayment(Payment payment) throws PaymentException //This method is used to update payment details
	{
		logger.info("Called addPayment() method of PayamentServices");
		if (payment != null) {
			if (paymentRepo.existsById(payment.getPaymentId())) {
				throw new PaymentException("Payment " + payment.getPaymentId() + " is already Exists");
			} else {
				payment = paymentRepo.save(payment);
			}
		}
		return payment;
	}

	@Override
	public Payment addPayment(Payment payment) throws PaymentException//This method is used to add payment details
	{
		logger.info("Called savePayment() method of PayamentServices");
		if (payment == null) {
			throw new PaymentException("Payment can not be Null");
		}
		return (paymentRepo.save((payment)));
	}

	@Override
	public Payment deletePaymentById(Long paymentId) throws PaymentException//This method is used to delete payment details using paymentId
	{
		logger.info("Called deletePaymentById() method of PayamentServices");
		if (paymentId == null) {
			throw new PaymentException("payment Id can not be null");
			
		} 
		else if (!paymentRepo.existsById(paymentId)) 
		{
			throw new PaymentException("Payment Id " + paymentId + " Does not Exist");
		}
		else 
		{
			Payment payment=findPaymentById(paymentId);
			
			paymentRepo.deleteById(paymentId);
			
			return payment;
		}
	}

	@Override
	public Payment findPaymentById(Long paymentId) throws PaymentException //This method is used to fetch payment details using paymentId
	{
		logger.info("Called findPaymentById() method of PayamentServices");
		if (paymentId == null) {
			throw new PaymentException("payment Id can not be null");
		} else if (!paymentRepo.existsById(paymentId)) {
			throw new PaymentException("Payment Id " + paymentId + " Does not Exist");
		}
		return (paymentRepo.findById(paymentId).orElse(null));
	}

	@Override
	public List<Payment> findAllPayments() //This method is used to fetch list of all payment details
	{
		logger.info("Called findAllPayments() method of PayamentServices");
		return paymentRepo.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public List<Statement> pendingBills(String cardNumber) throws CreditCardException //This method is used to fetch list of statement of payment pending.
	{
		logger.info("Called pendingBills() method of PayamentServices");
		CreditCard card = creditCardRepo.findById(cardNumber).orElse(null);
		if (cardNumber == null) {
			throw new CreditCardException("Card Number can not be null");
		} else if (card == null) {
			throw new CreditCardException("Card does not Exists");
		}
		Set<Statement> statements = card.getStatement();

		List<Statement> pendingStatements = statements.stream().filter(state -> state.getDueAmount() >= 0.0001)
				.distinct().collect(Collectors.toList());

		pendingStatements.sort((st1, st2) -> st1.getStatementId().compareTo(st2.getStatementId()));

		return pendingStatements;

	}

	@Override
	public List<Payment> paymentHistory(String cardNumber) throws CreditCardException //This method is used to fetch list of payment using creditcard
	{
		logger.info("Called paymentHistory() method of PayamentServices");
		CreditCard card = creditCardRepo.findById(cardNumber).orElse(null);
		if (cardNumber == null) {
			throw new CreditCardException("Card number can not be null");
		} else if (card == null) {
			throw new CreditCardException("Credit Card does not exists");
		}
		return card.getPayments().stream().collect(Collectors.toList());
	}

	@Override
	public Payment payForCreditCard(Payment pay, String cardNumber)
			throws PaymentException, CreditCardException, StatementException //This method is used to make payment of creditcard
	{
		logger.info("Called payForCreditCard() method of PayamentServices");
		Optional<CreditCard> card = creditCardRepo.findById(cardNumber);
		if (!card.isPresent()) {
			throw new CreditCardException("Card does not exists");
		} else {
			card.get().setCardNumber(cardNumber);

			Payment payment = new Payment();
			payment.setPaymentId(pay.getPaymentId());
			payment.setCard(card.get());
			payment.setPaymentMethod(pay.getPaymentMethod());
			Double amount = pay.getAmount();
			payment.setAmount(amount);
			card.get().setUsedLimit(card.get().getUsedLimit() - amount);
			payment.setPaidDate(LocalDate.now());
			payment.setPaidTime(LocalTime.now());
			paymentService.addPayment(payment);
			return payment;
		}
	}

}
