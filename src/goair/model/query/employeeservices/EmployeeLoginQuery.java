package goair.model.query.employeeservices;

import goair.model.employee.Employee;
import goair.model.query.adminservices.SearchFlightsForAdminQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class EmployeeLoginQuery {
	public static Logger logger = Logger.getLogger(SearchFlightsForAdminQuery.class);

	/**
	 * This method will validate employee login credentials
	 * @return employee[] 
	 */
	public Employee validateEmployeeLogin (String emailId,String password,Connection connection)
	{
		/* Check whether a employee is valid or not.
		 * If valid, then return the details of the employee 
		 * set in the Empoyee bean.
		 */

		String query = "select employeeId, emailId, password, firstName, "
				+ "lastName, gender, airlineName, jobDesc, position, hireDate, currentStatus, "
				+ "address, city, state, zipcode, dob "
				+ "from employee where currentStatus='ACTIVE' and emailId='"+ emailId+ "' and password='"+password+"'";

		logger.info("Get employee information : " + query);

		ResultSet resultSet = null;  
		Statement preparedStatement = null;

		Employee employee = null;

		try{

			preparedStatement = connection.createStatement();  
			resultSet = preparedStatement.executeQuery(query);  

			while (resultSet.next()) 
			{
				employee = new Employee();

				employee.setEmployeeId(resultSet.getInt("employeeId"));
				employee.setEmailId(resultSet.getString("emailId"));
				employee.setPassword(resultSet.getString("password"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setGender(resultSet.getString("gender"));
				employee.setJobDesc(resultSet.getString("jobDesc"));
				employee.setPosition(resultSet.getString("position"));
				employee.setAddress(resultSet.getString("address"));
				employee.setCity(resultSet.getString("city"));
				employee.setState(resultSet.getString("state"));
				employee.setZipcode(resultSet.getString("zipcode"));
				employee.setDob(resultSet.getDate("dob"));

			}

			resultSet.close();
			preparedStatement.close();

		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return employee;
	}

}
