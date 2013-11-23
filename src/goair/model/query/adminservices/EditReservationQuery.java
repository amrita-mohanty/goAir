package goair.model.query.adminservices;

import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class EditReservationQuery {
	
	public static Logger logger = Logger.getLogger(EditReservationQuery.class);
	
	/**
	 * Edit the Employee
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editReservation(Reservation reservation, Connection connection)
	{
		String reservationTableQuery = "update reservation set "  
				+ " numberOfSeatsBooked=?, "
				+ "creditCardNumber=? where pnr=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Update query for reservation table : "+reservationTableQuery);
			
			preparedStatement = connection.prepareStatement(reservationTableQuery);
			preparedStatement.setInt(1, reservation.getNumberOfSeatsBooked());
			preparedStatement.setLong(2, reservation.getCreditCardNumber());
			preparedStatement.setString(3, reservation.getPnr());
			
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while update.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
