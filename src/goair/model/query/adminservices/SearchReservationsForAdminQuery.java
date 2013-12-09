package goair.model.query.adminservices;

import goair.model.reservation.Reservation;
import goair.util.SearchParametersForReservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchReservationsForAdminQuery {
	
	public static Logger logger = Logger.getLogger(SearchReservationsForAdminQuery.class);
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String query = null;
	
	/**
	 * This method will get all the customers in system for a Admin
	 * @return Reservation[] 
	 */
	public Reservation[] searchReservationForAdmin(Connection connection, 
			SearchParametersForReservation searchParameters)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();

		/*String query = "select "
					+ "pnr, customerId, "
					+ "flightId, numberOfSeatsBooked, "
					+ "creditCardNumber, dateOfBooking, "
					+ "dateOfFlying, totalPrice "
					+ "from reservation ";*/
		createSqlQuery(searchParameters);
		
		logger.info("Get all the reservation : " + query);

		ResultSet resultSet = null;  
		Statement statement = null;

		Reservation reservation = null;

		try{

			statement = connection.createStatement();  
			resultSet = statement.executeQuery(query);  

			while (resultSet.next()) 
			{
				reservation = new Reservation();
				
				reservation.setCustomerId(resultSet.getString("customerid"));
				reservation.setCustomerFirstName(resultSet.getString("firstName"));
				reservation.setCustomerLastName(resultSet.getString("lastName"));
				
				reservation.setFlightId(resultSet.getInt("flightId"));
				reservation.setAirlineName(resultSet.getString("airlineName"));
				reservation.setFlightName(resultSet.getString("flightName"));
				reservation.setSource(resultSet.getString("source"));
				reservation.setDestination(resultSet.getString("destination"));
				reservation.setDepartureTime(resultSet.getTimestamp("departureTime"));
				
				reservation.setPnr(resultSet.getInt("pnr"));
				reservation.setNumberOfSeatsBooked(resultSet.getInt("numberOfSeatsBooked"));
				reservation.setCreditCardNumber(resultSet.getString("creditCardNumber"));
				reservation.setDateOfBooking(resultSet.getDate("dateOfBooking"));
				reservation.setDateOfFlying(resultSet.getDate("dateOfFlying"));
				reservation.setTotalPrice(resultSet.getInt("totalPrice"));
				
				reservations.add(reservation);
			}

			resultSet.close();
			statement.close();
		} 
		catch (Exception e) 
		{  
			logger.info("Error while running the query.\n"+e.getMessage());
			e.printStackTrace();  
		}

		return reservations.toArray(new Reservation[reservations.size()]);
	}
	
	public void createSqlQuery(SearchParametersForReservation searchParam){
		 query = "select "
				+ "r.pnr, r.customerId, "
				+ "r.flightId, r.numberOfSeatsBooked, "
				+ "r.creditCardNumber, r.dateOfBooking, "
				+ "r.dateOfFlying,r.totalPrice,c.firstName,c.lastName,"
				+ "f.airlineName,f.flightname,f.source,f.destination,f.departureTime "
				+ "from reservation r, flight f, customer c where r.flightId = f.flightid and "
				+ "r.customerId = c.customerId and r.currentStatus = 'Active' ";
		 
		 if(searchParam != null){
			 if(searchParam.pnr != 0){
				 query = query + " and r.pnr=" + searchParam.getPnr();
			 }
			 
			 if(searchParam.customerId != 0){
				 query = query + " and r.customerId=" + searchParam.getCustomerId();
			 }
			 
			 if(searchParam.flightId != 0){
				 query = query + " and r.flightId=" + searchParam.getFlightId();
			 }
			 
			 if(searchParam.dateOfFlying != null){
				 query = query + " and r.dateOfFlying=" + dateFormat.format(searchParam.getDateOfFlying());
			 }
		 }
	}

}
