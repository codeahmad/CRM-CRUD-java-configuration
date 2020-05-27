
package com.code.SpringMvcHibernateJavaCrm.Service;

import java.util.List;

import com.code.SpringMvcHibernateJavaCrm.Entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public void addCustomer(Customer customer);

	public void deleteById(int id);

	public Customer getCustomerById(int id);
}
