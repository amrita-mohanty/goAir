package goair.model.query;

import goair.model.flight.Flight;
import goair.model.query.employeeservices.EmployeeLoginQuery;
import goair.model.query.employeeservices.ViewEmployeeFlightsQuery;
import goair.util.DbConnection;
import goair.util.SearchParametersForFlights;
import goair.model.employee.Employee;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class EmployeeServiceQueries {
public static Logger logger = Logger.getLogger(AdminServiceQueries.class);
	
	Connection connection 		= null;
	
	// Query classes
	ViewEmployeeFlightsQuery flightsForEmployee = null;
	EmployeeLoginQuery employeeLogin = null;
	
	public EmployeeServiceQueries(){
		connection 					= DbConnection.getInstance().getConnection();
	}
	
	/*
	 * This method Check whether a employee is valid or not.
	 * If valid, then return the details of the employee set in the Employee bean.
	 */
	public Employee validateEmployeeLogin(String emailId,String password)
	{
		if(employeeLogin == null)
		{
			employeeLogin = new EmployeeLoginQuery();
		}
		logger.info("validateEmployeeLogin with params : "+ emailId + ", " + password);
		Employee emp = employeeLogin.validateEmployeeLogin(emailId, password, connection);
		if(emp == null) {
			return null;
		}
		else {
			return emp;
		}
	}
	
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] viewEmployeeFlights(SearchParametersForFlights searchParameters)
	{
		if(flightsForEmployee == null)
		{
			flightsForEmployee = new ViewEmployeeFlightsQuery();
		}
		logger.info("view Employee Flights with employeeId : "+searchParameters.getEmployeeId());
		return flightsForEmployee.viewEmployeeFlights(searchParameters, connection);
	}
	
	
}//class
