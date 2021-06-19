package com.cg.creditcardpayment.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.creditcardpayment.exceptions.AccountNotFoundException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.StatementNotFoundException;
import com.cg.creditcardpayment.exceptions.PaymentException;
import com.cg.creditcardpayment.exceptions.StatementException;
import com.cg.creditcardpayment.exceptions.TransactionException;
import com.cg.creditcardpayment.exceptions.LoginException;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

	/**
	 * This function handles exception related to Statement
	 * @param excep which is object of StatementNotFoundException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(StatementNotFoundException.class)
	public ResponseEntity<String> handleStatementNotFoundException(StatementNotFoundException snfe)
	{
		ResponseEntity<String> rEntity=new ResponseEntity<String>(snfe.toString(),HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	/**
	 * This function handles exception related to Customer
	 * @param excep which is object of CustomerException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException cnfe)
	{
		ResponseEntity<String> rEntity=new ResponseEntity<String>(cnfe.toString(),HttpStatus.NOT_FOUND);
		return rEntity;
	}
	/**
	 * This function handles exception related to login
	 * @param excep which is object of LoginException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<String> handleLoginExceptionAction(LoginException excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function handles exception related to Payment
	 * @param excep which is object of PaymentException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<String> handlePaymentExceptionAction(PaymentException excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function handles exception related to Statement
	 * @param excep which is object of StatementException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(StatementException.class)
	public ResponseEntity<String> handleStatementExceptionAction(StatementException excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function handles exception related to Transaction
	 * @param excep which is object of TransactionException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<String> handleTransactionExceptionAction(TransactionException excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function handles exception related to Account
	 * @param excep which is object of AccountException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundExceptionAction(AccountNotFoundException excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function handles all the defined exception
	 * @param excep which is object of Exception
	 * @return ResponseEntity with HTTP status code INETRNAL_sERVER_ERROR
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptionAction(Exception excep) {
		ResponseEntity<String> entity=new ResponseEntity<String>(excep.toString(),HttpStatus.NOT_FOUND);
		return entity;
	}
	/**
	 * This function binds all the errors 
	 * @param result which contains all the error
	 * @return all the errors in the string
	 */
	public static String messageFrom(BindingResult result) {		
		return result.getAllErrors().stream()
				.map(err -> err.getObjectName() + "-"+err.getDefaultMessage())
				.collect(Collectors.toList()).toString();
	}
	
}
