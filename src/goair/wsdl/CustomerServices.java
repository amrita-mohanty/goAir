package goair.wsdl;

import org.apache.log4j.Logger;

import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.query.AdminServiceQueries;
import goair.model.query.CustomerServiceQueries;
import goair.model.reservation.Reservation;
import goair.util.CheckValidity;
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
		logger.info("Add a Customer : "+ customer.toString());
		boolean isValidZipcode = true;
		int returncode = -1;
		try{
			if(customer !=null){
				String employeeId = customer.getCustomerId();
				boolean isValidEmployeeId = CheckValidity.isEmployeeIdValid(employeeId);
				if(isValidEmployeeId){
					if(customer.getZipcode() != null && !customer.getZipcode().trim().equals("")){
						isValidZipcode = CheckValidity.isZipValid(customer.getZipcode());
					}
					
					if(isValidZipcode){
						returncode =  adminServiceQueries.addCustomer(customer);
					}
					else{
						returncode = -3; //Invalid zipcode
					}		
				}
				else{
					returncode = -4; // Error code for invalid customer-id
				}
			}
			return returncode;		
		}		
		catch(Exception ex){
			ex.printStackTrace();
			return -2;
		}
	}
	
	/*
	 * This method Check whether a customer is valid or not.
	 * If valid, then return the details of the customer set in the Customer bean
	 * else returns null;
	 */
	public Customer customerLogin(String emailId,String password) {
		logger.info("Validate a Customer : email : " + emailId + ", password : " + password);
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
		// Need source, destination and date of flying to run this search
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
		int returncode = -1;
		try{
			if(reservation !=null){
				String creditCardcString = reservation.getCreditCardNumber();
				boolean isvaidCreditCard = CheckValidity.isCreditCardValid(creditCardcString);
				if(isvaidCreditCard){
					Long.parseLong(creditCardcString);				
					returncode = adminServiceQueries.addReservation(reservation);
				}
				else{
					returncode = -3; // Error code for invalid credit-card number
				}
			}
			return returncode;		
		}		
		catch(Exception ex){
			ex.printStackTrace();
			return -2;
		}
	}
	
	/*
	 * The searchParameter.customerId attribute should contain the customerId in order 
	 * to view all the reservations of the particular customer
	 */
	public Reservation[] viewAllReservations(SearchParametersForReservation searchParam) {
		logger.info("Search all Reservations for a customer: "+searchParam.toString());
		return adminServiceQueries.searchReservationsForAdmin(searchParam);
	}
	
	/**
	 * Cancel Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int cancelReservation(Reservation reservation){
		logger.info("Cancel a Reservation : "+ reservation.toString());
		return adminServiceQueries.cancelReservation(reservation);
	}
	
	public static void main(String[] args){
		CheckValidity.isCreditCardValid("10000000ssss0000000");
	}
}