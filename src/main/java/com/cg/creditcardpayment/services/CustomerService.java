package com.cg.creditcardpayment.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;
import com.cg.creditcardpayment.exceptions.StatementNotFoundException;

@Service
public class CustomerService implements ICustomerService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICustomerRepository crepository;
	
	//This method is used to add customer 
	@Override
	public Customer addCustomer(Customer customer) 			
	{
		logger.info("Called addCustomer() method of CustomerService");
		Customer c1=crepository.save(customer);
		return c1;
	}
	
	//This method is used to remove customer
	@Override
	public Customer removeCustomer(String custId) throws CustomerNotFoundException 				
	{
		logger.info("Called removeCustomer() method of CustomerService");
		Optional<Customer> searchCustomer=crepository.findById(custId);
		if(searchCustomer.isPresent())
		{
			Customer customerToDelete=searchCustomer.get();
			crepository.delete(customerToDelete);
			return customerToDelete;
		}
		else
		{
			throw new CustomerNotFoundException("Customer with id "+custId+" not available");
		}
	}
	
	//This method is used to update the details of customer
	@Override
	public Customer updateCustomer(Customer customer,String custId) throws CustomerNotFoundException		
	{
		logger.info("Called updateCustomer() method of CustomerService");
		Optional<Customer> searchCustomer=crepository.findById(custId);
		if(searchCustomer.isPresent())
		{
			return crepository.save(customer);
		}
		else {
			throw new CustomerNotFoundException("Customer with id "+custId+" not available");
		}
	}
	
	//This method is used to fetch a single customer using customer id
	@Override
	public Customer getCustomer(String custId) throws CustomerNotFoundException 			
	{
		logger.info("Called getCustomer() method of CustomerService");
		Optional<Customer> searchCustomer=crepository.findById(custId);
		if(searchCustomer.isPresent())
		{
			return crepository.findById(custId).get();
		}
		else {
			throw new CustomerNotFoundException("Customer with ID: "+custId+" Not Available!");
		}
	}
	
	//This method is used to return list of all the customers
	@Override
	public List<Customer> getAllCustomers() 			
	{
		logger.info("Called getAllCustomer() method of CustomerService");
		List<Customer> allCustomers=crepository.findAll();
		return allCustomers;
	}

	@Override
	public boolean customerExistById(String custId) throws CustomerNotFoundException
	{
		if(custId==null)
			throw new CustomerNotFoundException("customer id cannot be null");
		return(crepository.existsById(custId));
			
	}
	
}
