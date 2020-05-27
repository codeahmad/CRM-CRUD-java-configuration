
package com.code.SpringMvcHibernateJavaCrm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.SpringMvcHibernateJavaCrm.Dao.CustomerDao;
import com.code.SpringMvcHibernateJavaCrm.Entity.Customer;

@Service
public class CustomerServiceImplementation implements CustomerService {

	// Creating dao object
	@Autowired
	private CustomerDao dao;

	// Using @Transactional to handle starting and committing transaction
	@Override
	@Transactional
	public List<Customer> findAll() {
		
		// Getting result from dao
		return dao.findAll();
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {

		// Calling dao go get addMethod
		dao.addCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		// Calling dao for deletemethod
		dao.deleteById(id);

	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {

		// Calling dao forgetCustomer method
		return dao.getCustomerById(id);
	}

}
