package goair.wsdl;

import java.util.Date;

import org.apache.log4j.Logger;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.query.AdminServiceQueries;
import goair.model.reservation.Reservation;
import goair.util.CheckValidity;
import goair.util.SearchParametersForCustomers;
import goair.util.SearchParametersForEmployees;
import goair.util.SearchParametersForFlights;
import goair.util.SearchParametersForReservation;

public class AdminServices {
	
	public static Logger logger = Logger.getLogger(AdminServices.class);
	
	//When you login as admin you see following hyper links
	// 1. Search flight - when you get a searched flight you get option to edit / delete it.
	// 2. View all flights - when you view a flight you get option to edit / delete it.
	// 3. Add a flight
	
	// 4. Search customer - when you get a searched customer you get option to edit / delete it.
	// 5. View all passengers - when you view a customer you get option to edit / delete it.
	// 6. Add a customer 
	
	// 7. Search employee - when you get a searched employee you get option to edit / delete it.
	// 8. View all employee - when you view a employee you get option to edit / delete it.
	// 9. Add a employee 
	
	// 10. View all the bookings.
	// 11. Search bookings.
	
	// View flight page which will have flight details with availability.
	// View customer page which will have customer details with booking history and all.
	// View employee page which will have employee details with profile and what all the flights
			// he/she is serving with histories.
	

	public AdminServiceQueries adminServiceQueries = null;
	
	public AdminServices()
	{
		adminServiceQueries = new AdminServiceQueries();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return adminServiceQueries.searchFlightsForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] getAllFlightsForAdmin()
	{
		logger.info("searchAllFlightsForAdmin");
		return adminServiceQueries.getAllFlightsForAdmin();
	}
	
	/**
	 * Add a new flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addFlight(Flight flight)
	{
		logger.info("Add a flight : "+flight.toString());
		return adminServiceQueries.addFlight(flight);
	}
	
	/**
	 * Edit flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editFlight(Flight flight)
	{
		logger.info("Edit a flight : "+flight.toString());
		return adminServiceQueries.editFlight(flight);
	}
	
	/**
	 * Delete flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int deleteFlight(Flight flight)
	{
		logger.info("Delete a flight : "+flight.toString());
		return adminServiceQueries.deleteFlight(flight);
	}
	
	/**
	 * This method will get the Customer based on search parameters passed to it
	 * @return Customer[] 
	 */
	public Customer[] searchCustomersForAdmin(SearchParametersForCustomers searchParameters)
	{
		logger.info("searchCustomersForAdmin with params : "+searchParameters.toString());
		return adminServiceQueries.searchCustomersForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Customer based on search parameters passed to it
	 * @return Customer[] 
	 */
	public Customer[] getAllCustomersForAdmin()
	{
		logger.info("searchAllCustomersForAdmin");
		return adminServiceQueries.getAllCustomersForAdmin();
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
	 * Delete Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int deleteCustomer(Customer customer)
	{
		logger.info("Delete a Customer : "+customer.toString());
		return adminServiceQueries.deleteCustomer(customer);
	}
	
	/**
	 * This method will get the Employee based on search parameters passed to it
	 * @return Employee[] 
	 */
	public Employee[] searchEmployeesForAdmin(SearchParametersForEmployees searchParameters)
	{
		logger.info("searchEmployeesForAdmin with params : "+searchParameters.toString());
		return adminServiceQueries.searchEmployeesForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Employee based on search parameters passed to it
	 * @return Employee[] 
	 */
	public Employee[] getAllEmployeesForAdmin()
	{
		logger.info("searchAllEmployeesForAdmin");
		return adminServiceQueries.getAllEmployeesForAdmin();
	}
	
	/**
	 * Add a new Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addEmployee(Employee employee)
	{
		logger.info("Add a Employee : "+employee.toString());
		boolean isValidZipcode = true;
		int returncode = -1;
		try{
			if(employee !=null){
				String employeeId = employee.getEmployeeId();
				boolean isValidEmployeeId = CheckValidity.isEmployeeIdValid(employeeId);
				if(isValidEmployeeId){
					if(employee.getZipcode() != null && !employee.getZipcode().trim().equals("")){
						isValidZipcode = CheckValidity.isZipValid(employee.getZipcode());
					}
					
					if(isValidZipcode){
						returncode =  adminServiceQueries.addEmployee(employee);
					}
					else{
						returncode = -3; //Invalid zipcode
					}		
				}
				else{
					returncode = -4; // Error code for invalid employee-id
				}
			}
			return returncode;		
		}		
		catch(Exception ex){
			ex.printStackTrace();
			return -2;
		}
	}
	
	/**
	 * Edit Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editEmployee(Employee employee)
	{
		logger.info("Edit a Employee : "+employee.toString());
		return adminServiceQueries.editEmployee(employee);
	}
	
	/**
	 * Delete Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int deleteEmployee(Employee employee)
	{
		logger.info("Delete a Employee : "+employee.toString());
		return adminServiceQueries.deleteEmployee(employee);
	}
	
	/**
	 * This method will get the Reservation based on search parameters passed to it
	 * @return Reservation[] 
	 */
	public Reservation[] searchReservationsForAdmin(SearchParametersForReservation searchParameters)
	{
		logger.info("searchReservationsForAdmin with params : "+searchParameters.toString());
		return adminServiceQueries.searchReservationsForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Reservation based on search parameters passed to it
	 * @return Reservation[] 
	 */
	public Reservation[] getAllReservationsForAdmin()
	{
		logger.info("searchAllReservationsForAdmin");
		return adminServiceQueries.getAllReservationsForAdmin();
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
	
	/**
	 * Edit Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editReservation(Reservation reservation)
	{
		logger.info("Edit a Reservation : "+reservation.toString());
		return adminServiceQueries.editReservation(reservation);
	}
	
	/**
	 * Cancel Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int cancelReservation(Reservation reservation)
	{
		logger.info("Cancel a Reservation : "+reservation.toString());
		return adminServiceQueries.cancelReservation(reservation);
	}
	
	
	/*
	 * Get all the customers in a flight
	 * Input : dateOfFlying , flightId
	 */
	public Customer[] getCustomersForFlight(Date dateOfFlying,int flightId){
		logger.info("Get all customers in a Flight: "+ flightId + "," + dateOfFlying.toString());
		return adminServiceQueries.getCustomersForFlight(dateOfFlying, flightId);
	}
	
	/*
	 * Get all the employee in a flight
	 * Input : dateOfFlying , flightId
	 */
	public Employee[] getEmpployeesForFlight(Date dateOfFlying,int flightId){
		logger.info("Get all employees in a Flight: "+ flightId + "," + dateOfFlying.toString());
		return adminServiceQueries.getEmployeesForFlight(dateOfFlying, flightId);
	}
	
	public static void main(String[] args){
		CheckValidity.isEmployeeIdValid("409-18-2345");
	}
}
