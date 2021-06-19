package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.Statement;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.StatementNotFoundException;

public interface IStatementService {

	public Statement addStatement(Statement statement);
	public Statement removeStatement(Long sid) throws StatementNotFoundException;
	public Statement updateStatement(Long sid, Statement statement)  throws StatementNotFoundException;
	public Statement getStatement(Long sid) throws StatementNotFoundException;
	public List<Statement> getAllStatements();
	public List<Statement> getStatementsByCustomerId(String customerId) throws CustomerNotFoundException;
	public Statement getBilledStatement(String cardNumber)throws CreditCardException;
	public Statement getUnbilledStatement(String cardNumber) throws CreditCardException;
	public List<Statement> statementHistory(String cardNumber) throws CreditCardException;
}
