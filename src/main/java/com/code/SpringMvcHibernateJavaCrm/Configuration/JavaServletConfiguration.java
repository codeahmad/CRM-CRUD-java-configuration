package com.code.SpringMvcHibernateJavaCrm.Configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.code.SpringMvcHibernateJavaCrm")
public class JavaServletConfiguration {

	// Adding view resolver
	@Bean
	public ViewResolver resolver() {

		// Creating instance of class view resolver class
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		// Setting properties
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		// Returning ViewResolver object
		return viewResolver;
	}

	// Adding dataSource
	@Bean
	public DataSource dataSource() {

		// Creating instance of class data source
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// Setting properties database connection properties
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/customer_database?useSSL=false&serverTimeZone=UTC");
		dataSource.setUser("crmDatabase");
		dataSource.setPassword("Root@2012");

		//
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		// Adding connection pool properties
		dataSource.setInitialPoolSize(5);
		dataSource.setMinPoolSize(5);
		dataSource.setMaxPoolSize(20);
		dataSource.setMaxIdleTime(3000);

		// Returning DataSource object
		return dataSource;
	}

	// Setting hibernate properties 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.show_sql", "true");

			}
		};
	}

	
	// Creating session factory
	@Bean
	public LocalSessionFactoryBean factory() {

		// Creating instance of session factory class
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		
		// Adding entity scan package
		factory.setPackagesToScan("com.code.SpringMvcHibernateJavaCrm.Entity");
		
		// Injecting data source
		factory.setDataSource(dataSource());
		
		// Injecting hibernate properties
		factory.setHibernateProperties(hibernateProperties());

		// Return session factory object
		return factory;
	}

	// Creating transaction manager
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory factory) {
		
		// Creating instance of transaction manager class
 		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
 		
 		// Injecting session factory
		transactionManager.setSessionFactory(factory);

		// Return transaction manager object
		return transactionManager;
	}

}
