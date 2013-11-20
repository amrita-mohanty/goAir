package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class AddEmployeeQuery {
	
	public static Logger logger = Logger.getLogger(AddEmployeeQuery.class);
	
	/**
	 * Add a new Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addNewEmployee(Employee employee, Connection connection)
	{
		String employeeTableQuery = "insert into employee("
				+ "emailId, "
				+ "firstname, lastname, gender, airlineName, "
				+ "jobDesc, position, hireDate, address, city,"
				+ "state, zipcode, dob) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Insert query for Employee table : "+employeeTableQuery);
			
			preparedStatement = connection.prepareStatement(employeeTableQuery);
			preparedStatement.setString(1, employee.getEmailId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getGender());
			preparedStatement.setString(5, employee.getAirlineName());
			preparedStatement.setString(6, employee.getJobDesc());
			preparedStatement.setString(7, employee.getPosition());
			preparedStatement.setDate(8, new Date(employee.getHireDate().getTime()));
			preparedStatement.setString(9, employee.getAddress());
			preparedStatement.setString(10, employee.getCity());
			preparedStatement.setString(11, employee.getState());
			preparedStatement.setString(12, employee.getZipcode());
			preparedStatement.setDate(13, new Date(employee.getDob().getTime()));
			
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
