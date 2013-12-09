package goair.model.query.adminservices;

import goair.cache.ObjectCache;
import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetAllCustomersForAdminQuery {
	
	public static Logger logger = Logger.getLogger(GetAllCustomersForAdminQuery.class);
	
	/**
	 * This method will get all the customers in system for a Admin
	 * @return Customer[] 
	 */
	public Customer[] getAllCustomersForAdmin(Connection connection)
	{
		List<Customer> customers = new ArrayList<Customer>();

		String query = "select customerid, emailId, password,"
				+ "firstname, lastname, gender, passportNum, "
				+ "nationality, address, city,"
				+ "state, zipcode, dob "
				+ "from customer where currentStatus = 'Active'";

		logger.info("Get all the customers : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Customer customer = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				String customerId = resultSet.getString("customerId");

				if(ObjectCache.cacheObj.get("customer:" + customerId) == null) {
					
				customer = new Customer();

				customer.setCustomerId(resultSet.getString("customerid"));
				customer.setPassword(resultSet.getString("password"));
				customer.setEmailId(resultSet.getString("emailId"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setGender(resultSet.getString("gender"));
				customer.setPassportNum(resultSet.getString("passportNum"));
				customer.setNationality(resultSet.getString("nationality"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
				customer.setZipcode(resultSet.getString("zipcode"));
				customer.setDob(resultSet.getDate("dob"));
				
				ObjectCache.cacheObj.put("customer:" + customerId, customer);
				}
				else {
					customer = (Customer) ObjectCache.cacheObj.get("customer:" + customerId);
				}

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

}
