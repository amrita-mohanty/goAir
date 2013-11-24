package goair.model.query.adminservices;

import goair.model.reservation.Reservation;
import goair.util.SearchParametersForReservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchReservationsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchReservationsForAdminQuery.class);
	
	/**
	 * This method will get all the customers in system for a Admin
	 * @return Reservation[] 
	 */
	public Reservation[] searchReservationForAdmin(Connection connection, 
			SearchParametersForReservation searchParameters)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();

		String query = "select "
					+ "pnr, customerId, "
					+ "flightId, numberOfSeatsBooked, "
					+ "creditCardNumber, dateOfBooking, "
					+ "dateOfFlying, totalPrice "
					+ "from reservation ";

		logger.info("Get all the reservation : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Reservation reservation = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				reservation = new Reservation();

				reservation.setPnr(resultSet.getInt("pnr"));
				reservation.getCustomerDetails().setCustomerId(resultSet.getInt("customerid"));
				reservation.getFlightDetails().setFlightId(resultSet.getInt("flightId"));
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

}
