package goair.wsdl;

import org.apache.log4j.Logger;

import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.payment.PaymentBean;
import goair.model.query.AdminServiceQueries;
import goair.model.query.CustomerServiceQueries;
import goair.model.reservation.Reservation;
import goair.util.SearchParametersForFlights;
import goair.util.SearchParametersForReservation;

public class CustomerServices extends AirlineServices{
	
	public static Logger logger = Logger.getLogger(CustomerServices.class);
	public CustomerServiceQueries customerServiceQueries = null; 
	public AdminServiceQueries adminServiceQueries = null;
	
	public CustomerServices() {
		customerServiceQueries = new CustomerServiceQueries();
		adminServiceQueries = new AdminServiceQueries();
	}
	

	/**
	 * Adds a new Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addCustomer(Customer customer)
	{
		logger.info("Add a Customer : "+customer.toString());
		return adminServiceQueries.addCustomer(customer);
	}

	/*
	 * This method Check whether a customer is valid or not.
	 * If valid, then return the details of the customer set in the Customer bean
	 * else returns null;
	 */
	public Customer customerLogin(String emailId,String password) {
		return customerServiceQueries.validateCustomerLogin(emailId, password);
	}
	
	/**
	 * Edit Customer info in the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editCustomer(Customer customer)
	{
		logger.info("Edit a Customer : "+customer.toString());
		return adminServiceQueries.editCustomer(customer);
	}


	/**
	 * Search flights - This method searches all the flights with a given criteria
	 * and returns only those columns which a customer can view
	 * @return Flight[] - Return array of flights.
	 *  
	 */
	public Flight[] searchFlights(SearchParametersForFlights searchFlightParam)
	{
		logger.info("Search Flights for Customer : "+ searchFlightParam.toString());
		Flight[] searchResult = null;
		searchResult = customerServiceQueries.searchFlightsForCustomer(searchFlightParam);

		return searchResult;
	}

	/**
	 * Add a new Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addReservation(Reservation reservation)
	{
		logger.info("Add a Reservation : "+reservation.toString());
		return adminServiceQueries.addReservation(reservation);
	}
	
	/*
	 * The searchParameter.customerId attribute should contain the customerId in order 
	 * to view all the reservations of the particular customer
	 */
	public Reservation[] viewAllReservations(SearchParametersForReservation searchParam) {
		return adminServiceQueries.searchReservationsForAdmin(searchParam);
	}
	
	/*public int cancelReservation(){
		return 0;
	}*/

	
	public static void main(String args[]){
	}

}