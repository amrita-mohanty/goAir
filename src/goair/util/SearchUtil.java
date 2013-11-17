package goair.util;

import goair.model.flight.Flight;
import goair.model.query.adminservices.AdminServiceQueries;

public class SearchUtil {
	
	public AdminServiceQueries adminServiceQuery = null;
	
	public SearchUtil()
	{
		adminServiceQuery = new AdminServiceQueries();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		return adminServiceQuery.searchFlightsForAdmin(searchParameters);
	}
	
}
