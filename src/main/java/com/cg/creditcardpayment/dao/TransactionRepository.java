package com.cg.creditcardpayment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	@Query("select t from Transaction t where t.creditCard.cardNumber =:cardNumber")
     public List<Transaction> transactionHistory(@Param(value="cardNumber") String cardNumber);
}
