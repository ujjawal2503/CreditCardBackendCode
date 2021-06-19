package com.cg.creditcardpayment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
import com.cg.creditcardpayment.dao.CreditCardRepository;
import com.cg.creditcardpayment.entities.CreditCard;

@SpringBootTest(classes = CreditcardBillPaymentApplication.class)
public class CreditCardServiceTest {
	
	@Autowired 
	private CreditCardService cservice;
	
	@MockBean
	private CreditCardRepository cRepo;
	
	@Test
	public void testFindAll()
	{
		List<CreditCard> allCreditCards=new ArrayList<CreditCard>();
		
		allCreditCards.add(new CreditCard("214","Rupay","Gold",LocalDate.parse("2026-09-23"),"PNB",255,20000.00,10000.00));
		allCreditCards.add(new CreditCard("366","Mastercard","Platinum",LocalDate.parse("2027-10-24"),"SBI",285,30000.00,10000.00));
		
		when(cRepo.findAll()).thenReturn(allCreditCards);
		
		assertEquals(2,cservice.findAll().size());
	}
	
//	@Test
//	public void testGetCustomer()
//	{	
//		Optional<CreditCard> card1=Optional.of(new CreditCard("366","Mastercard","Platinum",LocalDate.parse("2027-10-24"),"SBI",285,30000.00,10000.00));
//		
//		when(cRepo.save(card1.get())).thenReturn(card1.get());
//		when(cRepo.findById(card1.get().getCardNumber())).thenReturn(card1);
//		
//		assertEquals(card1.get().getBankName(),cservice.findCreditCardById("366").getBankName());
//	}
	
	@Test
	public void testUpdateCreditCard()
	{
		CreditCard card1=new CreditCard("366","Mastercard","Platinum",LocalDate.parse("2027-10-24"),"SBI",285,30000.00,10000.00);
	
		when(cRepo.save(card1)).thenReturn(card1);
		
		Optional<CreditCard> actual=Optional.of(cservice.addCreditCard(card1));
		
		when(cRepo.findById(actual.get().getCardNumber())).thenReturn(actual);
	}
	
	@Test
	public void testAddCreditCard()
	{
		CreditCard card1=new CreditCard("366","Mastercard","Platinum",LocalDate.parse("2027-10-24"),"SBI",285,30000.00,10000.00);
	
		when(cRepo.save(card1)).thenReturn(card1);
		
		assertEquals(card1.getCardNumber(),cservice.addCreditCard(card1).getCardNumber());
	}
	
//	@Test
//	public void testDeleteCreditCardById()
//	{
//		CreditCard card1=new CreditCard("366","Mastercard","Platinum",LocalDate.parse("2027-10-24"),"SBI",285,30000.00,10000.00);
//		
//		cservice.deleteCreditCardById(card1.getCardNumber());
//		
//		verify(cRepo,times(1)).deleteById(card1.getCardNumber());
//	}
}
