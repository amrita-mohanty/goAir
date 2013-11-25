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

		boolean addComma = false;
		if(employee.getEmailId() != null)
		{
			employeeTableQuery += " emailId='"+employee.getEmailId()+"' ";
			addComma = true;
		}
		if(employee.getFirstName() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " firstname='"+employee.getFirstName()+"' ";
			addComma = true;
		}
		if(employee.getLastName() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " lastname='"+employee.getLastName()+"' ";
			addComma = true;
		}
		if(employee.getJobDesc() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " jobDesc='"+employee.getJobDesc()+"' ";
			addComma = true;
		}
		if(employee.getPosition() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " position='"+employee.getPosition()+"' ";
			addComma = true;
		}
		if(employee.getHireDate() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " hireDate='"+dateFormat.format(employee.getHireDate())+"' ";
			addComma = true;
		}
		if(employee.getAddress() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " address='"+employee.getAddress()+"' ";
			addComma = true;
		}
		if(employee.getCity() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " city='"+employee.getCity()+"' ";
			addComma = true;
		}
		if(employee.getState() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " state='"+employee.getState()+"' ";
			addComma = true;
		}
		if(employee.getZipcode() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " zipcode='"+employee.getZipcode()+"' ";
			addComma = true;
		}
		if(employee.getDob() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " dob='"+dateFormat.format(employee.getDob())+"' ";
			addComma = true;
		}
		if(employee.getPassword() != null)
		{
			employeeTableQuery += (addComma ? "," : "") + " password="+employee.getPassword()+" ";
			addComma = true;
		}
		
		employeeTableQuery += " where employeeId="+employee.getEmployeeId();
		
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
