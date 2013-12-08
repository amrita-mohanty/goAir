package goair.model.query.adminservices;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.SearchParametersForFlights;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchFlightsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchFlightsForAdminQuery.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters, 
			Connection connection)
	{
		List<Flight> flights = new ArrayList<Flight>();

		// Using SearchParametersForFlights create the query 
		//	that will search the flights.
		String query = createSearchFlightSqlQuery(searchParameters);

		logger.info("Get all the active flights based on search criteria : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Flight flight = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				flight = new Flight();

				flight.setFlightId(resultSet.getInt("flightId"));
				flight.setFlightName(resultSet.getString("flightName"));
				flight.setAirlineName(resultSet.getString("airlineName"));
				flight.setSource(resultSet.getString("source"));
				flight.setDestination(resultSet.getString("destination"));
				flight.setDepartureTime(resultSet.getTimestamp("departureTime"));
				flight.setArrivalTime(resultSet.getTimestamp("arrivalTime"));
				flight.setFlightStatus(resultSet.getString("status"));
				flight.setTotalSeats(resultSet.getInt("totalSeats"));
				flight.setSeatsReserved(resultSet.getInt("seatsReserved"));
				flight.setDaysOfWeek(resultSet.getString("daysOfWeek"));
				flight.setFlyingStartDate(resultSet.getDate("flyingStartDate"));
				flight.setFlyingEndDate(resultSet.getDate("flyingEndDate"));

				flights.add(flight);
			}

			resultSet.close();
			statement.close();

			// Add employee info
			for(Flight addedFlight : flights)
			{
				query = "select employeeId, emailId, firstName, lastName,"
						+ "gender, airlineName, jobDesc, position,"
						+ "hireDate, address, city, state, zipcode,"
						+ "dob "
						+ "from flightflyinginformation f, employee e "
						+ "where f.employeeId =  e.employee and f.flightId = "
						+ addedFlight.getFlightId();

				logger.info("Get all the employees for flight with id : " 
						+ addedFlight.getFlightId() + ", query : "+query);

				statement = connection.createStatement();  
				resultSet = statement.executeQuery(query);
				
				List<Employee> empList = new ArrayList<Employee>();
				Employee employee = null;
				while (resultSet.next()) 
				{
					employee = new Employee();

					employee.setEmployeeId(resultSet.getString("employeeId"));
					employee.setEmailId(resultSet.getString("emailId"));
					employee.setFirstName(resultSet.getString("firstName"));
					employee.setLastName(resultSet.getString("lastName"));
					employee.setGender(resultSet.getString("gender"));
					employee.setAirlineName(resultSet.getString("airlineName"));
					employee.setJobDesc(resultSet.getString("jobDesc"));
					employee.setPosition(resultSet.getString("position"));
					employee.setHireDate(resultSet.getTimestamp("hireDate"));
					employee.setAddress(resultSet.getString("address"));
					employee.setCity(resultSet.getString("city"));
					employee.setState(resultSet.getString("state"));
					employee.setZipcode(resultSet.getString("zipcode"));
					employee.setDob(resultSet.getTimestamp("dob"));

					empList.add(employee);
				}

				resultSet.close();
				statement.close();
				
				addedFlight.setCrewDetails(empList.toArray(new Employee[empList.size()]));
			}
			
			// Add Customers or Passengers
			for(Flight addedFlight : flights)
			{
				query = "select customerId, emailId, firstName, lastName,"
						+ "gender, passportNum, nationality, "
						+ "address, city, state, zipcode,"
						+ "dob "
						+ "from reservation r, customer c "
						+ "where r.customerId =  c.customerId and r.flightId = "
						+ addedFlight.getFlightId();

				logger.info("Get all the passengers(customers) for flight with id : " 
						+ addedFlight.getFlightId() + ", query : "+query);

				statement = connection.createStatement();  
				resultSet = statement.executeQuery(query);

				List<Customer> passengerList = new ArrayList<Customer>();
				Customer passenger = null;
				while (resultSet.next()) 
				{
					passenger = new Customer();

					passenger.setCustomerId(resultSet.getInt("customerId"));
					passenger.setEmailId(resultSet.getString("emailId"));
					passenger.setFirstName(resultSet.getString("firstName"));
					passenger.setLastName(resultSet.getString("lastName"));
					passenger.setGender(resultSet.getString("gender"));
					passenger.setPassportNum(resultSet.getString("passportNum"));
					passenger.setNationality(resultSet.getString("nationality"));
					passenger.setAddress(resultSet.getString("address"));
					passenger.setCity(resultSet.getString("city"));
					passenger.setState(resultSet.getString("state"));
					passenger.setZipcode(resultSet.getString("zipcode"));
					passenger.setDob(resultSet.getTimestamp("dob"));

					passengerList.add(passenger);
				}

				resultSet.close();
				statement.close();

				addedFlight.setPassengers(passengerList.toArray(new Customer[passengerList.size()]));
			}
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return flights.toArray(new Flight[flights.size()]);
	}

	public String createSearchFlightSqlQuery(SearchParametersForFlights searchParam) {
		String query = "select flightid, flightName, airlineName, source, "
				+ "destination, departureTime, arrivalTime, totalSeats, "
				+ "seatsReserved, daysOfWeek, flyingStartDate,"
				+ "flyingEndDate "
				+ "from flight where currentStatus = 'Active'";

		if(searchParam != null) {
			if(searchParam.getFlightId() != null) {
				query = query + " and flightId=" + searchParam.getFlightId();
			}
			if(searchParam.getFlightName() != null && !searchParam.getFlightName().equals("")) {
				query = query + " and flightName='" + searchParam.getFlightName()+"'";
			}
			if(searchParam.getAirlineName() != null && !searchParam.getAirlineName().equals("")) {
				query = query + " and airlineName='" + searchParam.getAirlineName()+"'";
			}
			if(searchParam.getSource() != null && !searchParam.getSource().equals("")) {
				query = query + " and source='" + searchParam.getSource()+"'";
			}
			if(searchParam.getDestination() != null && !searchParam.getDestination().equals("")) {
				query = query + " and destination='" + searchParam.getDestination()+"'";
			}
			if(searchParam.getDepartureTime() != null && !searchParam.getDepartureTime().equals("")) {
				query = query + " and departureTime='" + timeFormat.format(searchParam.getDepartureTime())+"'";
			}
			if(searchParam.getArrivalTime() != null && !searchParam.getArrivalTime().equals("")) {
				query = query + " and arrivalTime='" + timeFormat.format(searchParam.getArrivalTime())+"'";
			}
			if(searchParam.getNumberOfSeatsAvialable() != null) {
				query = query + " and (totalSeats - seatsReserved)=" + searchParam.getNumberOfSeatsAvialable();
			}
			
			if (searchParam.getEmployeeId() != null && !searchParam.getEmployeeId().equals("")) {
				String flightFlyingQuery = "(select flightId from flightflyinginformation "
						+ "where employeeId='"+searchParam.getEmployeeId()+"') ";
				query = query + " and flightId in " + flightFlyingQuery;
			}
			if (searchParam.getCustomerId() != null) {
				String reservationQuery = "(select flightId from reservation "
						+ "where customerId="+searchParam.getCustomerId()+") ";
				query = query + " and flightId in " + reservationQuery;
				
			}
			if (searchParam.getDateOfFlying() != null) {
				String flightFlyingQuery = "(select flightId from flightflyinginformation "
						+ "where dateOfFlying="+dateFormat.format(searchParam.getDateOfFlying())+") ";
				query = query + "and flightId in " + flightFlyingQuery;
			}
		}

		return query;
	}
}
