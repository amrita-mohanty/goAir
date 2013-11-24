package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class EditEmployeeQuery {
	
	public static Logger logger = Logger.getLogger(EditEmployeeQuery.class);
	
	/**
	 * Edit the Employee
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editEmployee(Employee employee, Connection connection)
	{
		String employeeTableQuery = "update employee set "  
				+ "emailId=?, "
				+ "firstname=?, lastname=?, gender=?, airlineName=?, "
				+ "jobDesc=?, position=?, hireDate=?, address=?, city=?,"
				+ "state=?, zipcode=?, dob=? where employeeId=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Update query for customer table : "+employeeTableQuery);
			
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
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while update.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
