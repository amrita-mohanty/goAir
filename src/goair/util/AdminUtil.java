package goair.util;

import org.apache.log4j.Logger;

import goair.model.flight.Flight;
import goair.model.query.adminservices.AdminServiceQueries;

public class AdminUtil {
	
	public static Logger logger = Logger.getLogger(AdminUtil.class);
	
	public AdminServiceQueries adminServiceQuery = null;
	
	public AdminUtil()
	{
		adminServiceQuery = new AdminServiceQueries();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return adminServiceQuery.searchFlightsForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] getAllFlightsForAdmin()
	{
		logger.info("searchAllFlightsForAdmin");
		return adminServiceQuery.getAllFlightsForAdmin();
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
		return adminServiceQuery.addFlight(flight);
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
		return adminServiceQuery.editFlight(flight);
	}
}
