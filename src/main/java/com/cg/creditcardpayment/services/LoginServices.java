package com.cg.creditcardpayment.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.LoginRepository;
import com.cg.creditcardpayment.entities.Login;

import com.cg.creditcardpayment.exceptions.LoginException;

@Service
public class LoginServices implements ILoginService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LoginRepository loginRepo;

	@Override
	public boolean existsById(String userId) {
		logger.info("Called existsById() method of LoginServices");
		return loginRepo.existsById(userId);
	}

	@Override
	public boolean signIn(Login user) throws LoginException {
		logger.info("Called signIn() method of LoginServices");
		if (user == null) {
			throw new LoginException("SignIn details Cannot be Null");
		}
		Optional<Login> userDetails = loginRepo.findById(user.getLoginId());
		if (userDetails.isEmpty()) {
			throw new LoginException("User Details does not Exists");
		}
		if (!userDetails.get().getRole().equals(user.getRole()))
			throw new LoginException("Role does not match");
		return userDetails.get().getPassword().equals(user.getPassword());
	}

	@Override
	public Login changePassword(Login login, String oldPassword, String newPassword) throws LoginException {
		logger.info("Called changePassword() method of LoginServices");
		Optional<Login> searchUserId = loginRepo.findById(login.getLoginId());

		if (searchUserId.isPresent()) {
			if (oldPassword.equals(login.getPassword())) {
				login.setPassword(newPassword);
				loginRepo.save(login);
			}
			return login;
		} else {
			throw new LoginException("User with ID " + login.getLoginId() + " not available");
		}
	}
}
