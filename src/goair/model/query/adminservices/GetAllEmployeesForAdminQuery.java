package goair.model.query.adminservices;

import goair.cache.ObjectCache;
import goair.model.employee.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetAllEmployeesForAdminQuery {
	
	public static Logger logger = Logger.getLogger(GetAllEmployeesForAdminQuery.class);
	
	/**
	 * This method will get all the employee in system for a Admin
	 * @return Employee[] 
	 */
	public Employee[] getAllEmployeesForAdmin(Connection connection)
	{
		List<Employee> employees = new ArrayList<Employee>();

		String query = "select employeeid, emailId, "
				+ "firstname, lastname, gender, airlineName, "
				+ "jobDesc, position, hireDate, address, city,"
				+ "state, zipcode, dob "
				+ "from employee where currentStatus = 'Active'";

		logger.info("Get all active employees : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Employee employee = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				String employeeid = resultSet.getString("employeeid");
				if(ObjectCache.cacheObj.get("employee:" + employeeid) == null) {
					
				employee = new Employee();

				employee.setEmployeeId(resultSet.getString("employeeid"));
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
				
				ObjectCache.cacheObj.put("employee:" + employeeid, employee);
				}
				else {
					employee = (Employee) ObjectCache.cacheObj.get("employee:" + employeeid);
				}

				employees.add(employee);
			}

			resultSet.close();
			statement.close();
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return employees.toArray(new Employee[employees.size()]);
	}

}
