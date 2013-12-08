package goair.model.query.adminservices;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.general.EmployeePassengerSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class GetAllFlightsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(GetAllFlightsForAdminQuery.class);
	
	/**
	 * This method will get all the flights in system for a Admin
	 * @return Flight[] 
	 */
	public Flight[] getAllFlightsForAdmin(Connection connection)
	{
		Map<Integer, Map<Date, EmployeePassengerSet>> flightMap = 
				new HashMap<Integer, Map<Date,EmployeePassengerSet>>();
		
		Map<Integer, Flight> flightIdToFlightMap = new HashMap<Integer, Flight>();
		
		List<Flight> flights = new ArrayList<Flight>();

		// Using SearchParametersForFlights create the query 
		//	that will search the flights.

		String query = "select flightid, flightName, airlineName, source, "
				+ "destination, departureTime, arrivalTime, totalSeats, "
				+ "seatsReserved, daysOfWeek, flyingStartDate,"
				+ "flyingEndDate,currentstatus "
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
				flight.setCurrentStatus(resultSet.getString("currentstatus"));
				flight.setTotalSeats(resultSet.getInt("totalSeats"));
				flight.setSeatsReserved(resultSet.getInt("seatsReserved"));
				flight.setDaysOfWeek(resultSet.getString("daysOfWeek"));
				flight.setFlyingStartDate(resultSet.getDate("flyingStartDate"));
				flight.setFlyingEndDate(resultSet.getDate("flyingEndDate"));
				
				// Add this flight to map so that we can use it later to create
				// 	the complete flight object
				flightIdToFlightMap.put(flight.getFlightId(), flight);
				
				// Add employees
				query = "select e.employeeId, emailId, firstName, lastName,"
						+ "gender, airlineName, jobDesc, position,"
						+ "hireDate, address, city, state, zipcode,"
						+ "dob, f.dateOfFlying, f.ticketPrice "
						+ "from flightflyinginformation f, employee e "
						+ "where f.employeeId =  e.employeeid and f.flightId = "
						+ flight.getFlightId();

				logger.info("Get all the employees for flight with id : " 
						+ flight.getFlightId() + ", query : "+query);

				statement = connection.createStatement();  
				ResultSet resultSet2 = statement.executeQuery(query);
				
				Map<Date, EmployeePassengerSet> dateOfFlyingEmployeePassengerSet = 
						flightMap.get(flight.getFlightId());
				EmployeePassengerSet employeePassengerSet = null;
				
				Employee employee = null;
				while (resultSet2.next()) 
				{
					employee = new Employee();

					employee.setEmployeeId(resultSet2.getString("employeeId"));
					employee.setEmailId(resultSet2.getString("emailId"));
					employee.setFirstName(resultSet2.getString("firstName"));
					employee.setLastName(resultSet2.getString("lastName"));
					employee.setGender(resultSet2.getString("gender"));
					employee.setAirlineName(resultSet2.getString("airlineName"));
					employee.setJobDesc(resultSet2.getString("jobDesc"));
					employee.setPosition(resultSet2.getString("position"));
					employee.setHireDate(resultSet2.getTimestamp("hireDate"));
					employee.setAddress(resultSet2.getString("address"));
					employee.setCity(resultSet2.getString("city"));
					employee.setState(resultSet2.getString("state"));
					employee.setZipcode(resultSet2.getString("zipcode"));
					employee.setDob(resultSet2.getTimestamp("dob"));
					
					Date dateOfFlying = resultSet2.getTimestamp("dateOfFlying");
					double ticketPrice = resultSet2.getLong("ticketPrice");
					
					if(dateOfFlyingEmployeePassengerSet == null)
					{
						dateOfFlyingEmployeePassengerSet = new HashMap<Date, EmployeePassengerSet>();
						employeePassengerSet = new EmployeePassengerSet();
						employeePassengerSet.setTicketPrice(ticketPrice);
						employeePassengerSet.addEmployee(employee);
						
						dateOfFlyingEmployeePassengerSet.put(dateOfFlying, employeePassengerSet);
						flightMap.put(flight.getFlightId(), dateOfFlyingEmployeePassengerSet);
					}
					else
					{
						 employeePassengerSet = 
								dateOfFlyingEmployeePassengerSet.get(dateOfFlying);
						
						if(employeePassengerSet == null)
						{
							employeePassengerSet = new EmployeePassengerSet();
							employeePassengerSet.setTicketPrice(ticketPrice);
							employeePassengerSet.addEmployee(employee);
							
							dateOfFlyingEmployeePassengerSet.put(dateOfFlying, employeePassengerSet);
						}
						else
						{
							employeePassengerSet.addEmployee(employee);
						}
					}
				}

				resultSet2.close();
				statement.close();
				
				// Add passengers
				query = "select c.customerId, emailId, firstName, lastName,"
						+ "gender, passportNum, nationality, "
						+ "address, city, state, zipcode,"
						+ "dob,dateOfFlying "
						+ "from reservation r, customer c "
						+ "where r.customerId =  c.customerId and r.flightId = "
						+ flight.getFlightId();

				logger.info("Get all the passengers(customers) for flight with id : " 
						+ flight.getFlightId() + ", query : "+query);

				statement = connection.createStatement();  
				resultSet2 = statement.executeQuery(query);

				Customer passenger = null;
				while (resultSet2.next()) 
				{
					passenger = new Customer();

					passenger.setCustomerId(resultSet2.getInt("customerId"));
					passenger.setEmailId(resultSet2.getString("emailId"));
					passenger.setFirstName(resultSet2.getString("firstName"));
					passenger.setLastName(resultSet2.getString("lastName"));
					passenger.setGender(resultSet2.getString("gender"));
					passenger.setPassportNum(resultSet2.getString("passportNum"));
					passenger.setNationality(resultSet2.getString("nationality"));
					passenger.setAddress(resultSet2.getString("address"));
					passenger.setCity(resultSet2.getString("city"));
					passenger.setState(resultSet2.getString("state"));
					passenger.setZipcode(resultSet2.getString("zipcode"));
					passenger.setDob(resultSet2.getTimestamp("dob"));
					
					Date dateOfFlying = resultSet2.getTimestamp("dateOfFlying");
					
					dateOfFlyingEmployeePassengerSet = flightMap.get(flight.getFlightId());
					
					if(dateOfFlyingEmployeePassengerSet == null)
					{
						dateOfFlyingEmployeePassengerSet = new HashMap<Date, EmployeePassengerSet>();
						employeePassengerSet = new EmployeePassengerSet();
						employeePassengerSet.addPassenger(passenger);
						
						dateOfFlyingEmployeePassengerSet.put(dateOfFlying, employeePassengerSet);
						flightMap.put(flight.getFlightId(), dateOfFlyingEmployeePassengerSet);
					}
					else
					{
						 employeePassengerSet = 
								dateOfFlyingEmployeePassengerSet.get(dateOfFlying);
						
						if(employeePassengerSet == null)
						{
							employeePassengerSet = new EmployeePassengerSet();
							employeePassengerSet.addPassenger(passenger);
							
							dateOfFlyingEmployeePassengerSet.put(dateOfFlying, employeePassengerSet);
						}
						else
						{
							employeePassengerSet.addPassenger(passenger);
						}
					}
				}

				resultSet2.close();
				statement.close();
			}

			resultSet.close();
			statement.close();
			
			// Create the flights 
			for(Entry<Integer, Flight> entry : flightIdToFlightMap.entrySet())
			{
				Integer flightId = entry.getKey();
				
				for (Date dateOfFlying : flightMap.get(flightId).keySet())
				{
					EmployeePassengerSet employeePassengerSet = flightMap.get(flightId).get(dateOfFlying);
					
					Flight newFlight = new Flight(entry.getValue());
					newFlight.setTicketPrice(employeePassengerSet.getTicketPrice());
					
					newFlight.setFlyingDate(dateOfFlying);
					
					newFlight.setCrewDetails(employeePassengerSet.getEmployees());
					newFlight.setPassengers(employeePassengerSet.getPassenger());
					
					flights.add(newFlight);
				}
			}
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+query+" : "+e.getMessage());
			e.printStackTrace();  
		}

		return flights.toArray(new Flight[flights.size()]);
	}

}
