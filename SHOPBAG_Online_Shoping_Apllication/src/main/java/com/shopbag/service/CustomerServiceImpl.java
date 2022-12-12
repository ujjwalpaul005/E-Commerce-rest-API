package com.shopbag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopbag.exception.CustomerException;
import com.shopbag.model.Address;
import com.shopbag.model.CurrentUserSession;
import com.shopbag.model.Customer;
import com.shopbag.repository.AddressDao;
import com.shopbag.repository.CurrentUserSessionRepo;
import com.shopbag.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AddressDao addressRepo;
	
	@Autowired
	private CurrentUserSessionRepo sessionRepo;
	
	
	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		
		Optional<Customer> optional = customerRepo.findById(cust.getCustomerId());
		
		if(optional.isPresent()) {
			throw new CustomerException("Customer already exists...");
		}
		
		return customerRepo.save(cust);
		
	}
	

	@Override
	public Customer updateCustomer(Customer cust, String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please provide a valid key to update customer!");
		
		
		if(cust.getCustomerId() == loggedInUser.getUserId()) {
			return customerRepo.save(cust);
		}
		else {
			throw new CustomerException("Invalid customer details, plaease login first!");
		}
		
	}
	

	@Override
	public Customer removeCustomer(Integer custId, String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please provide a valid key to update customer!");
		
		Optional<Customer> customerOpt = customerRepo.findById(custId);
		
		
		if(custId == loggedInUser.getUserId()) {
			
			customerRepo.delete(customerOpt.get());
			
			return customerOpt.get();
		}
		else {
			throw new CustomerException("Invalid customer details, plaease login first!");
		}
					
	}
	

	@Override
	public Customer viewCustomer(Integer custId) throws CustomerException {
		
		Optional<Customer> custOpt = customerRepo.findById(custId);
		
		if(custOpt.isPresent())
			return custOpt.get();
		
		else
			throw new CustomerException("Customer not found with this customerId "+custId);
	}

	@Override
	public List<Customer> viewAllCustomer(String location) throws CustomerException {
		
		List<Customer> customers = customerRepo.viewAllCustomer(location);
		
		if(customers.isEmpty())
			throw new CustomerException("Customer not found with this location: "+location);
		
		else {
			List<Customer> customerList = new ArrayList<>(customers);
			
			return customerList;
		}
	}

	
	
}
