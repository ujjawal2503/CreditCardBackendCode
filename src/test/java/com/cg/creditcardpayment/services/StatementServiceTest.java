//package com.cg.creditcardpayment.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.cg.creditcardpayment.CreditcardBillPaymentApplication;
//import com.cg.creditcardpayment.dao.IStatementRepository;
//import com.cg.creditcardpayment.entities.CreditCard;
//import com.cg.creditcardpayment.entities.Customer;
//import com.cg.creditcardpayment.entities.Statement;
//
//@SpringBootTest(classes = CreditcardBillPaymentApplication.class)
//public class StatementServiceTest {
//
//	@Autowired
//	private StatementService sService;
//	
//	@MockBean
//	private IStatementRepository sRepo;
//	
//	private Statement stmt=null;
//	private Statement stmt2=null;
//	
//	@BeforeEach
//	public void testBeforeEach() {
//		Customer c=new Customer("1001","Rahul","akavarapurahul@gmail.com","7995268208",LocalDate.parse("1998-10-31"),"Hyderabad");
//		CreditCard creditCard=new CreditCard("123456789000","Rupay","Gold",LocalDate.parse("2026-10-10"),"SBI",111,40000.0,1000.0,c);
//		
//		stmt=new Statement(1L,3000.00,10000.00,LocalDate.parse("2021-05-17"),LocalDate.parse("2021-05-26"),c,creditCard);
//		stmt2=new Statement(2L,4000.00,15000.00,LocalDate.parse("2021-05-17"),LocalDate.parse("2021-05-26"),c,creditCard);
//	}
//	
//	@Test
//	public void testAddStatement()
//	{
//		when(sRepo.save(stmt)).thenReturn(stmt);
//		Statement actualStatement=sService.addStatement(stmt);
//		
//		assertEquals(stmt.getStatementId(),actualStatement.getStatementId());
//		assertNotNull(actualStatement);
//		assertNotNull(stmt);
//	}
//	
//	@Test
//	public void testUpdateStatement() {
//		when(sRepo.save(stmt)).thenReturn(stmt);
//		Optional<Statement> actualStatement = Optional.of(sService.addStatement(stmt));
//		when(sRepo.findById(actualStatement.get().getStatementId())).thenReturn(actualStatement);
//		Statement stmt1 = sService.updateStatement(actualStatement.get().getStatementId(),actualStatement.get());
//
//		assertEquals(actualStatement.get().getBillAmount(), stmt1.getBillAmount());
//
//	}
//	
////	@Test
////	public void testRemoveStatement() {
//////		when(sRepo.save(stmt)).thenReturn(stmt);
////		sService.removeStatement(stmt.getStatementId());
////		
////		verify(sRepo, times(1)).deleteById(stmt.getStatementId());;
////	}
//	
//	@Test
//	public void testGetStatement()
//	{
//		Optional<Statement> actualStatement = Optional.of(stmt);
//		
//		when(sRepo.save(actualStatement.get())).thenReturn(actualStatement.get());
//		when(sRepo.findById(actualStatement.get().getStatementId())).thenReturn(actualStatement);
//		
//		assertEquals(actualStatement.get().getDueDate(), sService.getStatement(1L).getDueDate());
//	}
//	
//	@Test
//	public void testGetAllStatements()
//	{
//		List<Statement> allStatements=new ArrayList<Statement>();
//		allStatements.add(stmt);
//		allStatements.add(stmt2);
//		
//		when(sRepo.findAll()).thenReturn(allStatements);
//		
//		assertEquals(2, sService.getAllStatements().size());
//		
//	}
//	
//	@Test
//	public void testGetStatementsById()
//	{
//		List<Statement> allStatements=new ArrayList<Statement>();
//		allStatements.add(stmt);
//		allStatements.add(stmt2);
//		
//		when(sRepo.getStatementsByCustomerId("1001")).thenReturn(allStatements);
//		
//		assertEquals(2, sService.getStatementsByCustomerId("1001").size());
//		
//	}
//	
//	@Test
//	public void testStatementHistory()
//	{
//		List<Statement> allStatements=new ArrayList<Statement>();
//		allStatements.add(stmt);
//		allStatements.add(stmt2);
//		
//		when(sRepo.statementHistory("123456789000")).thenReturn(allStatements);
//		
//		assertEquals(2, sService.statementHistory("123456789000").size());
//		
//	}
//	
//	
//	@AfterEach
//	public void testAfterEach()
//	{
//		stmt=null;
//		stmt2=null;
//	}
//	
//}
