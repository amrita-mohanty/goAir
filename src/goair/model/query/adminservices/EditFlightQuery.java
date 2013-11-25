package goair.model.query.adminservices;

import goair.model.employee.Employee;
import goair.model.flight.Flight;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class EditFlightQuery {
	
	public static Logger logger = Logger.getLogger(EditFlightQuery.class);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Edit the flight
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editFlight(Flight flight, Connection connection)
	{
		//update flight table
		String updateFlightTableQuery = createUpdateFlightQuery(flight);
		Statement statement = null;
		try {
			logger.info("Update query for flight table : "+updateFlightTableQuery);
			statement = connection.createStatement();
			statement.execute(updateFlightTableQuery);
			statement.close();
		}
		catch (Exception e)
		{
			logger.error("Error updating flight query.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
		
		//update flightflyinginformation table
		String deleteFlyingInfoTableQuery = "delete from flightflyinginformation where flightId="+flight.getFlightId();
		try {
			logger.info("Delete from flightflyinginformation table : "+deleteFlyingInfoTableQuery);
			statement = connection.createStatement();
			statement.execute(deleteFlyingInfoTableQuery);
			statement.close();
		}
		catch (Exception e)
		{
			logger.error("Error deleting flightflyinginformation.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
		
		PreparedStatement preparedStatement = null;
		try
		{
			String flightFlyingInfoTableQuery = "insert into flightflyinginformation(flightId, "
					+ "dateOfFlying, flightStatus, "
					+ "employeeId, ticketPrice) "
					+ "values (?,?,?,?,?) ";
			
			preparedStatement = connection.prepareStatement(flightFlyingInfoTableQuery);
			
			// Here we have to insert into flight table then flightflyinginformation table
			logger.info("Insert query for flightFlyingInfoTableQuery table : "+
					flightFlyingInfoTableQuery);
			preparedStatement = connection.prepareStatement(flightFlyingInfoTableQuery);
			
			// Parse the dayOfWeek and add dates as per start date and end date
			for(String dayOfWeek : flight.getDaysOfWeek().split(","))
			{
				if(dayOfWeek.equals("Monday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.MONDAY);
				}
				else if(dayOfWeek.equals("Tuesday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.TUESDAY);
				}
				else if(dayOfWeek.equals("Wednesday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.WEDNESDAY);
				}
				else if(dayOfWeek.equals("Thrusday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.THURSDAY);
				}
				else if(dayOfWeek.equals("Friday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.FRIDAY);
				}
				else if(dayOfWeek.equals("Saturday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.SATURDAY);
				}
				else if(dayOfWeek.equals("Sunday"))
				{
					addADay(preparedStatement, flight, flight.getFlightId(),Calendar.SUNDAY);
				}
			}
			preparedStatement.executeBatch();
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
	
	
	private String createUpdateFlightQuery(Flight flight)
	{
		String updateFlightTableQuery = "update flight set ";
		
//		  `flightName` VARCHAR(10) NOT NULL UNIQUE,
//		  `source` VARCHAR(40) NOT NULL,
//		  `destination` VARCHAR(40) NOT NULL,
//		  `departureTime` DATETIME NOT NULL,
//		  `arrivalTime` DATETIME NOT NULL,
//		  `totalSeats` INT(20) NOT NULL,
//		  `seatsReserved` INT(10) NOT NULL,
//		  `daysOfWeek` VARCHAR(50) NOT NULL, -- Comma separated days for this flight eg. for Monday and Thursday it will be M,Th
//		  `flyingStartDate` DATE NOT NULL,
//		  `flyingEndDate` DATE NOT NULL,
//		  `currentStatus` VARCHAR(10) default 'ACTIVE', -- Active, Inactive or Deleted
		
		boolean addComma = false;
		if(flight.getFlightName() != null)
		{
			updateFlightTableQuery += " flightName='"+flight.getFlightName()+"' ";
			addComma = true;
		}
		if(flight.getSource() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " source='"+flight.getSource()+"' ";
			addComma = true;
		}
		if(flight.getDestination() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " destination='"+flight.getDestination()+"' ";
			addComma = true;
		}
		if(flight.getDepartureTime() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " departureTime='"+timeFormat.format(flight.getDepartureTime())+"' ";
			addComma = true;
		}
		if(flight.getArrivalTime() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " arrivalTime='"+timeFormat.format(flight.getArrivalTime())+"' ";
			addComma = true;
		}
		if(flight.getTotalSeats() > 0)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " totalSeats="+flight.getTotalSeats()+" ";
			addComma = true;
		}
		if(flight.getSeatsReserved() > 0)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " seatsReserved="+flight.getSeatsReserved()+" ";
			addComma = true;
		}
		if(flight.getDaysOfWeek() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " daysOfWeek='"+flight.getDaysOfWeek()+"' ";
			addComma = true;
		}
		if(flight.getFlyingStartDate() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " flyingStartDate='"+dateFormat.format(flight.getFlyingStartDate())+"' ";
			addComma = true;
		}
		if(flight.getFlyingEndDate() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " flyingEndDate='"+dateFormat.format(flight.getFlyingEndDate())+"' ";
			addComma = true;
		}
		if(flight.getCurrentStatus() != null)
		{
			updateFlightTableQuery += (addComma ? "," : "") + " currentStatus='"+flight.getCurrentStatus()+"' ";
			addComma = true;
		}
		
		updateFlightTableQuery += " where flightId=" + flight.getFlightId();
		
		return updateFlightTableQuery;
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
