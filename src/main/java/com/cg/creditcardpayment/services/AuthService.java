package com.cg.creditcardpayment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.entities.*;
import com.cg.creditcardpayment.services.IAuthService;
import com.cg.creditcardpayment.dao.*;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private IAdminRepository adminRepository; 

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Admin loginAdmin(String username, String password) {

		Optional<Admin> admin = adminRepository.findByUsername(username);
		if (!(admin.isEmpty()) && (admin.get().getPassword().equals(password))) {
			System.out.println("Welcome Admin " + admin.get().getUsername() + "! Sign In Successful");
			return admin.get();
		}
		return null;
	}

	@Override
	public Customer loginCustomer(String username, String password) {

		Optional<Customer> customer = customerRepository.findByUsername(username);
		if (!(customer.isEmpty()) && (customer.get().getPassword().equals(password))) {
			System.out.println("Welcome Admin " + customer.get().getUsername() + "! Sign In Successful");
			return customer.get();
		}
		return null;

	}

}