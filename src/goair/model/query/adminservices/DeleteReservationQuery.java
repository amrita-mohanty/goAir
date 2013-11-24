package goair.model.query.adminservices;

import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class DeleteReservationQuery {
	
	public static Logger logger = Logger.getLogger(DeleteReservationQuery.class);
	
	/**
	 * Delete the Reservation
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 0, failure : -1
	 */
	public int deleteReservation(Reservation reservation, Connection connection)
	{
		String deleteReservationTableQuery = "update reservation set currentStatus = 'Deleted' where pnr=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Delete query for customer table : "+deleteReservationTableQuery);
			
			preparedStatement = connection.prepareStatement(deleteReservationTableQuery);
			preparedStatement.setLong(1, reservation.getPnr());
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while delete.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
