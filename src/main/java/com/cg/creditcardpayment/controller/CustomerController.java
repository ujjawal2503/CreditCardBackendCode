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

import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.entities.Customers;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.services.ICustomerService;

/**
 * CustomerController
 * The CustomerController program takes care of mapping
 * the url's to the functions which are specific to the Customer
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * This a local variable: {@link #cservice} is the object of ICustomerService which is used to access the functions in CustomerService
	 */
	@Autowired
	private ICustomerService cservice;
	
	/**
	 * This method sends the new customer details to the add method in customer service
	 * @param customer which contains all the customer details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added customer details
	 */
	
	@PostMapping(value="/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer,BindingResult bindingResult)
	{
		logger.info("Called POST mapping addCustomer() method");
		if(cservice.customerExistById(customer.getUsername()))
		{
//			ResponseEntity<String> entity=new ResponseEntity<String>("Customer with id "+customer.getUserId()+" already exists",HttpStatus.OK);
			throw new CustomerNotFoundException("Customer with given id "+customer.getUsername()+" already exists!");
		}
		
		if(bindingResult.hasErrors())
		{
			throw new CustomerNotFoundException("Customer information provided is not valid, please try again!");
		}
			//Customer customer1=cservice.addCustomer(customer);
			//ResponseEntity<String> entity=new ResponseEntity<String>("Customer with id "+customer1.getUserId()+" is created",HttpStatus.CREATED);

			ResponseEntity<Customer> entity=new ResponseEntity<Customer>(cservice.addCustomer(customer),HttpStatus.OK);
			return entity;
	}
	
	/**
	 * This method retrieve the customer details of the given customerId
	 * @param  custId to find the customer details
	 * @return response is a ResponseEntity with HTTP status which contain the credit details of the specific customerId
	 * @throws CustomerNotFoundException when there is an exception
	 */
	
	@GetMapping(value="/getCustomer/{custId}")
	public ResponseEntity<Customers> getCustomer(@PathVariable String custId) throws CustomerNotFoundException
	{
		logger.info("Called GET mapping getCustomer() method");
		Customer customer1=cservice.getCustomer(custId);
		Customers customer2=new Customers(customer1);
		
		ResponseEntity<Customers> entity=new ResponseEntity<Customers>(customer2,HttpStatus.OK);
		return entity;
	}
	
	/**
	 * This method delete the customer details using customerId from customer service
	 * @param custId which contains all the customer details
	 * @return response is a ResponseEntity with HTTP status
	 * @throws CustomerNotFoundException when there is an exception
	 */
	
	@DeleteMapping(value="/removeCustomer/{custId}")
	public ResponseEntity<Customer> removeCustomer(@PathVariable String custId) throws CustomerNotFoundException
	{
		logger.info("Called DELETE mapping removeCustomer() method");
		Customer customer1=cservice.getCustomer(custId);
		ResponseEntity<Customer> entity=new ResponseEntity<Customer>(cservice.removeCustomer(customer1.getUsername()),HttpStatus.OK);
		return entity;
	}
	
	/**
	 * This method update the customer details in customer service
	 * @param custId which contains the customerId
	 * @param customer which contains all the customer details need to be updated
	 * @return response is a ResponseEntity with HTTP status which contain the newly updated customer details
	 * @throws CustomerNotFoundException when there is an exception
	 */
	
	@PutMapping(value="/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,BindingResult bindingResult) throws CustomerNotFoundException
	{
		logger.info("Called PUT mapping updateCustomer() method");
		if(bindingResult.hasErrors())
		{
			throw new CustomerNotFoundException("Customer information is not valid, please try again!");
		}
//		Customer customer1=cservice.updateCustomer(customer, customer.getUserId());
//		ResponseEntity<String> entity=new ResponseEntity<String>("Customer with id "+customer1.getUserId()+" updated successfully",HttpStatus.OK);
		
		ResponseEntity<Customer> entity=new ResponseEntity<Customer>(cservice.updateCustomer(customer, customer.getUsername()),HttpStatus.OK);

		return entity;
	}
	
	/**
	 * This method retrieve all the customer's details from the getAllCustomer method in CustomerService
	 * @return ResponseEntity which contains all the customer's details
	 */
	
	@GetMapping(value="/getAllCustomers")
	public ResponseEntity<List<Customers>> getAllCustomers()
	{
		logger.info("Called GET mapping getAllCustomers() method");
		List<Customer> allCustomers=cservice.getAllCustomers();
		List<Customers> allCustomers2=new ArrayList<Customers>();
		for(Customer customer:allCustomers)
			allCustomers2.add(new Customers(customer));
		ResponseEntity<List<Customers>> entity=new ResponseEntity<List<Customers>>(allCustomers2,HttpStatus.OK);
		return entity;
	}
}
