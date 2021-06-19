package com.cg.creditcardpayment.services;

import com.cg.creditcardpayment.entities.Login;
import com.cg.creditcardpayment.exceptions.LoginException;

public interface ILoginService {

	boolean existsById(String userId);
	
	boolean signIn(Login user) throws LoginException;
	
	Login changePassword(Login login,String oldPassord,String newPassword) throws LoginException;
}
