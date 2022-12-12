package com.shopbag.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopbag.exception.LoginException;
import com.shopbag.model.CurrentUserSession;
import com.shopbag.model.Customer;
import com.shopbag.model.LoginDTO;
import com.shopbag.repository.CurrentUserSessionRepo;
import com.shopbag.repository.CustomerRepo;

import net.bytebuddy.utility.RandomString;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentUserSessionRepo sessionRepo;
	
	@Override
	public String loginIntoAccount(LoginDTO dto) throws LoginException {
		
		Customer existingCustomer = customerRepo.findByEmail(dto.getEmail());

		if(existingCustomer == null){
			throw new LoginException("Please Enter a valid mobile number");
		}

		Optional<CurrentUserSession> validCustomerSessionOpt = sessionRepo.findById(existingCustomer.getCustomerId());

		if(validCustomerSessionOpt.isPresent()){
			throw new LoginException("User already Logged In with this number");
		}
		if(existingCustomer.getPassword().equals(dto.getPassword())){

			String key = RandomString.make(6);

			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(), key, LocalDateTime.now());

			sessionRepo.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
		throw new LoginException("Please Enter a valid password");

	}


	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		CurrentUserSession validCustomerUserSession = sessionRepo.findByUuid(key);

		if(validCustomerUserSession == null){

			throw new LoginException("User Not Logged In with this number");

		}

		sessionRepo.delete(validCustomerUserSession);

		return "Logged out!";

	}

	
	
}
