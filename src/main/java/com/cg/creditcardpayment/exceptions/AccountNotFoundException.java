package com.cg.creditcardpayment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountNotFoundException extends RuntimeException {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountNotFoundException.class);
	private static final long serialVersionUID = 1L;
	String msg;

	public AccountNotFoundException(String msg) {
		this.msg = msg;
		logger.info(msg);
	}

	@Override
	public String toString() {
		return msg;
	}
}
