package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.Account;
import com.cg.creditcardpayment.exceptions.AccountNotFoundException;

public interface IAccountService 
{
	public Account addAccount(Account account);
	
	public void removeAccount(Long id) throws AccountNotFoundException;
	
	public Account updateAccount(Long id, Account account) throws AccountNotFoundException ;
	
	public Account getAccount(Long id) throws AccountNotFoundException;
	
	public List<Account> getAllAccounts();
}
