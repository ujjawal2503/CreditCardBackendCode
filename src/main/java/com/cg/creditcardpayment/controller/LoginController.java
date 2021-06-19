package com.cg.creditcardpayment.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.entities.Login;
import com.cg.creditcardpayment.exceptions.LoginException;
import com.cg.creditcardpayment.services.LoginServices;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * This a local variable: {@link #loginservice} is the object of LoginService which is used to access the functions in LoginService
	 */
	@Autowired
	private LoginServices loginService;
    
	/**
	 * This method sends the new login details to the signIn method in login service and verification is done
	 * @param user which contains all the login details
	 * @return response is a ResponseEntity with HTTP status
	 * @throws LoginException when there are exception
	 */
	
	@PostMapping("/signIn")
	public ResponseEntity<String> signIn(@RequestBody @Valid Login user) throws LoginException 
	{   logger.info("Called POST mapping signIn() method");

		ResponseEntity<String> response = null;

		if (loginService.existsById(user.getLoginId())) 
		{
			if (loginService.signIn(user)) 
				response = new ResponseEntity<>("Signed In " + user.getLoginId(), HttpStatus.ACCEPTED);
		   else 
				response = new ResponseEntity<>("Login Id and password did not match", HttpStatus.UNAUTHORIZED);
		}
		else 
			response = new ResponseEntity<>("User with " + user.getLoginId() + " Does not exists",HttpStatus.NOT_FOUND);
		      
		return response;
	}
	/**
	 * This method is used to change the password
	 * @param oldPassword which store the old password of the login details
	 * @param newPassword which store the new password of the login details
	 * @return response is a ResponseEntity with HTTP status 
	 */
	@PutMapping(value = "/changePassword/{oldPassword}&{newPassword}")
	public ResponseEntity<String> changePassword(@RequestBody Login login,@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)throws LoginException 
	{  logger.info("Called PUT mapping changePassword() method");

		loginService.changePassword(login, oldPassword, newPassword);
		ResponseEntity<String> entity = new ResponseEntity<String>("Password of ID " + login.getLoginId() + " updated successfully", HttpStatus.OK);
		return entity;
	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody Login userdetails){
//		Object obj = null;
//		switch (userdetails.getRole()) {
//		case "admin":
//			obj = loginService.signIn(userdetails.getLoginId(), userdetails.getPassword());
//			break;
//		case "customer":
//			obj = loginService.loginCustomer(userdetails.getUsername(), userdetails.getPassword());
//			break;
//		default:
//			return "Wrong choice entered!";
//		}
//		if (obj != null)
//			return "Login successfull";
//		else
//			return "Id or password is incorrect";
//	}


}
