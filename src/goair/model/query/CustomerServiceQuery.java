package goair.model.query;

import goair.model.flight.Flight;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceQuery {
	DbConnection dbConnection = null;
	Connection connection = null;
	
	public CustomerServiceQuery()
	{
		dbConnection = new DbConnection();
		connection = dbConnection.createDbConnection();
	}
	
	/**
	 * This method will get the flights list based on search parameters passed to it.
	 * Search Parameter should have: source, destination, date of travel
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForCustomer(SearchParametersForFlights searchParameters)
	{
		List<Flight> flightsList = new ArrayList<Flight>();
		
		// Using SearchParametersForFlights create the query 
		//	that will search the flights.
		
		String travelDate = "STR_TO_DATE('" + new Date(searchParameters.getDateOfFlying().getTime())+"', '%Y-%m-%d')";
		
		/*String stmt_query = "select flight.flightid, flight.flightName, flight.airlineName, flight.source, "
				+ "flight.destination, flight.departureTime, flight.arrivalTime, flight.totalSeats, "
				+ "flight.seatsReserved, flightflyinginformation.ticketPrice from flight,flightflyinginformation where "
				+ "flight.flightId=flightflyinginformation.flightId and "
				+ "flight.source='" + searchParameters.getSource() + "' and flight.destination='" + searchParameters.getDestination() 
				+ "' and  flightflyinginformation.dateOfFlying="+travelDate;
				
			System.out.println("searchFlightsForCustomer query : " + stmt_query);*/
		
		String preparedStmt_query = "select flight.flightid, flight.flightName, flight.airlineName, flight.source, "
				+ "flight.destination, flight.departureTime, flight.arrivalTime, flight.totalSeats, "
				+ "flight.seatsReserved, flightflyinginformation.ticketPrice from flight,flightflyinginformation where "
				+ "flight.flightId=flightflyinginformation.flightId and "
				+ "flight.source=? and flight.destination=? and flightflyinginformation.dateOfFlying=?";
		
		System.out.println("searchFlightsForCustomer query : " + preparedStmt_query);
		
		 ResultSet resultSet = null; 
		 //Statement stmt = null;
		 PreparedStatement preparedStatement = null;
	     
	     Flight flight = null;
	     
	     try{
	    	 
	    	 preparedStatement = connection.prepareStatement(preparedStmt_query);  
	    	 preparedStatement.setString(1,searchParameters.getSource());
	    	 preparedStatement.setString(2,searchParameters.getDestination());
	    	 preparedStatement.setDate(3,new Date(searchParameters.getDateOfFlying().getTime())); // util.Date to sql.Date
	    	 
	    	 resultSet = preparedStatement.executeQuery();
	    	 
	    	 /*stmt = connection.createStatement();
	    	 resultSet  = stmt.executeQuery(stmt_query);*/
	    	 
	    	 

	    	 while (resultSet.next()) 
	    	 {
	    		 flight = new Flight();
	    		 
	    		 flight.setFlightId(resultSet.getInt("flightId")); // Hidden field in the UI.Required during reservation
	    		 flight.setFlightName(resultSet.getString("flightName"));
	    		 flight.setAirlineName(resultSet.getString("airlineName"));
	    		 flight.setSource(resultSet.getString("source"));
	    		 flight.setDestination(resultSet.getString("destination"));
	    		 flight.setDepartureTime(resultSet.getDate("departureTime"));
	    		 flight.setArrivalTime(resultSet.getDate("arrivalTime"));
	    		 flight.setSeatsAvailable(resultSet.getInt("totalSeats")-resultSet.getInt("seatsReserved"));
	    		 flight.setTicketPrice(resultSet.getDouble("ticketPrice"));
	    		 
	    		 flightsList.add(flight);
	    	 }  
	     } 
	     catch (Exception e) 
	     {  
	    	 System.err.println("Unable to retrieve values from the Flight table. ERROR MSG : " + e.getMessage());
	    	 e.printStackTrace();  
	     }

		return flightsList.toArray(new Flight[flightsList.size()]); // Convert ArrayList to Array[]
	}
	
}//class