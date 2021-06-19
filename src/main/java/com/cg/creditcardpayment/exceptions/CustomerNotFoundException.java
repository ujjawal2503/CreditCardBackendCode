package com.cg.creditcardpayment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerNotFoundException.class);
	
	String message;
	public CustomerNotFoundException(String message) {
		this.message=message;
		logger.info(message);
	}
	
	@Override
	public String toString() {
		return message;
	}
}
