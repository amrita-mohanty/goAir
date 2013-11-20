package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class DeleteEmployeeQuery {
	
	public static Logger logger = Logger.getLogger(DeleteEmployeeQuery.class);
	
	/**
	 * Edit the Employee
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 0, failure : -1
	 */
	public int deleteEmployee(Employee employee, Connection connection)
	{
		String deleteEmployeeTableQuery = "update employee set currentStatus = 'Deleted' where employeeId=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Delete query for customer table : "+deleteEmployeeTableQuery);
			
			preparedStatement = connection.prepareStatement(deleteEmployeeTableQuery);
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while delete.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
