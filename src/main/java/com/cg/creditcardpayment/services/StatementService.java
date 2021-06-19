package com.cg.creditcardpayment.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.CreditCardRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.dao.IStatementRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Statement;
import com.cg.creditcardpayment.entities.Transaction;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.StatementNotFoundException;

@Service
public class StatementService implements IStatementService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IStatementRepository sRepository;
	
	@Autowired
	private ICustomerRepository cRepository;
	
	@Autowired
	private CreditCardRepository creditRepo;
	
	//This method is used to add statement
	@Override
	public Statement addStatement(Statement statement) {
		logger.info("Called addStatement() method of StatementService");
		return sRepository.save(statement);
	}
	
	//This method is used to remove statement
//	@Override
//	public void removeStatement(Long sid) throws StatementNotFoundException{
//		logger.info("Called removeStatement() method of StatementService");
//		Optional<Statement> searchStatement=sRepository.findById(sid);
//		if(searchStatement.isPresent())
//		{
//			sRepository.deleteById(sid);
//		}
//		else {
//			throw new StatementNotFoundException(sid+" is not available!");
//		}
//	}
	@Override
	public Statement removeStatement(Long sid) throws StatementNotFoundException{
		logger.info("Called removeStatement() method of StatementService");
		Optional<Statement> searchStatement=sRepository.findById(sid);
		if(searchStatement.isPresent())
		{
			Statement statementToDelete=searchStatement.get();
			sRepository.delete(statementToDelete);
			return statementToDelete;
		}
		else {
			throw new StatementNotFoundException(sid+" is not available!");
		}
	}

	//This method is used to update statement
	@Override
	public Statement updateStatement(Long sid, Statement statement) throws StatementNotFoundException{
		logger.info("Called updateStatement() method of StatementService");
		Optional<Statement> searchStatement=sRepository.findById(sid);
		if(searchStatement.isPresent())
		{
			return sRepository.save(statement);
		}
		else 
		{
			throw new StatementNotFoundException("Statement with ID: "+sid+" Not Available!");
		}
	}

	//This method is used to fetch a statement using statementId
	@Override
	public Statement getStatement(Long sid) throws StatementNotFoundException{
		logger.info("Called getStatement() method of StatementService");
		Optional<Statement> searchStatement=sRepository.findById(sid);
		if(searchStatement.isPresent())
		{
			return sRepository.findById(sid).get();
		}
		else {
			throw new StatementNotFoundException("Statement with ID: "+sid+" Not Available!");
		}
	}



	//This method is used to fetch all the statements 
	@Override
	public List<Statement> getAllStatements() {
		logger.info("Called getAlltatements() method of StatementService");
		return sRepository.findAll();
	}

	//This method is used to fetch statements of a customer using customerId
	@Override
	public List<Statement> getStatementsByCustomerId(String customerId) throws CustomerNotFoundException{
		logger.info("Called getStatementsByCustomerId() method of StatementService");
		Optional<Customer> searchCustomer=cRepository.findById(customerId);
		if(searchCustomer.isPresent())
		{
			return sRepository.getStatementsByCustomerId(customerId);
		}
		else {
			throw new CustomerNotFoundException("Customer id "+customerId+" is Invalid!");
		}
	}
	
	//This method is used to fetch history of statements using cardNumber
	@Override
	public List<Statement> statementHistory(String cardNumber) throws CreditCardException {
	logger.info("Called statementHistory() method of StatementService");
	Optional<CreditCard> searchCard=creditRepo.findById(cardNumber);
	if(searchCard.isPresent())
	{
		return sRepository.statementHistory(cardNumber);
	}
	else
	throw  new CreditCardException("card number not found");
	}

	
	//This method is used to generate billed statement
	@Override
	public Statement getBilledStatement(String cardNumber) throws CreditCardException{
	    logger.info("Called getBilledStatement() method of StatementService");
		Statement bill=new Statement();
		if(cardNumber==null) {
			throw new CreditCardException("Card number cannot be null");
		}
		CreditCard card=creditRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("Credit card "+cardNumber+" Does not Exists");
		}
		if(card.getExpiryDate().isBefore(LocalDate.now())) {
			throw new CreditCardException("Credit Card is Expired, Please request new Credit Card");
		}
		bill.setStatementId(0L);
		LocalDate generalBillDate=LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 20);
		LocalDate lastBillDate;
		if(LocalDate.now().isAfter(generalBillDate))
		{
			lastBillDate=LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 20);
			bill.setBillingDate(lastBillDate);
		}
		else if(LocalDate.now().isBefore(generalBillDate))
		{
			lastBillDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().minusMonths(1).getMonthValue() ,20);
			bill.setBillingDate(lastBillDate);
		}
		else if(LocalDate.now().isEqual(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 20)))
		{
			bill.setBillingDate(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 20));
			
		}	
		bill.setCreditCard(card);
		bill.setDueDate(bill.getBillingDate().plusDays(20L));
		Statement billStatement;
		if(!bill.getBillingDate().isEqual(LocalDate.now())) {
			if(this.statementHistory(cardNumber).isEmpty()) {
				throw new StatementNotFoundException("No bill statements");
			}
			billStatement=this.statementHistory(cardNumber).stream().filter(st->st.getBillingDate().isEqual(bill.getBillingDate())).findFirst().orElse(null);
		}else {
			Set<Transaction> transaction =card.getTransaction();			
			Double amount=transaction.stream().filter(trans->trans.getTransactionDate().plusDays(30L).isAfter(LocalDate.now())).mapToDouble( amo -> amo.getAmount()).sum();
			bill.setDueAmount(amount);
			Double used=card.getUsedLimit();
			if(used<0) {
				if(amount+used>=0.0) {
					bill.setBillAmount(amount+used);
				}else {
					bill.setBillAmount(0.0);
				}
			}else {
				bill.setBillAmount(amount);
			}
			bill.setCustomer(card.getCustomer());
			billStatement = sRepository.save(bill);
		}
		return billStatement;
	}

	//This method is used to generate a temporary statement of unbilled
	@Override
	public Statement getUnbilledStatement(String cardNumber) throws CreditCardException {
		logger.info("Called getUnbilledStatement() method of StatementService");
		if(cardNumber==null) {
			throw new CreditCardException("Card Number cannot be Null");
		}
		CreditCard card=creditRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("Credit card "+cardNumber+" Does not Exists");
		}
		if(card.getExpiryDate().isBefore(LocalDate.now())) {
			throw new CreditCardException("Credit Card is Expired, Please request new Credit Card");
		}
		Statement unBill=new Statement();
		unBill.setStatementId(0L);
		unBill.setCreditCard(card);
		
		LocalDate generalBillDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 20);
		LocalDate lastBillDate = LocalDate.now();
		if(LocalDate.now().isAfter(generalBillDate)) {
			lastBillDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 20);
			unBill.setBillingDate(lastBillDate);
		}else if(LocalDate.now().isBefore(generalBillDate)) {
			lastBillDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().minusMonths(1).getMonthValue(), 20);
			unBill.setBillingDate(lastBillDate);
		}else if(LocalDate.now().isEqual(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 20))) {
			unBill.setBillingDate(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 20));
		}
		unBill.setBillingDate(unBill.getBillingDate().plusMonths(1));
		unBill.setDueDate(unBill.getBillingDate().plusDays(20L));
		CreditCard credit=creditRepo.findById(cardNumber).orElse(null);
		
		if(credit==null) {
			throw new CreditCardException("Credit card"+cardNumber+" does not Exists");
		}
		LocalDate bill = lastBillDate;
		Set<Transaction> transaction =credit.getTransaction();	
		Double amount=transaction.stream().filter(trans->trans.getTransactionDate().isAfter(bill)).mapToDouble(amo -> amo.getAmount()).sum();
		
		List<Payment> payments = credit.getPayments();
		Double payed=payments.stream().filter(pay->pay.getPaidDate().isAfter(bill)).mapToDouble(amo -> amo.getAmount()).sum();
		Double used=credit.getUsedLimit();
		if(used<0) {
			if(amount+used>=0.0) {
				unBill.setBillAmount(amount+used);
			}else {
				unBill.setBillAmount(0.0);
			}
		}else {
			unBill.setBillAmount(amount);
		}
		unBill.setDueAmount(unBill.getBillAmount()-payed);
		unBill=sRepository.save(unBill);
		sRepository.deleteById(unBill.getStatementId());
		return unBill;
	}

}