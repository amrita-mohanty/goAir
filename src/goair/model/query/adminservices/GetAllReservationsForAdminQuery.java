package goair.model.query.adminservices;

import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetAllReservationsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(GetAllReservationsForAdminQuery.class);
	
	/**
	 * This method will get all the reservations in the system for 
	 * an  admin or a customer based on the searchParameters
	 * @return Reservation[] 
	 */
	public Reservation[] getAllReservationsForAdmin(Connection connection)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();

		String query = "select "
				+ "r.pnr, r.customerId, "
				+ "r.flightId, r.numberOfSeatsBooked, "
				+ "r.creditCardNumber, r.dateOfBooking, "
				+ "r.dateOfFlying,r.totalPrice,c.firstName,c.lastName,"
				+ "f.airlineName,f.flightname,f.source,f.destination,f.departureTime "
				+ "from reservation r, flight f, customer c where r.flightId = f.flightid and "
				+ "r.customerId = c.customerId";

		logger.info("Get all the reservation query : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Reservation reservation = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				reservation = new Reservation();
//				reservation.setCustomerDetails(new Customer());
//				reservation.setFlightDetails(new Flight());

				reservation.setPnr(resultSet.getInt("pnr"));
//				reservation.getCustomerDetails().setCustomerId(resultSet.getInt("customerid"));
//				reservation.getCustomerDetails().setFirstName(resultSet.getString("firstName"));
//				reservation.getCustomerDetails().setLastName(resultSet.getString("lastName"));
//				reservation.getFlightDetails().setFlightId(resultSet.getInt("flightId"));
//				reservation.getFlightDetails().setAirlineName(resultSet.getString("airlineName"));
//				reservation.getFlightDetails().setFlightName(resultSet.getString("flightName"));
//				reservation.getFlightDetails().setSource(resultSet.getString("source"));
//				reservation.getFlightDetails().setDestination(resultSet.getString("destination"));
//				reservation.getFlightDetails().setDepartureTime(resultSet.getTimestamp("departureTime"));
				reservation.setNumberOfSeatsBooked(resultSet.getInt("numberOfSeatsBooked"));
				reservation.setCreditCardNumber(resultSet.getInt("creditCardNumber"));
				reservation.setDateOfBooking(resultSet.getDate("dateOfBooking"));
				reservation.setDateOfFlying(resultSet.getDate("dateOfFlying"));
				reservation.setTotalPrice(resultSet.getInt("totalPrice"));
				
				reservations.add(reservation);
			}

			resultSet.close();
			statement.close();
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return reservations.toArray(new Reservation[reservations.size()]);
	}
	
	

}//class
