package goair.model.query.adminservices;

import goair.model.flight.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class DeleteFlightQuery {
	
	public static Logger logger = Logger.getLogger(DeleteFlightQuery.class);
	
	/**
	 * Edit the flight
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 0, failure : -1
	 */
	public int deleteFlight(Flight flight, Connection connection)
	{
		String deleteFlightTableQuery = "update flight set currentStatus='Deleted' where flightId=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Delete query for flight table : "+deleteFlightTableQuery);
			
			preparedStatement = connection.prepareStatement(deleteFlightTableQuery);
			preparedStatement.setInt(1, flight.getFlightId());
			
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
