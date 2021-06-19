package com.cg.creditcardpayment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.entities.Account;

@SpringBootTest(classes = CreditcardBillPaymentApplication.class)
public class AccountServiceTest 
{
	@Autowired
	private AccountServices aservice;

	@MockBean                                                                      
	private IAccountRepository arepository;
	
	
	@Test
	public void testGetAllAccounts() {
		Account a1 = new Account(1L, "Dattatreya",123.45,"Saving Account");
		Account a2 = new Account(2L, "vipul",141.45,"Current Account");
		List<Account> allAccounts = new ArrayList<>();

		allAccounts.add(a1);
		allAccounts.add(a2);
	
		when(arepository.findAll()).thenReturn(allAccounts);

		assertEquals(2, aservice.getAllAccounts().size());

	}
	
	
	@Test
	public void testGetOneAccount() {
		
		Optional<Account> a1 = Optional.of(new Account(1L, "Dattatreya",123.45,"Saving Account"));

		when(arepository.save(a1.get())).thenReturn(a1.get());
		when(arepository.findById(a1.get().getAccountNumber())).thenReturn(a1);

		assertEquals(a1.get().getAccountName(), aservice.getAccount(1l).getAccountName());

	}
	
	@Test
	public void testSaveEmployee() {
		Account a1 = new Account(1L, "Dattatreya",123.45,"Saving Account");
		when(arepository.save(a1)).thenReturn(a1);

		assertEquals(a1.getAccountNumber(), aservice.addAccount(a1).getAccountNumber());
		assertEquals(a1.getAccountName(), aservice.addAccount(a1).getAccountName());

	}
	
	
	@Test
	public void testUpdateAccount()
	{   
		Account a1 = new Account(1L, "Dattatreya",123.45,"Saving Account");
		when(arepository.save(a1)).thenReturn(a1);
		
		Optional<Account> actual=Optional.of(aservice.addAccount(a1));
		when(arepository.findById(actual.get().getAccountNumber())).thenReturn(actual);
	}
	
//	@Test
//	public void testRemoveAccount()
//	{
//		Account a1 = new Account(1L, "Dattatreya",123.45,"Saving Account");
//		
//		aservice.removeAccount(a1.getAccountNumber());
//		
//		verify(arepository,times(1)).deleteById(a1.getAccountNumber());
//	} 
	

	
}
