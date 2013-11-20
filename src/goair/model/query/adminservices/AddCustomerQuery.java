package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class AddCustomerQuery {
	
	public static Logger logger = Logger.getLogger(AddCustomerQuery.class);
	
	/**
	 * Add a new Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addNewCustomer(Customer customer, Connection connection)
	{
		String customerTableQuery = "insert into customer("
				+ "emailId, "
				+ "firstname, lastname, gender, passportNum, "
				+ "nationality, address, city,"
				+ "state, zipcode, dob) values (?,?,?,?,?,?,?,?,?,?,?) ";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Insert query for customer table : "+customerTableQuery);
			
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