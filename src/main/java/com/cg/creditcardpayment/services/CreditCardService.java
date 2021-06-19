package com.cg.creditcardpayment.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.CreditCardRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;

@Service
public class CreditCardService implements ICreditCardService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CreditCardRepository creditCardRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;

	@Override
	public CreditCard updateCreditCard(CreditCard creditCard) throws CreditCardException//This method is used to update the creditcard details
	{
		logger.info("Called updateCreditCard() method of CreditCardService");
		if(creditCard !=null) { 
			if(!creditCardRepo.existsById(creditCard.getCardNumber())) {
				throw new CreditCardException("CreditCard "+creditCard.getCardNumber()+" doesn't  Exists");
			}else {
				return creditCardRepo.save(creditCard);
			}
		}
		throw new CreditCardException("CreditCard cannot be null");
	}

	
	@Override
	public CreditCard addCreditCard(CreditCard creditCard) throws CreditCardException//This method is used to add the creditcard detail
	{
		logger.info("Called addCreditCard() method of CreditCardService");
		if(creditCard==null) {
			throw new CreditCardException("Credit Card  should not be null");
		}else {
			return (creditCardRepo.save(creditCard));
		}
	}

	
	@Override
	public CreditCard deleteCreditCardById(String creditCardId) throws CreditCardException //This method is used to delete the creditcard detail using card number
	{
		logger.info("Called deleteCreditCard() method of CreditCardService");
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (!creditCardRepo.existsById(creditCardId)) {
			throw new CreditCardException("Card Number "+creditCardId+" does not exists");
		}else {
			    CreditCard creditCard=findCreditCardById(creditCardId);
					creditCardRepo.deleteById(creditCardId);
					return creditCard;
		}
	}

	
	@Override
	public CreditCard findCreditCardById(String creditCardId) throws CreditCardException //This method is used to fetch the creditcard detail using card number
	{
		logger.info("Called findCreditCardById() method of CreditCardService");
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (creditCardRepo.existsById(creditCardId)) {
			return creditCardRepo.findById(creditCardId).get();
			
		}else {
			throw new CreditCardException("Card Number "+creditCardId+" does not exists");
		}
	}

	
	@Override
	public List<CreditCard> findAll() //This method is used to fetch all the creditcard details
	{
		logger.info("Called findAll() method of CreditCardService");
		return creditCardRepo.findAll().stream().collect(Collectors.toList());
	}

	
	@Override
	public boolean creditCardExistsById(String cardNumber) throws CreditCardException //This method is used to check whether the creditcard detail exist or not using card number
	{
		if(cardNumber==null) {
			throw new CreditCardException("Card Number should not be null");
		}
		return creditCardRepo.existsById(cardNumber);
	}

	

	
	@Override
	public List<CreditCard> findByCustomerId(String customerId) throws CreditCardException, CustomerNotFoundException //This method is used to fetch the creditcard detail using customerId	
	{
		logger.info("Called findByCustomerId() method of CreditCardService");
		Optional<Customer> optional=customerRepo.findById(customerId);
		if(optional.isPresent())
		{
			List<CreditCard> creditCards=creditCardRepo.findByCustomerId(customerId);
			if(creditCards.size()==0)
				throw new CreditCardException("No Credit cards found");
			return creditCards;
		}
		else {
			throw new CustomerNotFoundException("Customer does not exists");
		}
	}


}
