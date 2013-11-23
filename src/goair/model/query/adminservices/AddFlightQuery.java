package goair.model.query.adminservices;

import goair.model.employee.Employee;
import goair.model.flight.Flight;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class AddFlightQuery {
	
	public static Logger logger = Logger.getLogger(AddFlightQuery.class);
	
	/**
	 * Add a new flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addNewFlight(Flight flight, Connection connection)
	{
		String flightTableQuery = "insert into flight(flightName, source, "
				+ "destination, departureTime, arrivalTime, totalSeats, "
				+ "seatsReserved, daysOfWeek, flyingStartDate,"
				+ "flyingEndDate) values (?,?,?,?,?,?,?,?,?,?) ";

		String getFlightIdTableQuery = "select flightId "
				+ "from flight "
				+ "where flightName='"+ flight.getFlightName() +"'";
		
		String flightFlyingInfoTableQuery = "insert into flightflyinginformation(flightId, "
				+ "dateOfFlying, flightStatus, "
				+ "employeeId, ticketPrice) "
				+ "values (?,?,?,?,?) ";
		
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		
		try
		{
			logger.info("Insert query for flight table : "+flightTableQuery);
			
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
			
			preparedStatement.execute();
			preparedStatement.close();
			
			// Get the flight id just added.
			logger.info("Get the flight id just added : "+
					getFlightIdTableQuery);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getFlightIdTableQuery);
			Integer flightId = null;
			while(resultSet.next())
			{
				flightId = resultSet.getInt("flightId");
			}
			
			// Here we have to insert into flight table then flightflyinginformation table
			logger.info("Insert query for flightFlyingInfoTableQuery table : "+
					flightFlyingInfoTableQuery);
			preparedStatement = connection.prepareStatement(flightFlyingInfoTableQuery);
			
			// Parse the dayOfWeek and add dates as per start date and end date
			for(String dayOfWeek : flight.getDaysOfWeek().split(","))
			{
				if(dayOfWeek.equals("Monday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.MONDAY);
				}
				else if(dayOfWeek.equals("Tuesday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.TUESDAY);
				}
				else if(dayOfWeek.equals("Wednesday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.WEDNESDAY);
				}
				else if(dayOfWeek.equals("Thrusday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.THURSDAY);
				}
				else if(dayOfWeek.equals("Friday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.FRIDAY);
				}
				else if(dayOfWeek.equals("Saturday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.SATURDAY);
				}
				else if(dayOfWeek.equals("Sunday"))
				{
					addADay(preparedStatement, flight, flightId,Calendar.SUNDAY);
				}
			}
			preparedStatement.executeBatch();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
	private void addADay(PreparedStatement preparedStatement, Flight flight,
			int flightId, int dayOfWeek)
	{
		Calendar start = Calendar.getInstance();
		start.setTime(flight.getFlyingStartDate());

		while (start.get(Calendar.DAY_OF_WEEK) != dayOfWeek) 
		{
		    start.add(Calendar.DAY_OF_WEEK, +1);
		}
		
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		logger.info("start Date: " + ft.format(start.getTime()));
			      
		// Add this monday
		addRowToFlightFlyingInfoTable(preparedStatement, flight, flightId, 
				new java.sql.Date(start.getTime().getTime()));
		
		// For all the mondays till the date is lower than end date
		while (start.getTime().before(flight.getFlyingEndDate())) 
		{
			logger.info("next Date: " + ft.format(start.getTime()));
			addRowToFlightFlyingInfoTable(preparedStatement, flight, flightId, 
					new java.sql.Date(start.getTime().getTime()));
		    start.add(Calendar.DAY_OF_WEEK, +7);
		}
	}
	
	private void addRowToFlightFlyingInfoTable(PreparedStatement preparedStatement, Flight flight,
			int flightId, Date day)
	{
		try
		{
			if(flight.getCrewDetails() != null)
			{
				for(Employee emp : flight.getCrewDetails())
				{
					preparedStatement.setInt(1, flightId);
					preparedStatement.setDate(2, day);
					preparedStatement.setString(3, flight.getFlightStatus());
					preparedStatement.setInt(4, emp.getEmployeeId());
					preparedStatement.setDouble(5, flight.getTicketPrice());
					
					preparedStatement.addBatch();
				}
			}
			else
			{
				preparedStatement.setInt(1, flightId);
				preparedStatement.setDate(2, day);
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
