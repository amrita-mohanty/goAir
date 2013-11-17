package goair.model.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

public class AdminServiceQuery 
{
	DbConnection dbConnection = null;
	Connection connection = null;
	
	public AdminServiceQuery()
	{
		dbConnection = new DbConnection();
		connection = dbConnection.createDbConnection();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		List<Flight> flights = new ArrayList<Flight>();
		
		// Using SearchParametersForFlights create the query 
		//	that will search the flights.
		
		String query = "select flightid, flightname, airlinename, source, "
				+ "destination, departuretime, arrivaltime, totalseats, "
				+ "seatsreserved, daysofweek, flyingstartdate,"
				+ "flyingenddate "
				+ "from flight ";
		
		System.out.println("Get all the flights : " + query);
		
		 ResultSet resultSet = null;  
	     Statement statement = null;
	     
	     Flight flight = null;
	     
	     String flightId;
	     String flightName;
	     String airlineName;
	     String source;
	     String destination;
	     Long departureTime;
	     Long arrivalTime;
	     String status;
	     int totalSeats;
	     int seatsReserved;
	     String daysOfWeek;
	     Date flyingStartDate;
	     Date flyingEndDate;
	     
	     
	     
	     
	     try{
	    	 
	    	 statement = connection.createStatement();  
	    	 resultSet = statement.executeQuery(query);  

	    	 while (resultSet.next()) 
	    	 {
	    		 
	    	 }  
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println("Unable to retrieve values from the UserDetails table ...");
	    	 e.printStackTrace();  
	     }

		return null;
	}

}