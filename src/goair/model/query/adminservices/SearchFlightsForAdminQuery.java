package goair.model.query.adminservices;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.SearchParametersForFlights;
import goair.util.SearchParametersForReservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchFlightsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchFlightsForAdminQuery.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
		String query = "select flightid, flightName, airlineName, source, "
				+ "destination, departureTime, arrivalTime, totalSeats, " 
				+ "seatsReserved, daysOfWeek, flyingStartDate,"
				+ "flyingEndDate "
				+ "from flight where currentStatus = 'Active'";

		logger.info("Get all the active flights : " + query);

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

					employee.setEmployeeId(resultSet.getInt("employeeId"));
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

	public String createSqlQuery(SearchParametersForReservation searchParam){
		 String query = "select "
				+ "r.pnr, r.customerId, "
				+ "r.flightId, r.numberOfSeatsBooked, "
				+ "r.creditCardNumber, r.dateOfBooking, "
				+ "r.dateOfFlying,r.totalPrice,c.firstName,c.lastName,"
				+ "f.airlineName,f.flightname,f.source,f.destination,f.departureTime "
				+ "from reservation r, flight f, customer c where r.flightId = f.flightid and "
				+ "r.customerId = c.customerId and r.currentStatus = 'Active' ";
		 
		 if(searchParam != null){
			 if(searchParam.pnr != 0){
				 query = query + " and r.pnr=" + searchParam.getPnr();
			 }
			 
			 if(searchParam.customerId != 0){
				 query = query + " and r.customerId=" + searchParam.getCustomerId();
			 }
			 
			 if(searchParam.flightId != 0){
				 query = query + " and r.flightId=" + searchParam.getFlightId();
			 }
			 
			 if(searchParam.dateOfFlying != null){
				 //query = query + " and r.dateOfFlying=" + dateFormat.format(searchParam.getDateOfFlying());
			 }
		 }
		 
		 return null;
	}
}
