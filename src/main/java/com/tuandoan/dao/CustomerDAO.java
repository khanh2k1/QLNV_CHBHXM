package com.tuandoan.dao;

import java.util.List;

import com.tuandoan.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theACustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public void hibernate();
}
