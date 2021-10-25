package com.tuandoan.controller;

import java.util.List;

import com.tuandoan.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuandoan.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> theILoveYous = customerService.getCustomers();
		//System.out.println("listCustomers())))))))))))");
		//System.out.println(theILoveYous);
				
		// add the customers to the model
		theModel.addAttribute("customers", theILoveYous);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theACustomer = new Customer();
		
		theModel.addAttribute("customer", theACustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theACustomer) {

		//System.out.println(theACustomer);
		// save the customer using our service
		customerService.saveCustomer(theACustomer);
		//System.out.println("save controllerrrrrrrrrrrr");
		//System.out.println(theACustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Customer theACustomer = customerService.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theACustomer);
		
		// send over to our form		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}

	@GetMapping("/runHibernate")
	public void runHibernate(){
		customerService.hibernate();
	}
}










