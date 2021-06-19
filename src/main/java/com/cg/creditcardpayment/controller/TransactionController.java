package com.cg.creditcardpayment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import com.cg.creditcardpayment.dao.TransactionRepository;
import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Transaction;
import com.cg.creditcardpayment.entities.Transactions;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.PaymentException;
import com.cg.creditcardpayment.exceptions.TransactionException;
import com.cg.creditcardpayment.services.TransactionServices;
/**
 * TransactionController
 * The TransactionController program takes care of mapping
 * the url's to the functions which are specific to the Transaction
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * This a local variable: {@link #tservice} is the object of TransactionServices which is used to access the functions in TransactionService
	 */
	@Autowired
	private TransactionServices tservices;
	@Autowired
	private TransactionRepository transactionRepo;
	/**
	 * This method sends the new transaction details to the add method in transaction service
	 * @param transaction which contains all the transaction details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added transaction details
	 */
	@PostMapping(value = "/addTransaction")
	public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction transaction , BindingResult bindingResult) throws TransactionException{
		logger.info("Called POST mapping addTransaction() method");
		if (bindingResult.hasErrors()) {
			throw new TransactionException("Transaction information is not valid, please try again!");
		}
		
		if(transactionRepo.existsById(transaction.getTransactionId()))
		{
		throw new TransactionException("Transaction with id " + transaction.getTransactionId() + "already exists");
		
		}
		else {
		Transaction transaction1 = tservices.addTransaction(transaction);
		ResponseEntity<Transaction> entity = new ResponseEntity<Transaction>(transaction1,HttpStatus.OK);
		return entity;}
	}
    
	/**
	 * This method delete the transaction details using transactionId from transaction service
	 * @param transactionId which contains transaction id details
	 * @return response is a ResponseEntity with HTTP status
	 *  @throws TransactionException when there is an exception
	 */
	@DeleteMapping(value = "/removeTransaction/{transactionId}")
	public ResponseEntity<Transaction> removeTransaction(@PathVariable long transactionId) throws TransactionException {
		logger.info("Called DELETE mapping removeTransaction() method");
	  if(!tservices.existsById(transactionId))
	  {
			throw new TransactionException("Transaction with id " + transactionId + "does not  exists");
	  }
		    
		ResponseEntity<Transaction> entity = new ResponseEntity<Transaction>(tservices.removeTransactionById(transactionId), HttpStatus.OK);
		return entity;
	}
	
	/**
	 * This method retrieve the transaction details of the given transactiontId
	 * @param  transactionId to find the transaction details
	 * @return response is a ResponseEntity with HTTP status which contain the transaction details of the specific transactionId
	 * @throws CreditCardException when there is an exception
	 */
	@GetMapping(value = "/getTransaction/{transactionId}")
	public ResponseEntity<Transactions> transaction(@PathVariable Long transactionId)
			throws CreditCardException {
		logger.info("Called GET mapping getTransactionDetailById() method");
		Transaction transaction = tservices.getTransactionDetailById(transactionId);
		Transactions transaction1=new Transactions(transaction);
		ResponseEntity<Transactions> entity = new ResponseEntity<Transactions>(transaction1,
				HttpStatus.OK);
		return entity;
	}
	/**
	 * This method retrieve all the transaction's details from the findAllTransactions method in TransactionServices
	 * @return ResponseEntity which contains all the transaction's details
	 */
	@GetMapping(value = "/getAllTransaction")
	public ResponseEntity<List<Transactions>> getAllTransactions() {
		logger.info("Called GET mapping getAllTransactions() method");
		List<Transaction> allTransaction = tservices.getAllTransactions();
		List<Transactions> allTransactions=new ArrayList<Transactions>();
		for(Transaction t:allTransaction)
		{
			allTransactions.add(new Transactions(t));
		}
		ResponseEntity<List<Transactions>> entity = new ResponseEntity<List<Transactions>>(allTransactions,
				HttpStatus.OK);
		return entity;
	}
	/**
	 * This method retrieve all the transaction's details from the transactionHistory method in TransactionService
	 * @param cardNumber for which transaction details need to be fetched
	 * @return ResponseEntity which contains all the transaction's details in list
	 * @throws CreditCardException when there is an exception
	 */
	@GetMapping(value = "/getHistoryOfTransaction/{cardNumber}")
	public ResponseEntity<List<Transactions>> transactionHistory(@PathVariable String cardNumber)
			throws CreditCardException {
		logger.info("Called GET mapping transactionHistory() method");
		List<Transaction> historyTransaction = tservices.transactionHistory(cardNumber);
		List<Transactions> allTransactions=new ArrayList<Transactions>();
		for(Transaction t:historyTransaction)
		{
			allTransactions.add(new Transactions(t));
		}
		ResponseEntity<List<Transactions>> entity = new ResponseEntity<List<Transactions>>(allTransactions,
				HttpStatus.OK);
		return entity;
	}
	@PutMapping("/updateTransaction")
	public ResponseEntity<Transaction> updatePayment(@RequestBody @Valid Transaction transaction, BindingResult bindingResult) throws TransactionException{
		logger.info("Called PUT mapping updateTransaction() method");
		if (bindingResult.hasErrors()) {
			throw new TransactionException("Transaction information is not valid, please try again!");
		}
		
		
		ResponseEntity<Transaction> response=null;
		if(transaction==null) 
			throw new TransactionException("Transaction information is null, please try again!");
		else 
		{
		
			response =new ResponseEntity<Transaction>(tservices.updateTransaction(transaction, transaction.getTransactionId()),HttpStatus.OK);
		}
		return response;
	}

}
