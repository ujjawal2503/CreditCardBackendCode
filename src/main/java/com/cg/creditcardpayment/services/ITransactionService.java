package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.Transaction;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.TransactionException;


public interface ITransactionService {
	
	boolean existsById(Long transactionId) throws TransactionException;
	public Transaction addTransaction(Transaction transaction) throws TransactionException;
	public Transaction removeTransactionById(Long transactionId) throws TransactionException;
	public Transaction updateTransaction(Transaction transaction,Long transactionId) throws TransactionException;
	public Transaction getTransactionDetailById(Long transactionId)throws TransactionException;
	public List<Transaction> getAllTransactions(); 
	public List<Transaction> getAllCustomerTransactions(Iterable<String> custId);
	public List<Transaction> transactionHistory(String cardNumber) throws CreditCardException;
}
