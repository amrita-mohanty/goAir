package goair.model.query.adminservices;

import goair.model.reservation.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
	/*
	 * We assume that the reservation object is complete because we need 
	 * total seats reserved to make it available and we can avoid an extra 
	 * query to get other details.
	 */
	public int cancelReservation(Reservation reservation, Connection connection)
	{
		Statement statement = null;
		try
		{
			String cancelReservationTableQuery = "update reservation set currentStatus = 'Cancelled' where pnr="+reservation.getPnr();
			
			logger.info("Cancel query for reservation table : "+cancelReservationTableQuery);
			
			statement = connection.createStatement();
			statement.execute(cancelReservationTableQuery);
			statement.close();
			
			// here we have to return back the seats to flight table as well so that they are 
			// 	available for others to reserve
				//Step 1 : get seats reserved from flight table
			String getSeatReservedInFightQuery = "select seatsReserved from flight where flightId="+reservation.getFlightId();
			logger.info("Get seatsReserved from flight table query : "+getSeatReservedInFightQuery);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getSeatReservedInFightQuery);
			int seatsReserved = 0;
			while(resultSet.next())
			{
				seatsReserved = resultSet.getInt("seatsReserved");
			}
			resultSet.close();
			statement.close();
			
				//Step 2 : update the flight table with new number of seats
			String updateFlightTableQuery = "update flight set seatsReserved="+(seatsReserved+reservation.getNumberOfSeatsBooked())
					+" where flightId="+reservation.getFlightId();
			logger.info("Update flight table query : "+updateFlightTableQuery);
			
			statement = connection.createStatement();
			statement.execute(updateFlightTableQuery);
			statement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while canceling reservation.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
