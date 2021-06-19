package com.cg.creditcardpayment.dao;


import com.cg.creditcardpayment.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long>{

}
