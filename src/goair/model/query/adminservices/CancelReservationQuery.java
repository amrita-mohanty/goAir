package goair.model.query.adminservices;

import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class CancelReservationQuery {
	
	public static Logger logger = Logger.getLogger(CancelReservationQuery.class);
	
	/**
	 * Cancel the Reservation
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 1, failure : -1
	 */
	public int cancelReservation(Reservation reservation, Connection connection)
	{
		String cancelReservationTableQuery = "update reservation set currentStatus = 'Cancelled' where pnr=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Cancel reservation query : "+ cancelReservationTableQuery);
			
			preparedStatement = connection.prepareStatement(cancelReservationTableQuery);
			preparedStatement.setLong(1, reservation.getPnr());
			preparedStatement.execute();
			preparedStatement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while cancelling the reservation.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
