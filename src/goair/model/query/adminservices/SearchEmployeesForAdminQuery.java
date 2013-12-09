package goair.model.query.adminservices;

import goair.model.employee.Employee;
import goair.util.SearchParametersForEmployees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchEmployeesForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchEmployeesForAdminQuery.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * This method will get all the employee in system for a Admin based on the search criteria
	 * @return Employee[] 
	 */
	public Employee[] searchEmployeesForAdmin(Connection connection, SearchParametersForEmployees searchParameters)
	{
		List<Employee> customers = new ArrayList<Employee>();
		logger.info("Search Parameters to get all the active employees based on the search criteria::: " + searchParameters.toString());
		
		String query = createSqlQuery(searchParameters);
		logger.info("Get all the active employees based on the search criteria: " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Employee employee = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				employee = new Employee();

				employee.setEmployeeId(resultSet.getString("employeeid"));
				employee.setEmailId(resultSet.getString("emailId"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
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

	public String createSqlQuery(SearchParametersForEmployees searchParam){
		 String query = "select employeeid, emailId, "
					+ "firstname, lastname, gender, airlineName, "
					+ "jobDesc, position, hireDate, address, city,"
					+ "state, zipcode, dob "
					+ "from employee where currentStatus = 'Active'";
		 
		 if (searchParam != null) {
			 if (searchParam.getEmployeeId() != null && !searchParam.getEmployeeId().equals("")) {
				 query = query + " and employeeId='" + searchParam.getEmployeeId()+"'";
			 }
			 if (searchParam.getAirlineName() != null && !searchParam.getAirlineName().equals("")) {
				 query = query + " and airlineName='" + searchParam.getAirlineName()+"'";
			 }
			 if (searchParam.getJobDesc() != null && !searchParam.getJobDesc().equals("")) {
				 query = query + " and jobDesc='" + searchParam.getJobDesc()+"'";
			 }
			 if (searchParam.getPosition() != null && !searchParam.getPosition().equals("")) {
				 query = query + " and position='" + searchParam.getPosition()+"'";
			 }
			 if (searchParam.getHireDate() != null && !searchParam.getHireDate().equals("")) {
				 query = query + " and hireDate='" + dateFormat.format(searchParam.getHireDate())+"'";
			 }
			 
			//Person related attributes	
			 if (searchParam.getEmailId() != null && !searchParam.getEmailId().equals("")) {
				 query = query + " and emailId='" + searchParam.getEmailId()+"'";
			 }
			 if (searchParam.getPassword() != null && !searchParam.getPassword().equals("")) {
				 query = query + " and password='" + searchParam.getPassword()+"'";
			 }
			 if (searchParam.getFirstName() != null && !searchParam.getFirstName().equals("")) {
				 query = query + " and firstName='" + searchParam.getFirstName()+"'";
			 }
			 if (searchParam.getLastName() != null && !searchParam.getLastName().equals("")) {
				 query = query + " and lastName='" + searchParam.getLastName()+"'";
			 }
			 if (searchParam.getGender() != null && !searchParam.getGender().equals("")) {
				 query = query + " and gender='" + searchParam.getGender()+"'";
			 }
			 if (searchParam.getAddress() != null && !searchParam.getAddress().equals("")) {
				 query = query + " and address='" + searchParam.getAddress()+"'";
			 }
			 if (searchParam.getCity() != null && !searchParam.getCity().equals("")) {
				 query = query + " and city='" + searchParam.getCity()+"'";
			 }
			 if (searchParam.getState() != null && !searchParam.getState().equals("")) {
				 query = query + " and state='" + searchParam.getState()+"'";
			 }
			 if (searchParam.getZipcode() != null && !searchParam.getZipcode().equals("")) {
				 query = query + " and zipcode='" + searchParam.getZipcode()+"'";
			 }
			 if (searchParam.getDob() != null && !searchParam.getDob().equals("")) {
				 query = query + " and dob='" +  dateFormat.format(searchParam.getDob())+"'";
			 }
		 }
		 
		 return query;
	}
}
