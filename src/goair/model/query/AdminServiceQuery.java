package goair.model.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;

public class AdminServiceQuery 
{
	DbConnection dbConnection = null;
	Connection connection = null;
	
	public AdminServiceQuery()
	{
		dbConnection = new DbConnection();
		connection = dbConnection.createDbConnection();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		List<Flight> flights = new ArrayList<Flight>();

		// Using SearchParametersForFlights create the query 
		//	that will search the flights.

		String query = "select flightid, flightName, airlineName, source, "
				+ "destination, departureTime, arrivalTime, totalSeats, "
				+ "seatsReserved, daysOfWeek, flyingStartDate,"
				+ "flyingEndDate "
				+ "from flight ";

		System.out.println("Get all the flights : " + query);

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
				flight.setDepartureTime(resultSet.getDate("departureTime"));
				flight.setArrivalTime(resultSet.getDate("arrivalTime"));
				flight.setStatus(resultSet.getString("status"));
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

				System.out.println("Get all the employees for flight with id : " 
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

				System.out.println("Get all the passengers(customers) for flight with id : " 
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
			System.out.println("Unable to retrieve values from the UserDetails table ...");
			e.printStackTrace();  
		}

		return null;
	}

}
