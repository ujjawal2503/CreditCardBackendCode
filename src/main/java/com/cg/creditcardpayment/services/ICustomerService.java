package com.cg.creditcardpayment.services;

import java.util.List;

import com.cg.creditcardpayment.entities.Customer;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;

public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(String custId) throws CustomerNotFoundException;
	public Customer updateCustomer(Customer customer,String custId) throws CustomerNotFoundException;
	public Customer getCustomer(String custId) throws CustomerNotFoundException;
	public List<Customer> getAllCustomers();
	public boolean customerExistById(String custId);
}
