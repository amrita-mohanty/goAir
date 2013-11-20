package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class EditCustomerQuery {
	
	public static Logger logger = Logger.getLogger(EditCustomerQuery.class);
	
	/**
	 * Edit the flight
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editCustomer(Customer customer, Connection connection)
	{
		String customerTableQuery = "update customer set "  
				+ "emailId=?, " 
				+ "firstname=?, lastname=?, gender=?, passportNum=?, " 
				+ "nationality=?, address=?, city=?," 
				+ "state=?, zipcode=?, dob=? where customerId=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Update query for customer table : "+customerTableQuery);
			
			preparedStatement = connection.prepareStatement(customerTableQuery);
			preparedStatement.setString(1, customer.getEmailId());
			preparedStatement.setString(2, customer.getFirstName());
			preparedStatement.setString(3, customer.getLastName());
			preparedStatement.setString(4, customer.getGender());
			preparedStatement.setString(5, customer.getPassportNum());
			preparedStatement.setString(6, customer.getNationality());
			preparedStatement.setString(7, customer.getAddress());
			preparedStatement.setString(8, customer.getCity());
			preparedStatement.setString(9, customer.getState());
			preparedStatement.setString(10, customer.getZipcode());
			preparedStatement.setDate(11, new Date(customer.getDob().getTime()));
			preparedStatement.setInt(12, customer.getCustomerId());
			
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
