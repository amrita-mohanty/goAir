package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class DeleteCustomerQuery {
	
	public static Logger logger = Logger.getLogger(DeleteCustomerQuery.class);
	
	/**
	 * Edit the Customer
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 0, failure : -1
	 */
	public int deleteCustomer(Customer customer, Connection connection)
	{
		String deleteCustomerTableQuery = "update customer set currentStatus = 'Deleted' where customerId=?";

		PreparedStatement preparedStatement = null;
		try
		{
			logger.info("Delete query for customer table : "+deleteCustomerTableQuery);
			
			preparedStatement = connection.prepareStatement(deleteCustomerTableQuery);
			preparedStatement.setInt(1, customer.getCustomerId());
			preparedStatement.execute();
			preparedStatement.close();
			
			return 0;
		}
		catch (Exception e)
		{
			logger.error("Error while delete.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
