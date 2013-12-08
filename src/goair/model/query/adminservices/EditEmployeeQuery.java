package goair.model.query.adminservices;

import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class EditEmployeeQuery {
	
	public static Logger logger = Logger.getLogger(EditEmployeeQuery.class);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Edit the Employee
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editEmployee(Employee employee, Connection connection)
	{
		String employeeTableQuery = "update employee set ";  

		if(employee.getEmailId() != null)
		{
			employeeTableQuery += ", emailId='"+employee.getEmailId()+"' ";
		}
		if(employee.getFirstName() != null)
		{
			employeeTableQuery += ", firstname='"+employee.getFirstName()+"' ";
		}
		if(employee.getLastName() != null)
		{
			employeeTableQuery += ", lastname='"+employee.getLastName()+"' ";
		}
		if(employee.getJobDesc() != null)
		{
			employeeTableQuery += ", jobDesc='"+employee.getJobDesc()+"' ";
		}
		if(employee.getPosition() != null)
		{
			employeeTableQuery += ", position='"+employee.getPosition()+"' ";
		}
		if(employee.getHireDate() != null)
		{
			employeeTableQuery += ", hireDate='"+dateFormat.format(employee.getHireDate())+"' ";
		}
		if(employee.getAddress() != null)
		{
			employeeTableQuery += ", address='"+employee.getAddress()+"' ";
		}
		if(employee.getCity() != null)
		{
			employeeTableQuery += ", city='"+employee.getCity()+"' ";
		}
		if(employee.getState() != null)
		{
			employeeTableQuery += ", state='"+employee.getState()+"' ";
		}
		if(employee.getZipcode() != null)
		{
			employeeTableQuery += ", zipcode='"+employee.getZipcode()+"' ";
		}
		if(employee.getDob() != null)
		{
			employeeTableQuery += ", dob='"+dateFormat.format(employee.getDob())+"' ";
		}
		if(employee.getPassword() != null)
		{
			employeeTableQuery += ", password="+employee.getPassword()+" ";
		}
		
		employeeTableQuery += " where employeeId='"+employee.getEmployeeId()+"'";
		employeeTableQuery = employeeTableQuery.replace("set ,", "set ");
		
		Statement statement = null;
		try
		{
			logger.info("Update query for employee table : "+employeeTableQuery);
			
			statement = connection.createStatement();
			statement.executeUpdate(employeeTableQuery);
			
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
