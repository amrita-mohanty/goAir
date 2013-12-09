package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class EditCustomerQuery {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Logger logger = Logger.getLogger(EditCustomerQuery.class);
	
	/**
	 * Edit Customer info in the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editCustomer(Customer customer, Connection connection)
	{
		String customerTableQuery = "update customer set ";
		
		if(customer.getEmailId() != null)
		{
			customerTableQuery += ", emailId='"+customer.getEmailId()+"' ";
		}
		if(customer.getFirstName() != null)
		{
			customerTableQuery += ", firstname='"+customer.getFirstName()+"' ";
		}
		if(customer.getLastName() != null)
		{
			customerTableQuery += ", lastname='"+customer.getLastName()+"' ";
		}
		if(customer.getPassportNum() != null)
		{
			customerTableQuery += ", passportNum='"+customer.getPassportNum()+"' ";
		}
		if(customer.getNationality() != null)
		{
			customerTableQuery += ", nationality='"+customer.getNationality()+"' ";
		}
		if(customer.getAddress() != null)
		{
			customerTableQuery += ", address='"+customer.getAddress()+"' ";
		}
		if(customer.getCity() != null)
		{
			customerTableQuery += ", city='"+customer.getCity()+"' ";
		}
		if(customer.getState() != null)
		{
			customerTableQuery += ", state='"+customer.getState()+"' ";
		}
		if(customer.getZipcode() != null)
		{
			customerTableQuery += ", zipcode='"+customer.getZipcode()+"' ";
		}
		if(customer.getDob() != null)
		{
			customerTableQuery += ", dob='"+dateFormat.format(customer.getDob())+"' ";
		}
		if(customer.getPassword() != null)
		{
			customerTableQuery += ", password="+customer.getPassword()+" ";
		}
		
		customerTableQuery += " where customerId='"+customer.getCustomerId()+"'";
		customerTableQuery = customerTableQuery.replace("set ,", "set ");
		
		Statement statement = null;
		try
		{
			logger.info("Update query for customer table : "+customerTableQuery);
			
			statement = connection.createStatement();
			statement.executeUpdate(customerTableQuery);
			
			return 1;
		}
		catch (Exception e)
		{
			logger.error("Error while insert.\n"+e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}
}
