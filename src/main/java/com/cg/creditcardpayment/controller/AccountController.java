package com.cg.creditcardpayment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.entities.Account;
import com.cg.creditcardpayment.services.AccountServices;

/**
 * AccountController
 * The AccountController program takes care of mapping
 * the url's to the functions which are specific to the Account
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * This a local variable: {@link #accountService} is the object of AccountService which is used to access the functions in AccountServices 
	 */
	@Autowired
	private AccountServices accountService;
	@Autowired
	private IAccountRepository accountRepo;
   
	/**
	 * This method sends the new account details to the add method in AccountService
	 * @param account which contains all the account details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added account details
	 */

    @PostMapping(value = "/addAccount")
	public ResponseEntity<String> addAccount(@RequestBody Account account) {
		logger.info("Called POST mapping addAccount() method");
		
		if(accountRepo.existsById(account.getAccountNumber()))
		{
			ResponseEntity<String> rentity = new ResponseEntity<String>(
					"Account With ID :" + account.getAccountNumber() + " already exist", HttpStatus.OK);
			return rentity;
		}
		Account acc = accountService.addAccount(account);
		ResponseEntity<String> rentity = new ResponseEntity<String>(
				"Account With ID :" + acc.getAccountNumber() + " Created Successfully!", HttpStatus.CREATED);
		return rentity;
	}
    
    /**
	 * This method retrieve the account details of the given account number
	 * @param  accountId to find the account details
	 * @return response is a ResponseEntity with HTTP status which contain the account details of the specific accountId
	 */

	@GetMapping(value = "/getAccount/{accountId}")
	public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
		logger.info("Called GET mapping getAccount() method");
		Account acc = accountService.getAccount(accountId);
		ResponseEntity<Account> rentity = new ResponseEntity<Account>(acc, HttpStatus.OK);
		return rentity;
	}
    
	 /**
	 * This method delete the account details of the given account number
	 * @param  accountId to find the account details
     * @return ResponseEntity with HTTP status
	 */
	
	@DeleteMapping(value = "/deleteAccount/{id}")
	public ResponseEntity<String> removeAccount(@PathVariable Long id) {
		logger.info("Called DELETE mapping removeAccount() method");
		Account acc = accountService.getAccount(id);
		accountService.removeAccount(acc.getAccountNumber());
		ResponseEntity<String> rentity = new ResponseEntity<String>(
				"Account With Given ID :" + id + " Deleted Succesfully", HttpStatus.OK);
		return rentity;
	}

	/**
	 * This method retrieve all the details of accounts from the findAllAccount method in AccountService
	 * @return ResponseEntity which contains all the details of accounts
	 */
	
	@GetMapping(value = "/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		logger.info("Called GET mapping getAllAccounts() method");
		List<Account> allAccounts = accountService.getAllAccounts();
		ResponseEntity<List<Account>> rentity = new ResponseEntity<List<Account>>(allAccounts, HttpStatus.OK);
		return rentity;
	}
    
	/**
	 * This method update the account details of the given account number
	 * @param  accountId to find the account details
     * @return ResponseEntity with HTTP status
	 */
		
	@PutMapping(value = "/updateAccount/{id}")
	public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody Account account) {

		logger.info("Called PUT mapping updateAccount() method");
		Account acc = accountService.updateAccount(id, account);

		ResponseEntity<String> rentity = new ResponseEntity<String>(
				"Account With ID :" + acc.getAccountNumber() + " Updated Successfully!", HttpStatus.OK);

		return rentity;
	}
}
