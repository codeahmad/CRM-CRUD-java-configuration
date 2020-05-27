
package com.code.SpringMvcHibernateJavaCrm.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.SpringMvcHibernateJavaCrm.Entity.Customer;

@Repository
public class CustomerDaoImplementation implements CustomerDao {

	// Creating SessionFactor object
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Customer> findAll() {
		// Getting current session from factory using session object
		Session session = factory.getCurrentSession();

		// Creating query to get all items from database
		Query<Customer> query = session.createQuery("from Customer", Customer.class);

		// Getting result and adding to Customer array
		List<Customer> list = query.getResultList();
		return list;
	}

	@Override
	public void addCustomer(Customer customer) {
		// Getting current session from factory using session object
		Session session = factory.getCurrentSession();

		// Adding customer to database
		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteById(int id) {
		// Getting current session from factory using session object
		Session session = factory.getCurrentSession();

		// Creating query to delete customer using id
		// Update and delete can not be typed we do not use Customer.class here or cast
		// <Customer>
		Query query = session.createQuery("delete from Customer where id=:customerId");

		// Setting value of id
		query.setParameter("customerId", id);

		// Update changes
		query.executeUpdate();

	}

	@Override
	public Customer getCustomerById(int id) {
		// Getting current session from factory using session object
		Session session = factory.getCurrentSession();

		// Creating query to get customer
		Customer customer = session.get(Customer.class, id);

		// Return customer object
		return customer;
	}

}
