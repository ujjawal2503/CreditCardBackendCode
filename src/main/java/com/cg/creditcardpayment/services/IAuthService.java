package com.cg.creditcardpayment.services;

import com.cg.creditcardpayment.entities.*;

public interface IAuthService {

	Admin loginAdmin(String username,String password);
	 Customer loginCustomer(String username,String password);
}