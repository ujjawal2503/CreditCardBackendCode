//package com.cg.creditcardpayment.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
//import com.cg.creditcardpayment.dao.ICustomerRepository;
//import com.cg.creditcardpayment.entities.Customer;
//
//@SpringBootTest(classes = CreditcardBillPaymentApplication.class)
//public class CustomerServiceTest{
//	
//	@Autowired
//	private CustomerService cservice;
//	
//	@MockBean
//	private ICustomerRepository cRepo;
//	
//	@Test
//	public void testGetAllCustomers()
//	{
//		List<Customer> allCustomers=new ArrayList<Customer>();
//		
//		allCustomers.add(new Customer("2343","Vipul","vipul026@gmail.com","7885588552",LocalDate.parse("1998-08-09"),"A-233 Mayur Vihar Meerut"));
//		allCustomers.add(new Customer("4554","Mayank","mayank06@gmail.com","8995588552",LocalDate.parse("1998-11-19"),"D-289 Central Market Meerut"));
//		
//		when(cRepo.findAll()).thenReturn(allCustomers);
//		
//		assertEquals(2,cservice.getAllCustomers().size());
//	}
//	
//	@Test
//	public void testGetCustomer()
//	{	
//		Optional<Customer> customer1=Optional.of(new Customer("2334","Vipul","vipul027@gmail.com","896563235",LocalDate.parse("1998-09-17"),"A-233 Mayur Vihar Meerut"));
//		
//		when(cRepo.save(customer1.get())).thenReturn(customer1.get());
//		when(cRepo.findById(customer1.get().getUserId())).thenReturn(customer1);
//		
//		assertEquals(customer1.get().getName(),cservice.getCustomer("2334").getName());
//	}
//	
//	@Test
//	public void testUpdateCustomer()
//	{
//		Customer customer1=new Customer("2334","Vipul","vipul027@gmail.com","896563235",LocalDate.parse("1998-09-17"),"A-233 Mayur Vihar Meerut");
//		when(cRepo.save(customer1)).thenReturn(customer1);
//		
//		Optional<Customer> actual=Optional.of(cservice.addCustomer(customer1));
//		when(cRepo.findById(actual.get().getUserId())).thenReturn(actual);
//	}
//	
//	@Test
//	public void testAddCustomer()
//	{
//		Customer customer1=new Customer("2334","Vipul","vipul027@gmail.com","896563235",LocalDate.parse("1998-09-17"),"A-233 Mayur Vihar Meerut");
//		when(cRepo.save(customer1)).thenReturn(customer1);
//		
//		assertEquals(customer1.getUserId(),cservice.addCustomer(customer1).getUserId());
//	}
//	
//}