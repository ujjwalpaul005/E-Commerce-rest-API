package com.shopbag.service;

import com.shopbag.exception.LoginException;
import com.shopbag.model.LoginDTO;

public interface LoginService {

	String loginIntoAccount(LoginDTO dto) throws LoginException;
	
	String logoutFromAccount(String key) throws LoginException;
	
}
