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

import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.PaymentException;
import com.cg.creditcardpayment.exceptions.StatementException;
import com.cg.creditcardpayment.exceptions.TransactionException;
import com.cg.creditcardpayment.dao.PaymentRepository;
import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.entities.CreditCards;
import com.cg.creditcardpayment.entities.Payment;
import com.cg.creditcardpayment.entities.Payments;
import com.cg.creditcardpayment.entities.Statement;
import com.cg.creditcardpayment.entities.Statements;
import com.cg.creditcardpayment.entities.Transaction;
import com.cg.creditcardpayment.services.PaymentServices;
/**
 * PaymentController
 * The PaymentController program takes care of mapping
 * the url's to the functions which are specific to the Payment
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * This a local variable: {@link #paymentservice} is the object of PaymentServices which is used to access the functions in PaymentServices
	 */
	@Autowired
	private PaymentServices paymentService;
	@Autowired
    private PaymentRepository paymentRepo;
	/**
	 * This method sends the new payment details to the add method in payment service
	 * @param customer which contains all the customer details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added customer details
	 * @throws PaymentException when there is an exception
	 */
	
	@PostMapping("/addPayment")
	public ResponseEntity<Payment> add(@Valid @RequestBody Payment payment,BindingResult bindingResult) throws PaymentException {
		
		logger.info("Called POST mapping add() method");
		if(bindingResult.hasErrors())
		{
			throw new PaymentException("Payment information not valid");
		}
		if(paymentRepo.existsById(payment.getPaymentId()))
		{
			throw new PaymentException(	"Payment with ID: " + payment.getPaymentId() + " already exists!");
				
		}
		else
		{
			Payment payment1=paymentService.addPayment(payment);
			ResponseEntity<Payment> rEntity = new ResponseEntity<Payment>(payment1, HttpStatus.OK);
			return rEntity;
		}
	}
	
	/**
	 * This method update the payment details in payment service
	 * @param payment which contains all the payment details need to be updated
	 * @return response is a ResponseEntity with HTTP status which contain the newly updated payment details
	 * @throws paymentException when there is an exception
	 */
	@PutMapping("/updatePayment")
	public ResponseEntity<Payment> updatePayment(@RequestBody @Valid Payment payment,BindingResult bindingResult) throws PaymentException{
		logger.info("Called PUT mapping updateCreditCard() method");
		
		if(bindingResult.hasErrors())
		{
			throw new PaymentException("Payment information not valid");
		}
			
		Payment payment1=paymentService.updatePayment(payment);
		ResponseEntity<Payment> rEntity = new ResponseEntity<Payment>(payment1, HttpStatus.OK);
		return rEntity;
	}
	/**
	 * This method retrieve all the payment's details from the findAllPayments method in PaymentServices
	 * @return ResponseEntity which contains all the payment's details
	 */
	
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<Payments>> findAll() {
		
		logger.info("Called GET mapping getAllPayments() method");
		List<Payment> allPayments = paymentService.findAllPayments();
		List<Payments> allPayments1=new ArrayList<Payments>();
		for(Payment payment:allPayments)
			allPayments1.add(new Payments(payment));
		ResponseEntity<List<Payments>> entity = new ResponseEntity<List<Payments>>(allPayments1, HttpStatus.OK);
		return entity;
	}
    
	/**
	 * This method retrieve the payment details of the given paymentId
	 * @param  paymentId to find the payment details
	 * @return response is a ResponseEntity with HTTP status which contain the payment details of the specific paymentId
	 * @throws PaymentException when there is an exception
	 */
	
	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<Payments> findById(@PathVariable("paymentId") Long paymentId) throws PaymentException {
		
		logger.info("Called GET mapping findById() method");
		Payment payment = paymentService.findPaymentById(paymentId);
		Payments payment1=new Payments(payment);
		ResponseEntity<Payments> rEntity = new ResponseEntity<Payments>(payment1, HttpStatus.OK);
		return rEntity;
	}
	
	/**
	 * This method retrieve the pending bills of the given cardNumber
	 * @param  cardNumber to find the pending bills details
	 * @return response is a ResponseEntity with HTTP status which contain the pending details list statement of the specific cardNumber
	 * @throws CreditCardException when there is an exception
	 */
	
	@GetMapping("/pendingBills/{cardNumber}")
	public ResponseEntity<List<Statements>> getPendingBillStatements(@PathVariable("cardNumber") String cardNumber)throws CreditCardException {
		
		logger.info("Called GET mapping getPendingBillStatements() method");
		List<Statement> allStatement=paymentService.pendingBills(cardNumber);
		List<Statements> allStatements=new ArrayList<Statements>();
		for(Statement c:allStatement)
			allStatements.add(new Statements(c));
		
		
		ResponseEntity<List<Statements>> entity=new ResponseEntity<List<Statements>>(allStatements,HttpStatus.OK);
		return entity;
	}
	@DeleteMapping(value = "/removePayment/{paymentId}")
	public ResponseEntity<Payment> removePayment(@PathVariable long paymentId)  throws PaymentException{
		logger.info("Called DELETE mapping removePayment() method");
	  if(!paymentService.existsById(paymentId))
	  {
			throw new PaymentException("Payment id " + paymentId + "does not  exists");
	  }
		    
		ResponseEntity<Payment> entity = new ResponseEntity<Payment>(paymentService.deletePaymentById(paymentId), HttpStatus.OK);
		return entity;
	}
	/**
	 * This method retrieve the pay bills of the given cardNumber
	 * @param  cardNumber to find the pending bills details
	 * @return response is a ResponseEntity with HTTP status which contain the payment details  of the specific cardNumber
	 * @throws Exception when there is an exception
	 */
	
	@PostMapping("/pay/{cardNumber}")
	public ResponseEntity<Payments> paybill(@Valid @RequestBody Payment pay, @PathVariable("cardNumber") String cardNumber)throws PaymentException, CreditCardException, StatementException {
		logger.info("Called POST mapping paybill() method");
		Payment payment=paymentService.payForCreditCard(pay, cardNumber);
		Payments payment1=new Payments(payment);
		ResponseEntity<Payments> entity=new ResponseEntity<Payments>(payment1,HttpStatus.OK);
		return entity;
	}
    
	/**
	 * This method retrieve all the payment's details from the paymentHistory method in PaymentService
	 * @param cardNumber for which payment details need to be fetched
	 * @return ResponseEntity which contains all the payment's details in list
	 * @throws CreditCardException when there is an exception
	 */
	
	@GetMapping("/paymentHistory/{cardNumber}")
	public ResponseEntity<List<Payments>> paymentHistory(@PathVariable("cardNumber") String cardNumber)throws CreditCardException {
		
		logger.info("Called GET mapping paymentHistory() method");
		List<Payment> historyOfPayments = paymentService.paymentHistory(cardNumber);
		List<Payments> historyOfPayments1=new ArrayList<Payments>();
		for(Payment payment:historyOfPayments)
			historyOfPayments1.add(new Payments(payment));
		ResponseEntity<List<Payments>> entity = new ResponseEntity<List<Payments>>(historyOfPayments1, HttpStatus.OK);
		return entity;
	}

}
