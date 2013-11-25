package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DeleteCustomerQuery {
	
	public static Logger logger = Logger.getLogger(DeleteCustomerQuery.class);
	
	/**
	 * Edit the Customer
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * In delete we just update the status to Deleted instead of removing the whole row
	 * success : 1, failure : -1
	 */
	public int deleteCustomer(Customer customer, Connection connection)
	{
		String deleteCustomerTableQuery = "update customer set currentStatus = 'Deleted' where customerId="
				+customer.getCustomerId();

		Statement statement = null;
		try
		{
			logger.info("Delete query for customer table : "+deleteCustomerTableQuery);
			
			statement = connection.createStatement();
			statement.execute(deleteCustomerTableQuery);
			statement.close();
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while deleting flight.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
	
}
