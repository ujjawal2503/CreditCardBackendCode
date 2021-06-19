package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;

public interface ICreditCardService {

	
	boolean creditCardExistsById(String cardNumber) throws CreditCardException;
	
	CreditCard addCreditCard(CreditCard creditCard) throws CreditCardException;
	
	CreditCard updateCreditCard(CreditCard creditCard) throws CreditCardException;
	
	CreditCard deleteCreditCardById(String cardNumber) throws CreditCardException;
	
	CreditCard findCreditCardById(String cardNumber) throws CreditCardException;
	
	List<CreditCard> findAll();
	
	List<CreditCard> findByCustomerId(String customerId) throws CreditCardException, CustomerNotFoundException;

}
