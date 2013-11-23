package goair.model.query;

import goair.model.flight.Flight;
import goair.model.query.adminservices.SearchFlightsForAdminQuery;
import goair.model.query.customerservices.SearchFlightsForCustomerQuery;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class CustomerServiceQueries {
public static Logger logger = Logger.getLogger(AdminServiceQueries.class);
	
	DbConnection dbConnection 	= null;
	Connection connection 		= null;
	
	// Query classes
	SearchFlightsForCustomerQuery searchFlightsForCustomer = null;
	
	public CustomerServiceQueries(){
		dbConnection 				= new DbConnection();
		connection 					= dbConnection.createDbConnection();
	}
	
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		if(searchFlightsForCustomer == null)
		{
			searchFlightsForCustomer = new SearchFlightsForCustomerQuery();
		}
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return searchFlightsForCustomer.searchFlightsForCustomer(searchParameters, connection);
	}
	
	
	
	
}//class
