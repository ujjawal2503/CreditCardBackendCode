package com.cg.creditcardpayment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.creditcardpayment.entities.*;

public interface IAdminRepository extends JpaRepository<Admin, String> {

	@Query("SELECT a FROM Admin a WHERE a.username = :username")
	Optional<Admin> findByUsername(@Param("username") String username);

}