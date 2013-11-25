package goair.model.query.adminservices;

import goair.model.flight.Flight;

import java.sql.Connection;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DeleteFlightQuery {
	
	public static Logger logger = Logger.getLogger(DeleteFlightQuery.class);
	
	/**
	 * Edit the flight
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 1, failure : -1
	 */
	public int deleteFlight(Flight flight, Connection connection)
	{
		String deleteFlightTableQuery = "update flight set currentStatus='Deleted' where flightId="+flight.getFlightId();

		Statement statement = null;
		try
		{
			logger.info("Delete query for flight table : "+deleteFlightTableQuery);
			
			statement = connection.createStatement();
			statement.execute(deleteFlightTableQuery);
			statement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while deleting flight.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
