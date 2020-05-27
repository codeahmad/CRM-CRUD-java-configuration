
package com.code.SpringMvcHibernateJavaCrm.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.SpringMvcHibernateJavaCrm.Entity.Customer;
import com.code.SpringMvcHibernateJavaCrm.Service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Pre processor for trimming leading and trailing white spaces
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor ste= new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, ste);
	}
	
	
	
	// Creating service object
	@Autowired
	private CustomerService service;

	@GetMapping("/list")
	public String showList(Model model) {

		// Creating list of customer and adding result form service
		List<Customer> list = service.findAll();

		// Adding customer list to model
		model.addAttribute("customerModel", list);

		// Returning the view name
		return "customer-list";
	}

	// Adding new customer
	@GetMapping("/addCustomerForm")
	public String addCustomer(Model model) {

		// Creating customer object and pass it to model
		Customer theCustomer = new Customer();
		model.addAttribute("customerModel", theCustomer);

		// Returning the view name
		return "add-customer-form";
	}

	@RequestMapping("/saveCustomer")
	public String saveCustomer(
			@Valid @ModelAttribute("customerModel") Customer customer, BindingResult bindingResult) {
			
		if(bindingResult.hasErrors()) {
			return "add-customer-form";
		}else {
	
		// Call add method from service and add modelAttribute coming from the form
		service.addCustomer(customer);

		// Redirect to list of customer
		return "redirect:/customer/list";
	}}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {

		// Calling service to delete Customer
		service.deleteById(id);

		return "redirect:/customer/list";
	}

	@GetMapping("/editCustomer")
	public String update(@RequestParam("customerId") int id, Model model) {
		
		Customer customer = service.getCustomerById(id);

		model.addAttribute("customerModel", customer);

		return "add-customer-form";
	}

}
