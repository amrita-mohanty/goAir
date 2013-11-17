package goair.model.query.adminservices;

import java.sql.Connection;

import goair.model.flight.Flight;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

public class AdminServiceQueries 
{
	DbConnection dbConnection = null;
	Connection connection = null;
	
	// Query classes
	SearchFlightsForAdminQuery searchFlightsForAdmin = null;
	
	public AdminServiceQueries()
	{
		dbConnection = new DbConnection();
		connection = dbConnection.createDbConnection();
		searchFlightsForAdmin = new SearchFlightsForAdminQuery();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		return searchFlightsForAdmin.searchFlightsForAdmin(searchParameters, connection);
	}

}
