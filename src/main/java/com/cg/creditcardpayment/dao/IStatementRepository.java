package com.cg.creditcardpayment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entities.Statement;

@Repository
public interface IStatementRepository extends JpaRepository<Statement, Long>{

	@Query("select s from Statement s where s.customer.username = :customerId")
	public List<Statement> getStatementsByCustomerId(@Param(value = "customerId") String customerId);
	
	@Query("select s from Statement s where s.creditCard.cardNumber = :cardNumber")
    public List<Statement> statementHistory(@Param(value="cardNumber") String cardNumber);
}
