package goair.model.query.adminservices;

import goair.model.customer.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetCustomersForFlightQuery {
	
	public static Logger logger = Logger.getLogger(GetCustomersForFlightQuery.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Customer[] searchCustomersForFlight(int flightId,
			Connection connection)
	{
		// Create the query that will search the customers in a flight.
		String query = null;

		ResultSet resultSet = null;  
		Statement statement = null;
		List<Customer> passengerList = new ArrayList<Customer>();

		try{

			statement = connection.createStatement();  
			
			// Get All Customers or Passengers for a flight
				query = "select c.customerId, emailId, firstName, lastName,"
						+ "gender, passportNum, nationality, "
						+ "address, city, state, zipcode,"
						+ "dob "
						+ "from reservation r, customer c "
						+ "where r.customerId =  c.customerId and r.flightId = "
						+ flightId;

				logger.info("Get all the passengers(customers) for flight with id : " 
						+ flightId+", query : "+query);

				statement = connection.createStatement();  
				resultSet = statement.executeQuery(query);

				Customer passenger = null;
				while (resultSet.next()) 
				{
					passenger = new Customer();

					passenger.setCustomerId(resultSet.getString("customerId"));
					passenger.setEmailId(resultSet.getString("emailId"));
					passenger.setFirstName(resultSet.getString("firstName"));
					passenger.setLastName(resultSet.getString("lastName"));
					passenger.setGender(resultSet.getString("gender"));
					passenger.setPassportNum(resultSet.getString("passportNum"));
					passenger.setNationality(resultSet.getString("nationality"));
					passenger.setAddress(resultSet.getString("address"));
					passenger.setCity(resultSet.getString("city"));
					passenger.setState(resultSet.getString("state"));
					passenger.setZipcode(resultSet.getString("zipcode"));
					passenger.setDob(resultSet.getTimestamp("dob"));

					passengerList.add(passenger);
				}

				resultSet.close();
				statement.close();
			
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return passengerList.toArray(new Customer[passengerList.size()]);
	}
}
