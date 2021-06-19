package com.cg.creditcardpayment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CreditCardException.class);
	
	String msg;
	
	public CreditCardException(String msg) {
		this.msg=msg;
		logger.info(msg);
	}
	
	@Override
	public String toString() {
		return msg;
	}

}
