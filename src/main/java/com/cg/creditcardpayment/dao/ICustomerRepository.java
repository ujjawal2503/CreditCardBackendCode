package com.cg.creditcardpayment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,String>{

	Optional<Customer> findByUsername(String username);

}