package com.cg.creditcardpayment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.dao.IStatementRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.Statement;
import com.cg.creditcardpayment.entities.Statements;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.StatementNotFoundException;
import com.cg.creditcardpayment.services.IStatementService;

/**
 * StatementController The StatementController program takes care of mapping the
 * url's to the functions which are specific to the Statement
 * 
 */
@RestController
@RequestMapping(value = "/api/statement")
@CrossOrigin(origins = "http://localhost:3000")
public class StatementController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * This a local variable: {@link #sService} is the object of IStatementService
	 * which is used to access the functions in StatementService
	 */
	@Autowired
	private IStatementService sService;
	@Autowired
	private IStatementRepository statementRepo;

	/**
	 * This method sends the new statement details to the add method in statament
	 * service
	 * 
	 * @param statement which contains all the statement details
	 * @return response is a ResponseEntity with HTTP status which contain the newly
	 *         added statement details
	 */

	@PostMapping
	public ResponseEntity<Statement> addStatement(@Valid @RequestBody Statement statement, BindingResult bindingResult)
			throws Exception, StatementNotFoundException {
		logger.info("Called POST mapping addStatement() method");
		if (bindingResult.hasErrors()) {
			throw new Exception("Statement information is not valid, please try again!");
		}
		if (statementRepo.existsById(statement.getStatementId())) {
			throw new StatementNotFoundException(
					"Statement with ID: " + statement.getStatementId() + " Already exists!");
		} else {
			Statement stmt = sService.addStatement(statement);
			ResponseEntity<Statement> rEntity = new ResponseEntity<Statement>(stmt, HttpStatus.CREATED);
			return rEntity;
		}
	}

	/**
	 * This method delete the statement details using statementId from statement
	 * service
	 * 
	 * @param sid which contains statement id details
	 * @return response is a ResponseEntity with HTTP status
	 */

	@DeleteMapping("/{sid}")
	public ResponseEntity<Statement> removeStatement(@PathVariable("sid") Long sid) throws StatementNotFoundException {
		logger.info("Called DELETE mapping removeStatement() method");

		return new ResponseEntity<Statement>(sService.removeStatement(sid), HttpStatus.OK);

	}

	/**
	 * This method update the customer details in customer service
	 * 
	 * @param sid       which contains the statementId
	 * @param statement which contains all the statement details need to be updated
	 * @return response is a ResponseEntity with HTTP status in String
	 */

	@PutMapping
	public ResponseEntity<Statement> updateStatement(@Valid @RequestBody Statement statement,
			BindingResult bindingResult) throws StatementNotFoundException, Exception {
		logger.info("Called PUT mapping updateStatement() method");
		if (bindingResult.hasErrors()) {
			throw new Exception("Statement information provided is not valid, please try again");
		}
		return new ResponseEntity<Statement>(sService.updateStatement(statement.getStatementId(), statement),
				HttpStatus.ACCEPTED);
	}

	/**
	 * This method retrieve the statement details of the give statementId
	 * 
	 * @param sid to find the statement details
	 * @return response is a ResponseEntity with HTTP status which contain the
	 *         statement details of the specific sid
	 */
	@GetMapping(value = "/getStatement/{sid}")
	public ResponseEntity<Statements> getStatement(@PathVariable("sid") Long sid) {
		logger.info("Called GET mapping getStatement() method");
		Statement stmt = sService.getStatement(sid);
		Statements stmt1=new Statements(stmt);
		
		ResponseEntity<Statements> rEntity = new ResponseEntity<Statements>(stmt1, HttpStatus.OK);
		return rEntity;
	}

	/**
	 * http://localhost:9090/api/statement/getAllStatements This method retrieve all
	 * the statement's details from the findAllStatements method in
	 * StatementServices
	 * 
	 * @return ResponseEntity which contains all the statement's details
	 */
	@GetMapping(value = "/getAllStatements")
	public ResponseEntity<List<Statements>> getAllStatements() {
		logger.info("Called GET mapping getAllStatements() method");
		List<Statement> allStatements = sService.getAllStatements();
		List<Statements> allStatements1=new ArrayList<Statements>();
		for(Statement s:allStatements)
			allStatements1.add(new Statements(s));
		ResponseEntity<List<Statements>> rEntity = new ResponseEntity<List<Statements>>(allStatements1, HttpStatus.OK);
		return rEntity;
	}


	/**
	 * This method retrieve the statement of the given customerId
	 * 
	 * @param customerId to find the statement details
	 * @return response is a ResponseEntity with HTTP status which contain the
	 *         statement details of the specific customerId
	 * @throws Exception when there is an exception
	 */
	@GetMapping(value = "/getStatementsByCustomerId/{customerId}")
	public ResponseEntity<List<Statements>> getStatementsByCustomerId(@PathVariable("customerId") String customerId)
			throws CustomerNotFoundException {
		logger.info("Called GET mapping getStatementsByCustomerId() method");
		List<Statement> customerStatements = sService.getStatementsByCustomerId(customerId);
		List<Statements> customerStatements1=new ArrayList<Statements>();
		for(Statement s:customerStatements)
			customerStatements1.add(new Statements(s));
		ResponseEntity<List<Statements>> rEntity = new ResponseEntity<List<Statements>>(customerStatements1, HttpStatus.OK);
		return rEntity;
	}

	/**
	 * This method retrieve all the statement's details from the statementHistory
	 * method in StatementService
	 * 
	 * @param cardNumber for which statement details need to be fetched
	 * @return ResponseEntity which contains all the statement's details in list
	 * @throws CreditCardException when there is an exception
	 */

	@PostMapping(value = "/statementHistory")
	public ResponseEntity<List<Statements>> statementHistory(@RequestBody CreditCard card) {
		logger.info("Called GET mapping statementHistory() method");
		List<Statement> historyStatements = sService.statementHistory(card.getCardNumber());
		List<Statements> historyStatements1=new ArrayList<Statements>();
		for(Statement s:historyStatements)
			historyStatements1.add(new Statements(s));
		ResponseEntity<List<Statements>> rEntity = new ResponseEntity<List<Statements>>(historyStatements1, HttpStatus.OK);
		return rEntity;
	}

	/**
	 * This method will generate a billed statement using getBilledStatement method
	 * in StatementService
	 * 
	 * @param cardNumber for which statement details need to be fetched
	 * @return ResponseEntity which contains statement is generated in billed
	 *         statement
	 */

	@PostMapping(value = "/getBilledStatement")
	public ResponseEntity<Statements> getBilledStatement(@RequestBody CreditCard card) {
		logger.info("Called GET mapping getBilledStatement() method");
		Statement stmt = sService.getBilledStatement(card.getCardNumber());
		Statements stmt1=new Statements(stmt);
		
		ResponseEntity<Statements> rEntity = new ResponseEntity<Statements>(stmt1, HttpStatus.OK);
		return rEntity;
	}

	/**
	 * This method will generate a temporary unbilled statement using
	 * getUnbilledStatement method in StatementService
	 * 
	 * @param cardNumber for which statement details need to be fetched
	 * @return ResponseEntity which contains statement is generated in unbilled
	 *         statement
	 */

	@PostMapping(value = "/getUnbilledStatement")
	public ResponseEntity<Statements> getUnbilledStatement(@RequestBody CreditCard card) {
		logger.info("Called GET mapping getUnbilledStatement() method");
		Statement stmt = sService.getUnbilledStatement(card.getCardNumber());
		Statements stmt1=new Statements(stmt);
		
		ResponseEntity<Statements> rEntity = new ResponseEntity<Statements>(stmt1, HttpStatus.OK);
		return rEntity;
	}
}