package goair.model.query.customerservices;

import goair.model.customer.Customer;
import goair.model.query.adminservices.SearchFlightsForAdminQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class CustomerLoginQuery {
	public static Logger logger = Logger.getLogger(SearchFlightsForAdminQuery.class);

	/**
	 * This method will validate customer login credentials
	 * @return customer[] 
	 */
	public Customer validateCustomerLogin (String emailId,String password,Connection connection)
	{
		/* Check whether a customer is valid or not.
		 * If valid, then return the details of the customer 
		 * set in the Customer bean.
		 */

		String query = "select customerId, emailId, password, firstName, "
				+ "lastName, gender, passportNum, nationality, "
				+ "address, city, state, zipcode, dob "
				+ "from customer where currentStatus='Active' and emailId=? and password=?";

		logger.info("Validate customer login : " + query);

		ResultSet resultSet = null;  
		PreparedStatement preparedStatement = null;

		Customer customer = null;

		try{

			preparedStatement = connection.prepareStatement(query);  
			preparedStatement.setString(1,emailId);
			preparedStatement.setString(2,password);

			resultSet = preparedStatement.executeQuery();  

			while (resultSet.next()) 
			{
				customer = new Customer();

				customer.setCustomerId(resultSet.getString("customerId"));
				customer.setEmailId(resultSet.getString("emailId"));
				customer.setPassword(resultSet.getString("password"));
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

			}

			resultSet.close();
			preparedStatement.close();

		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return customer;
	}

}
