package goair.util;

import goair.model.flight.Flight;
import goair.model.query.AdminServiceQuery;

public class SearchUtil {
	
	public AdminServiceQuery adminServiceQuery = null;
	
	public SearchUtil()
	{
		adminServiceQuery = new AdminServiceQuery();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		
		
		return null;
	}
	
}
