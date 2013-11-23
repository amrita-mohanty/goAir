package goair.model.query.customerservices;

import goair.model.flight.Flight;
import goair.util.SearchParametersForFlights;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchFlightsForCustomerQuery {
	
	public static Logger logger = Logger.getLogger(SearchFlightsForCustomerQuery.class);

	/**
	 * This method will get the flights list based on search parameters passed to it.
	 * Search Parameter should have: source, destination, date of travel
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForCustomer(SearchParametersForFlights searchParameters,
			Connection connection)
	{
		List<Flight> flightsList = new ArrayList<Flight>();

		// Using SearchParametersForFlights create the query 
		//	that will search the flights.

		String query = "select flight.flightid, flight.flightName, flight.airlineName, flight.source, "
				+ "flight.destination, flight.departureTime, flight.arrivalTime, flight.totalSeats, "
				+ "flight.seatsReserved from flight,flightflyinginformation where "
				+ "flight.flightId=flightflyinginformation.flightId and "
				+ "flight.source=? and flight.destination=? and  flightflyinginformation.dateOfFlying=?";



		logger.info("Get all the flights : " + query);

		ResultSet resultSet = null;  
		PreparedStatement preparedStatement = null;

		Flight flight = null;

		try{

			preparedStatement = connection.prepareStatement(query);  
			preparedStatement.setString(1,searchParameters.getSource());
			preparedStatement.setString(2,searchParameters.getDestination());
			preparedStatement.setDate(3,new Date(searchParameters.getDateOfFlying().getTime()));

			resultSet = preparedStatement.executeQuery(query);

			while (resultSet.next()) 
			{
				flight = new Flight();

				flight.setFlightId(Integer.parseInt(resultSet.getString("flightId"))); // Hidden field in the UI.Required during reservation
				flight.setFlightName(resultSet.getString("flightName"));
				flight.setAirlineName(resultSet.getString("airlineName"));
				flight.setSource(resultSet.getString("source"));
				flight.setDestination(resultSet.getString("destination"));
				flight.setDepartureTime(new Date(resultSet.getTimestamp("departureTime").getTime()));
				flight.setArrivalTime(new Date(resultSet.getTimestamp("arrivalTime").getTime()));
				flight.setSeatsAvailable(resultSet.getInt("totalSeats")-resultSet.getInt("seatsReserved"));
				flight.setSeatsReserved(resultSet.getInt("seatsReserved"));

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