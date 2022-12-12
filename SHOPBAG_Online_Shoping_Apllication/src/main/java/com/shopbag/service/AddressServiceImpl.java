package com.shopbag.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopbag.exception.AddressException;
import com.shopbag.model.Address;
import com.shopbag.model.Customer;
import com.shopbag.repository.AddressDao;
import com.shopbag.repository.CustomerRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CustomerRepo customerDao;

	@Override
	public Address addAddress(Address address) throws AddressException {

//		Optional<Address> newAddress = addressDao.findById(address.getAddressId());

		Address newAddress = addressDao.save(address);

//		if (!newAddress.isPresent()) {
//
//			throw new AddressException("Address not found for updation");
//
//		}

		return newAddress;
	}

	@Override
	public Address updateAddress(Address address) throws AddressException {
		Optional<Address> newAddress = addressDao.findById(address.getAddressId());

		if (!newAddress.isPresent()) {

			throw new AddressException("Address not found for updation");

		}

		return addressDao.save(address);
	}

	@Override
	public Address removeAddress(Integer address) throws AddressException {

		Optional<Address> ad = addressDao.findById(address);

		if (ad.isPresent()) {
			Address ad1=ad.get();
			addressDao.delete(ad1);
			
			return ad1;
		}

		throw new AddressException("address not found with id " + address);

	}

	@Override
	public List<Address> viewAllAddress() throws AddressException {
		List<Address> list = addressDao.findAll();

		if (list.size() == 0) {

			throw new AddressException("No Address found");
		}

		return list;
	}

	@Override
	public Address viewAddress(Integer customerId) throws AddressException {

		Address addressOpt = addressDao.findByCustomerId(customerId);

		if (addressOpt == null) {

			throw new AddressException("No customer found with id : " + customerId);
		}

		return addressOpt;
	}

}