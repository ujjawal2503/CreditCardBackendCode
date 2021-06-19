package com.cg.creditcardpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.creditcardpayment.entities.Login;

public interface LoginRepository  extends JpaRepository<Login, String>{

}
