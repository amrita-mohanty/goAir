package goair.model.query;

import goair.model.flight.Flight;
import goair.model.query.customerservices.CustomerLoginQuery;
import goair.model.query.customerservices.SearchFlightsForCustomerQuery;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;
import goair.model.customer.Customer;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class CustomerServiceQueries {
public static Logger logger = Logger.getLogger(AdminServiceQueries.class);
	
	Connection connection 		= null;
	
	// Query classes
	SearchFlightsForCustomerQuery searchFlightsForCustomer = null;
	CustomerLoginQuery customerLogin = null;
	
	public CustomerServiceQueries(){
		connection 					= DbConnection.getInstance().getConnection();
	}
	
	/*
	 * This method will register a new customer by inserting
	 * the details of the new Customer in the DB.
	 */
	
	
	
	
	/*
	 * This method Check whether a customer is valid or not.
	 * If valid, then return the details of the customer set in the Customer bean.
	 */
	public Customer validateCustomerLogin(String emailId,String password)
	{
		if(customerLogin == null)
		{
			customerLogin = new CustomerLoginQuery();
		}
		logger.info("validateCustomerLogin with params : "+ emailId + ", " + password);
		return customerLogin.validateCustomerLogin(emailId, password, connection);
	}
	
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForCustomer(SearchParametersForFlights searchParameters)
	{
		if(searchFlightsForCustomer == null)
		{
			searchFlightsForCustomer = new SearchFlightsForCustomerQuery();
		}
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return searchFlightsForCustomer.searchFlightsForCustomer(searchParameters, connection);
	}
	
	
	
	
	
	
}//class
