package com.cg.creditcardpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entities.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
}
