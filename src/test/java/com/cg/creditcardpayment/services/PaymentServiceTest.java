package com.cg.creditcardpayment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
import com.cg.creditcardpayment.dao.PaymentRepository;
import com.cg.creditcardpayment.entities.Account;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Statement;

@SpringBootTest(classes=CreditcardBillPaymentApplication.class)
public class PaymentServiceTest {
	@MockBean
	private PaymentRepository paymentRepo;
	
	@Autowired
	private PaymentServices service;

	@Test
	@DisplayName("PaymentServiceImplTest :: All Payment Details should retrive")
	void testGetAll() {
		CreditCard creditCard1=new CreditCard("2568479632140","VISA","GOLD",LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new Customer());
		
		List<Payment> testData=Arrays.asList(new Payment[] {
				new Payment(1L,"UPI",LocalDate.now(),LocalTime.now(),6000.0,creditCard1),
				new Payment(2L,"UPI",LocalDate.now(),LocalTime.now(),7000.0,creditCard1)
		});
		
		when(paymentRepo.findAll()).thenReturn(testData);
		
		assertEquals(2,service.findAllPayments().size());

	}
   
	
	@Test
	public void testAddPayment() {
		CreditCard creditCard1=new CreditCard("2568479632140","VISA","GOLD",LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new Customer());
		Payment payment = new Payment(1L,"UPI",LocalDate.now(),LocalTime.now(),6000.0,creditCard1);
		when(paymentRepo.save(payment)).thenReturn(payment);

		assertEquals(payment.getAmount() , service.addPayment(payment).getAmount());
		assertEquals(payment.getAmount() , service.addPayment(payment).getAmount());

	}
	@Test
	public void testGetAllPayments() {
		CreditCard creditCard1=new CreditCard("2568479632140","VISA","GOLD",LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new Customer());
		Payment payment = new Payment(1L,"UPI",LocalDate.now(),LocalTime.now(),6000.0,creditCard1);
		CreditCard creditCard2=new CreditCard("2568479632000","RUPAY","SILVER",LocalDate.parse("2023-10-18"),"PNB",624,10000.0,10000.0,new Customer());
		Payment payment2 = new Payment(2L,"NETBANKING",LocalDate.now(),LocalTime.now(),8000.0,creditCard2);
		
		List<Payment> allPayments = new ArrayList<>();

		allPayments.add(payment);
		allPayments.add(payment2);
	
		when(paymentRepo.findAll()).thenReturn(allPayments);

		assertEquals(2, service.findAllPayments().size());

	}
	@Test
	public void testUpdatePayment()
	{   
		CreditCard creditCard1=new CreditCard("2568479632140","VISA","GOLD",LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new Customer());
		Payment payment = new Payment(1L,"UPI",LocalDate.now(),LocalTime.now(),6000.0,creditCard1);
		when(paymentRepo.save(payment)).thenReturn(payment);
		
		Optional<Payment> actual=Optional.of(service.addPayment(payment));
		when(paymentRepo.findById(actual.get().getPaymentId())).thenReturn(actual);
	}
	

	
	
}
