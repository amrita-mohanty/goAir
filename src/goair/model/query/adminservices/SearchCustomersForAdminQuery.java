package goair.model.query.adminservices;

import goair.model.customer.Customer;
import goair.util.SearchParametersForCustomers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchCustomersForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchCustomersForAdminQuery.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * This method will get all the customers in system for a Admin based on the search criteria:
	 * @return Customer[] 
	 */
	public Customer[] searchCustomersForAdmin(Connection connection, SearchParametersForCustomers searchParameters)
	{
		List<Customer> customers = new ArrayList<Customer>();
		logger.info("Search Parameters to get all the active customers based on the search criteria::: " + searchParameters.toString());
		
		String query = createSqlQuery(searchParameters);
		logger.info("Get all the active customers based on the search criteria:: " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Customer customer = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				customer = new Customer();

				customer.setCustomerId(resultSet.getString("customerid"));
				customer.setEmailId(resultSet.getString("emailId"));
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setGender(resultSet.getString("gender"));
				customer.setPassportNum(resultSet.getString("passportNum"));
				customer.setNationality(resultSet.getString("nationality"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
				customer.setZipcode(resultSet.getString("zipcode"));
				customer.setDob(resultSet.getDate("dob"));

				customers.add(customer);
			}

			resultSet.close();
			statement.close();
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return customers.toArray(new Customer[customers.size()]);
	}
	
	public String createSqlQuery(SearchParametersForCustomers searchParam){

		String query = "select customerid, emailId, "
				+ "firstName, lastName, gender, passportNum, "
				+ "nationality, address, city,"
				+ "state, zipcode, dob "
				+ "from customer where currentStatus = 'Active'";
		
		 if(searchParam != null){
			 
			 if (searchParam.getCustomerId() != null && !searchParam.getCustomerId().equals("")) {
				 query = query + " and customerId='" + searchParam.getCustomerId()+"'";
			 }
			 if (searchParam.getPassportNum() != null && !searchParam.getPassportNum().equals("")) {
				 query = query + " and passportNum='" + searchParam.getPassportNum()+"'";
			 }
			 if (searchParam.getNationality() != null && !searchParam.getNationality().equals("")) {
				 query = query + " and nationality='" + searchParam.getNationality()+"'";
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
