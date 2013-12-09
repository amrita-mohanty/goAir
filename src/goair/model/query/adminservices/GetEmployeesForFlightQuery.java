package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetEmployeesForFlightQuery {
	public static Logger logger = Logger.getLogger(GetEmployeesForFlightQuery.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Employee[] searchEmployeesForFlight(int flightId,
			Connection connection)
	{
		// Create the query that will search the customers in a flight.
		String query = null;

		ResultSet resultSet = null;  
		Statement statement = null;
		List<Employee> employeeList = new ArrayList<Employee>();

		try{

			statement = connection.createStatement();  
			
			// Get All Employees for a flight
			query = "select e.employeeId, emailId, firstName, lastName,"
					+ "gender, airlineName, jobDesc, position,"
					+ "hireDate, address, city, state, zipcode,"
					+ "dob "
					+ "from flightflyinginformation f, employee e "
					+ "where f.employeeId =  e.employeeId and f.flightId = "
						+ flightId;

				logger.info("Get all the employees for flight with id : " 
						+ flightId +", query : "+query);

				statement = connection.createStatement();  
				resultSet = statement.executeQuery(query);

				Employee employee= null;
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

					employeeList.add(employee);
				}

				resultSet.close();
				statement.close();
			
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return employeeList.toArray(new Employee[employeeList.size()]);
	}
}
