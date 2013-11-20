package goair.model.query.adminservices;

import goair.model.employee.Employee;
import goair.util.SearchParametersForEmployees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchEmployeesForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchEmployeesForAdminQuery.class);
	
	/**
	 * This method will get all the employee in system for a Admin
	 * @return Employee[] 
	 */
	public Employee[] searchEmployeesForAdmin(Connection connection, SearchParametersForEmployees searchParameters)
	{
		List<Employee> customers = new ArrayList<Employee>();

		String query = "select employeeid, emailId, "
				+ "firstname, lastname, gender, airlineName, "
				+ "jobDesc, position, hireDate, address, city,"
				+ "state, zipcode, dob "
				+ "from employee ";

		logger.info("Get all the employees : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Employee employee = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				employee = new Employee();

				employee.setEmployeeId(resultSet.getInt("employeeid"));
				employee.setEmailId(resultSet.getString("emailId"));
				employee.setFirstName(resultSet.getString("firstname"));
				employee.setLastName(resultSet.getString("lastname"));
				employee.setGender(resultSet.getString("gender"));
				employee.setAirlineName(resultSet.getString("airlineName"));
				employee.setJobDesc(resultSet.getString("jobDesc"));
				employee.setPosition(resultSet.getString("position"));
				employee.setHireDate(resultSet.getDate("hireDate"));
				employee.setAddress(resultSet.getString("address"));
				employee.setCity(resultSet.getString("city"));
				employee.setState(resultSet.getString("state"));
				employee.setZipcode(resultSet.getString("zipcode"));
				employee.setDob(resultSet.getDate("dob"));

				customers.add(employee);
			}

			resultSet.close();
			statement.close();
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return customers.toArray(new Employee[customers.size()]);
	}

}
