package com.cg.creditcardpayment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.CreditCardRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.dao.TransactionRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Transaction;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.TransactionException;

@Service
public class TransactionServices  implements ITransactionService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	TransactionRepository trepository;
	@Autowired
	ICustomerRepository crepository;
	@Autowired
	CreditCardRepository ccrepository;
	

	@Override
	public Transaction addTransaction(Transaction transaction) //This method is used to add the transaction
	{
		logger.info("Called addTransaction() method of TransactionServices");
		Transaction t1=trepository.save(transaction);
		return t1;
	}

	@Override
	public Transaction removeTransactionById(Long transactionId)//This method is used to remove the transaction
	{
		logger.info("Called removeTransactionById() method of TransactionServices");
		Transaction transaction=getTransactionDetailById(transactionId);
		trepository.deleteById(transactionId);
		return transaction;
		
		
	}

	@Override
	public Transaction updateTransaction(Transaction transaction,Long transactionId) //This method is used to update the transaction
	{
		logger.info("Called updateTransaction() method of TransactionServices");
		Optional<Transaction> searchTransaction=trepository.findById(transactionId);
		if(searchTransaction.isPresent())
			return trepository.save(transaction);
		else
			throw new TransactionException("Transaction with Id "+transactionId+" does not exist");
	}

   @Override
	public Transaction getTransactionDetailById(Long transactionId) throws TransactionException //This method is used to fetch transaction by using transaction id
   {
	   logger.info("Called getTransactionDetailById() method of TransactionServices");
		if(transactionId==null) 
			throw new TransactionException("transaction Id cannot be Null");
		else if(!trepository.existsById(transactionId)) 
			throw new TransactionException("Transaction with Transaction Id "+transactionId+" Does not Exists");
		
		return (trepository.findById(transactionId).orElse(null));
		
	}

	@Override
	public List<Transaction> getAllTransactions()//This method is used to get all transaction.
	{
		logger.info("Called getAllTransactions() method of TransactionServices");
		List<Transaction> allTransaction=trepository.findAll();
		return allTransaction;
	}
	
	@Override
	public List<Transaction> getAllCustomerTransactions(Iterable<String> custId)//This method is used to fetch the transaction list using customer id.
	{
		logger.info("Called getAllCustomerTransaction() method of TransactionServices");
		List<Transaction> allTransaction=new ArrayList<Transaction>();
		for(String s:custId)
			trepository.findById(Long.parseLong(s)).ifPresent(allTransaction::add);
		
		return allTransaction;
		
	}

	@Override
	public boolean existsById(Long transactionId) throws TransactionException//This method check whether the transaction exist or not using Id.
	{
		logger.info("Called existsById() method of TransactionServices");
		if(transactionId==null) 
			throw new TransactionException("transaction Id can't be Null");
		return trepository.existsById(transactionId);
	}


	@Override
	public List<Transaction> transactionHistory(String cardNumber) throws CreditCardException//This method is used to fetch the transaction using creditcard number
	{
		logger.info("Called transactionHistory() method of TransactionServices");
		Optional<CreditCard> searchCard=ccrepository.findById(cardNumber);
		if(searchCard.isPresent())
			return trepository.transactionHistory(cardNumber);
		else
			throw  new CreditCardException("card number not found");
	
	}
	

}
