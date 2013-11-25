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
		
		boolean addComma = false;
		
		if(customer.getEmailId() != null)
		{
			customerTableQuery += " emailId='"+customer.getEmailId()+"' ";
			addComma = true;
		}
		if(customer.getFirstName() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " firstname='"+customer.getFirstName()+"' ";
			addComma = true;
		}
		if(customer.getLastName() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " lastname='"+customer.getLastName()+"' ";
			addComma = true;
		}
		if(customer.getPassportNum() != null)
		{
			customerTableQuery += (addComma ? "," : "") +" passportNum='"+customer.getPassportNum()+"' ";
			addComma = true;
		}
		if(customer.getNationality() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " nationality='"+customer.getNationality()+"' ";
			addComma = true;
		}
		if(customer.getAddress() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " address='"+customer.getAddress()+"' ";
			addComma = true;
		}
		if(customer.getCity() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " city='"+customer.getCity()+"' ";
			addComma = true;
		}
		if(customer.getState() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " state='"+customer.getState()+"' ";
			addComma = true;
		}
		if(customer.getZipcode() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " zipcode='"+customer.getZipcode()+"' ";
			addComma = true;
		}
		if(customer.getDob() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " dob='"+dateFormat.format(customer.getDob())+"' ";
			addComma = true;
		}
		if(customer.getPassword() != null)
		{
			customerTableQuery += (addComma ? "," : "") + " password="+customer.getPassword()+" ";
			addComma = true;
		}
		
		customerTableQuery += " where customerId="+customer.getCustomerId();
		
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
