package com.cg.creditcardpayment.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.CreditCards;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.services.CreditCardService;

/**
 * CreditCardController
 * The CreditCardController program takes care of mapping
 * the url's to the functions which are specific to the CreditCard
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/creditCard")
public class CreditCardController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * This a local variable: {@link #creditCardService} is the object of CreditCardService which is used to access the functions in CreditCardServices 
	 */
	
	@Autowired
	private CreditCardService creditCardService;
	
	/**
	 * This method sends the new creditcard details to the add method in creditcard service
	 * @param creditCard which contains all the creditcard details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added creditcard details
	 * @throws CreditCardException when there is an exception
	 */
	
	@PostMapping("/addCreditCard")
	public ResponseEntity<CreditCard> add(@RequestBody @Valid CreditCard creditCard,BindingResult bindingResult) throws CreditCardException {
		logger.info("Called POST mapping add() method");
		if (bindingResult.hasErrors()) {
			throw new CreditCardException("Statement information is not valid, please try again!");
		}
		ResponseEntity<CreditCard> response=null;
		if(creditCard==null) 
			throw new CreditCardException("CreditCard information cannot be null , please try again!");
		else if(creditCardService.creditCardExistsById(creditCard.getCardNumber()))
		{
			throw new CreditCardException("CREDITCARD with card number" + creditCard.getCardNumber() + " Already Exist");
		}
		else 
			{CreditCard creditCard1=creditCardService.addCreditCard(creditCard);
			response = new ResponseEntity<CreditCard>( creditCard1, HttpStatus.OK);}
		return response;
	}
	
	/**
	 * This method retrieve the creditcard details of the given cardnumber
	 * @param  cardNumber to find the creditcard details
	 * @return response is a ResponseEntity with HTTP status which contain the credit details of the specific cardnumber
	 * @throws CreditCardException when there is an exception
	 */
	
	@GetMapping("/getCreditCard/{cardNumber}")
	public ResponseEntity<CreditCards> findById(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		logger.info("Called GET mapping findById() method");
		
		ResponseEntity<CreditCards> response=null;
		if(!(creditCardService.creditCardExistsById(cardNumber)) || cardNumber==null) {
			throw new CreditCardException("CreditCard does not exist or it is null");
		}else {
			CreditCard creditCard=creditCardService.findCreditCardById(cardNumber);
			CreditCards creditCards=new CreditCards(creditCard);
			response=new ResponseEntity<CreditCards>(creditCards, HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * This method delete the creditcard details using cardnumber from creditcard service
	 * @param cardNumber which contains all the creditcard details
	 * @return response is a ResponseEntity with HTTP status
	 * @throws CreditCardException when there is an exception
	 */
	
	@DeleteMapping("/deleteCreditCard/{cardNumber}")
	public ResponseEntity<CreditCard> deleteCreditCard(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		logger.info("Called DELETE mapping deleteCreditCard() method");
		
		ResponseEntity<CreditCard> response=null;
	
		if(!(creditCardService.creditCardExistsById(cardNumber)) || cardNumber==null) {
			throw new CreditCardException("CreditCard does not exist or it is null");
		}
		else {
		        response=new ResponseEntity<CreditCard>( creditCardService.deleteCreditCardById(cardNumber), HttpStatus.OK);
			 
		}
		return response;
	}
	/**
	 * This method update the creditcard details in creditcard service
	 * @param creditCard which contains all the creditcard details need to be updated
	 * @return response is a ResponseEntity with HTTP status which contain the newly updated creditcard details
	 * @throws CreditCardException when there is an exception
	 */
	
	@PutMapping("/updateCreditCard")
	public ResponseEntity<CreditCard> updateCreditCard(@RequestBody @Valid CreditCard creditCard,BindingResult bindingResult) throws CreditCardException{
		logger.info("Called PUT mapping updateCreditCard() method");
		if (bindingResult.hasErrors()) {
			throw new CreditCardException("CreditCard information is not valid, please try again!");
		}
		ResponseEntity<CreditCard> response=null;
		if(creditCard==null) 
		{
			throw new CreditCardException("CreditCard details cannot be null");
		}
//		else if(!creditCard.getCardNumber().equals(cardNumber))
//		{
//			throw new CreditCardException("CreditCard number mismatch for updation");
//		}
		else 
		{
			creditCard =creditCardService.updateCreditCard(creditCard);
			response =new ResponseEntity<CreditCard>(creditCard,HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * This method retrieve all the creditcard's details from the findAll method in CreditCardService
	 * @return ResponseEntity which contains all the creditcard's details
	 */
	
	@GetMapping("/getAllCreditCards")
	public ResponseEntity<List<CreditCards>> findAll() {
		logger.info("Called GET mapping findAll() method");
		List<CreditCard> allCreditCard=creditCardService.findAll();
		List<CreditCards> allCreditCards=new ArrayList<CreditCards>();
		for(CreditCard c:allCreditCard)
			allCreditCards.add(new CreditCards(c));
		ResponseEntity<List<CreditCards>> response=new ResponseEntity<List<CreditCards>>(allCreditCards,HttpStatus.OK);
		return response;
	}
	
	/**
	 * This method retrieve all the creditcard's details from the findByCustomerId method in CreditCardService
	 * @param customerId for which creditcard details need to be fetched
	 * @return ResponseEntity which contains all the creditcard's details in list
	 * @throws CreditCardException when there is an exception
	 */
	
	@GetMapping("/getAllCreditCards/{customerId}")
	public ResponseEntity<List<CreditCards>> getAllCreditCardsOfCustomer(@PathVariable("customerId") String customerId) throws CreditCardException, CustomerNotFoundException{
		logger.info("Called GET mapping getAllCreditCardsOfCustomer() method");
		List<CreditCard> allCreditCard=creditCardService.findByCustomerId(customerId);
		List<CreditCards> allCreditCards=new ArrayList<CreditCards>();
		for(CreditCard c:allCreditCard)
			allCreditCards.add(new CreditCards(c));
		ResponseEntity<List<CreditCards>> response=new ResponseEntity<List<CreditCards>>(allCreditCards,HttpStatus.OK);
		return response;
	
	}
	
}
