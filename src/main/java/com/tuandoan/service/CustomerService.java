package com.tuandoan.service;

import java.util.List;

import com.tuandoan.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theACustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public void hibernate();
	
}
