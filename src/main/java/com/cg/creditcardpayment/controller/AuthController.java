package com.cg.creditcardpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.entities.Login;
import com.cg.creditcardpayment.exceptions.LoginException;
import com.cg.creditcardpayment.request.*;
import com.cg.creditcardpayment.services.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	public AuthService authService;
 
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest userdetails){
		Object obj = null;
		switch (userdetails.getRole()) {
		case "admin":
			obj = authService.loginAdmin(userdetails.getUsername(), userdetails.getPassword());
			break;
		case "customer":
			obj = authService.loginCustomer(userdetails.getUsername(), userdetails.getPassword());
			break;
		default:
			return "Wrong role entered!";
		}
		if (obj != null)
			return "Login successfull";
		else
			return "Id or password is incorrect";
	}
}