package goair.model.query.adminservices;

import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class AddReservationQuery {
	
	public static Logger logger = Logger.getLogger(AddReservationQuery.class);
	
	/**
	 * Add a new Customer to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addNewReservation(Reservation reservation, Connection connection)
	{
		String reservationTableQuery = "insert into reservation("
				+ "customerId, "
				+ "flightId, numberOfSeatsBooked, "
				+ "creditCardNumber, dateOfBooking, "
				+ "dateOfFlying, totalPrice ) values (?,?,?,?,?,?,?) ";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Insert query for reservation table : "+reservationTableQuery);
			
			preparedStatement = connection.prepareStatement(reservationTableQuery);
			preparedStatement.setInt(1, reservation.getCustomerId());
			preparedStatement.setInt(2, reservation.getFlightId());
			preparedStatement.setInt(3, reservation.getNumberOfSeatsBooked());
			preparedStatement.setLong(4, reservation.getCreditCardNumber());
			preparedStatement.setDate(5, new Date(reservation.getDateOfBooking().getTime()));
			preparedStatement.setDate(6, new Date(reservation.getDateOfFlying().getTime()));
			preparedStatement.setDouble(7, reservation.getTotalPrice());
			
			preparedStatement.execute();
			preparedStatement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
