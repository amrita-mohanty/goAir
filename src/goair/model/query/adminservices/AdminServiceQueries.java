package goair.model.query.adminservices;

import java.sql.Connection;

import org.apache.log4j.Logger;

import goair.model.flight.Flight;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

public class AdminServiceQueries 
{
	public static Logger logger = Logger.getLogger(AdminServiceQueries.class);
	
	DbConnection dbConnection 	= null;
	Connection connection 		= null;
	
	// Query classes
	SearchFlightsForAdminQuery searchFlightsForAdmin 		= null;
	SearchAllFlightsForAdminQuery searchAllFlightsForAdmin 	= null;
	AddFlightQuery addFlightQuery 							= null;
	EditFlightQuery editFlightQuery 					    = null;
	DeleteFlightQuery deleteFlightQuery 					= null;
	
	public AdminServiceQueries()
	{
		dbConnection 				= new DbConnection();
		connection 					= dbConnection.createDbConnection();
		searchFlightsForAdmin 		= new SearchFlightsForAdminQuery();
		searchAllFlightsForAdmin 	= new SearchAllFlightsForAdminQuery();
		addFlightQuery 				= new AddFlightQuery();
		editFlightQuery				= new EditFlightQuery();
		deleteFlightQuery			= new DeleteFlightQuery();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return searchFlightsForAdmin.searchFlightsForAdmin(searchParameters, connection);
	}

	/**
	 * Get all the flights in the system
	 * @return Flight[] - Return list of all the flights
	 */
	public Flight[] getAllFlightsForAdmin()
	{
		logger.info("searchAllFlightsForAdmin");
		return searchAllFlightsForAdmin.searchFlightsForAdmin(connection);
	}
	
	/**
	 * Add a new flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addFlight(Flight flight)
	{
		logger.info("Add a flight : "+flight.toString());
		return addFlightQuery.addNewFlight(flight, connection);
	}
	
	/**
	 * Edit flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editFlight(Flight flight)
	{
		logger.info("Edit a flight : "+flight.toString());
		return editFlightQuery.editFlight(flight, connection);
	}
	
	/**
	 * Delete flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int deleteFlight(Flight flight)
	{
		logger.info("Delete a flight : "+flight.toString());
		return deleteFlightQuery.deleteFlight(flight, connection);
	}
}
