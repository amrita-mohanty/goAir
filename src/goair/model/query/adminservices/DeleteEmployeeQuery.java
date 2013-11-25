package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.Statement;

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
		String deleteEmployeeTableQuery = "update employee set currentStatus = 'Deleted' where employeeId="
				+employee.getEmployeeId();

		Statement statement = null;
		try
		{
			logger.info("Delete query for employee table : "+deleteEmployeeTableQuery);
			
			statement = connection.createStatement();
			statement.execute(deleteEmployeeTableQuery);
			statement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while deleting employee.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
