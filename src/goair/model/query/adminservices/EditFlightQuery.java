package goair.model.query.adminservices;

import goair.model.employee.Employee;
import goair.model.flight.Flight;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class EditFlightQuery {
	
	public static Logger logger = Logger.getLogger(EditFlightQuery.class);
	
	/**
	 * Edit the flight
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editFlight(Flight flight, Connection connection)
	{
		String flightTableQuery = "update flight set flightName=?, source=?, "
				+ "destination=?, departureTime=?, arrivalTime=?, totalSeats=?, "
				+ "seatsReserved=?, daysOfWeek=?, flyingStartDate=?,"
				+ "flyingEndDate=? where flightId=?";

		String deleteFlyingInfoTableQuery = "delete from flightflyinginformation where flightId=?";
		
		String flightFlyingInfoTableQuery = "insert into flightflyinginformation(flightId, "
				+ "dateOfFlying, flightStatus, "
				+ "employeeId, ticketPrice) "
				+ "values (?,?,?,?,?) ";
		
		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Update query for flight table : "+flightTableQuery);
			
			preparedStatement = connection.prepareStatement(flightTableQuery);
			preparedStatement.setString(1, flight.getFlightName());
			preparedStatement.setString(2, flight.getSource());
			preparedStatement.setString(3, flight.getDestination());
			preparedStatement.setDate(4, new Date(flight.getDepartureTime().getTime()));
			preparedStatement.setDate(5, new Date(flight.getArrivalTime().getTime()));
			preparedStatement.setInt(6, flight.getTotalSeats());
			preparedStatement.setInt(7, flight.getSeatsReserved());
			preparedStatement.setString(8, flight.getDaysOfWeek());
			preparedStatement.setDate(9, new Date(flight.getFlyingStartDate().getTime()));
			preparedStatement.setDate(10, new Date(flight.getFlyingEndDate().getTime()));
			preparedStatement.setInt(11, flight.getFlightId());
			
			preparedStatement.execute();
			
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(deleteFlyingInfoTableQuery);
			logger.info("Delete all the records from the flying table : "+deleteFlyingInfoTableQuery);
			
			preparedStatement.setInt(1, flight.getFlightId());
			preparedStatement.execute();
			
			preparedStatement.close();
			
			// Here we have to insert into flight table then flightflyinginformation table
			logger.info("Insert query for flightFlyingInfoTableQuery table : "+
					flightFlyingInfoTableQuery);
			// Parse the dayOfWeek and add dates as per start date and end date
			for(String dayOfWeek : flight.getDaysOfWeek().split(","))
			{
				preparedStatement = connection.prepareStatement(flightFlyingInfoTableQuery); 
				
				if(dayOfWeek.equals("Monday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Tuesday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Wednesday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Thrusday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Friday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Saturday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
				else if(dayOfWeek.equals("Sunday"))
				{
					Calendar start = Calendar.getInstance();
					start.setTime(flight.getFlyingStartDate());

					while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +1);
					}
					
					// Add this monday
					addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					
					// For all the mondays till the date is lower than end date
					while (start.before(flight.getFlyingEndDate())) 
					{
					    start.add(Calendar.DAY_OF_WEEK, +7);
					    addRowToFlightFlyingInfoTable(preparedStatement, flight, flight.getFlightId(), start);
					}
				}
			}
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
	private void addRowToFlightFlyingInfoTable(PreparedStatement preparedStatement, Flight flight,
			int flightId, Calendar cal)
	{
		try
		{
			if(flight.getCrewDetails() != null)
			{
				for(Employee emp : flight.getCrewDetails())
				{
					preparedStatement.setInt(1, flightId);
					preparedStatement.setDate(2, new Date(cal.getTimeInMillis()));
					preparedStatement.setString(3, flight.getFlightStatus());
					preparedStatement.setInt(4, emp.getEmployeeId());
					preparedStatement.setDouble(5, flight.getTicketPrice());
					
					preparedStatement.addBatch();
				}
			}
			else
			{
				preparedStatement.setInt(1, flightId);
				preparedStatement.setDate(2, new Date(cal.getTimeInMillis()));
				preparedStatement.setString(3, flight.getFlightStatus());
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
				preparedStatement.setDouble(5, flight.getTicketPrice());
				
				preparedStatement.addBatch();
			}
		}
		catch(Exception e)
		{
			logger.error("Error while adding a row : addRowToFlightFlyingInfoTable");
		}
	}
}
