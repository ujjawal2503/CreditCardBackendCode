package com.cg.creditcardpayment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
import com.cg.creditcardpayment.dao.TransactionRepository;
import com.cg.creditcardpayment.entities.Transaction;

@SpringBootTest(classes = CreditcardBillPaymentApplication.class)
public class TransactionServiceTest {
	
	@Autowired
	private TransactionServices tservice;
	
	@MockBean
	private TransactionRepository tRepo;
	
	@Test
	public void testGetAllTransactions()
	{
		LocalTime transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		List<Transaction> allTransactions=new ArrayList<Transaction>();
		
		allTransactions.add(new Transaction(200l,"Unpaid",2000.00,LocalDate.now(),transactionTime));
		allTransactions.add(new Transaction(203l,"Unpaid",3000.00,LocalDate.now(),transactionTime));
		
		when(tRepo.findAll()).thenReturn(allTransactions);
		
		assertEquals(2,tservice.getAllTransactions().size());
	}
	
//	@Test
//	public void testGetTransactionById()
//	{	
//		LocalTime transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
//		Optional<Transaction> transaction1=Optional.of(new Transaction(2001l,"Unpaid",2000.00,LocalDate.now(),transactionTime));
//		
//		when(tRepo.save(transaction1.get())).thenReturn(transaction1.get());
//		when(tRepo.findById(transaction1.get().getTransactionId())).thenReturn(transaction1);
//		
//		assertEquals(transaction1.get().getStatus(),tservice.getTransactionDetailById(2001l).getStatus());
//	}
	
	@Test
	public void testRemoveTransactionById()
	{
		LocalTime transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		Transaction transaction1=new Transaction(2001l,"Unpaid",2000.00,LocalDate.now(),transactionTime);
		
		tservice.removeTransactionById(transaction1.getTransactionId());
		
		verify(tRepo,times(1)).deleteById(transaction1.getTransactionId());
	}
	
	@Test
	public void testUpdateCustomer()
	{
		LocalTime transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		Transaction transaction1=new Transaction(2001l,"Unpaid",2000.00,LocalDate.now(),transactionTime);
		
		when(tRepo.save(transaction1)).thenReturn(transaction1);
		
		LocalTime transactionTime2 = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		Optional<Transaction> actual=Optional.of(new Transaction(2001l,"Unpaid",2000.00,LocalDate.now(),transactionTime2));
		
		when(tRepo.findById(actual.get().getTransactionId())).thenReturn(actual);
	}
}
