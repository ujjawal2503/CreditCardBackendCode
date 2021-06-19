package com.cg.creditcardpayment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entities.CreditCard;
import com.cg.creditcardpayment.exceptions.CreditCardException;
import com.cg.creditcardpayment.exceptions.CustomerNotFoundException;


@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard,String> {

	@Query("select c from CreditCard c where c.customer.username = :customerId")
	public List<CreditCard> findByCustomerId(@Param("customerId") String customerId) throws CreditCardException, CustomerNotFoundException;
}
