package com.cg.creditcardpayment.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.creditcardpayment.entities.Account;
import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.exceptions.AccountNotFoundException;


@Service
public class AccountServices implements IAccountService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IAccountRepository arepository;

	@Override
	public Account addAccount(Account account) {
		logger.info("Called addAccount() method of AccountServices");
		Account acc=arepository.save(account);
		return acc; 
	}
	
	@Override
	public void removeAccount(Long accountId) throws AccountNotFoundException {
		logger.info("Called removeAccount() method of AccountServices");
		Optional<Account> searchAccount=arepository.findById(accountId);
		Account acc=searchAccount.get();
		if(searchAccount.isPresent())
		{     
			arepository.delete(acc);
		}
		else
		{	
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public Account updateAccount(Long accountId, Account account) throws AccountNotFoundException  {
		logger.info("Called updateAccount() method of AccountServices");
		Optional<Account> searchAccount=arepository.findById(accountId); 
		if(searchAccount.isPresent())
		{     
			return arepository.save(account);   
		}
		else
		{	
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public Account getAccount(Long id) throws AccountNotFoundException  {
		logger.info("Called getAccount() method of AccountServices");
		Optional<Account> searchAccount=arepository.findById(id); 
		if(searchAccount.isPresent())
		{     
			return arepository.findById(id).get();   
		}
		else
		{	
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		logger.info("Called getAllAccount() method of AccountServices");
		List<Account> allAccounts=arepository.findAll();
		return allAccounts;
	}

}
