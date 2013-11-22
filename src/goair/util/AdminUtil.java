package goair.util;

import org.apache.log4j.Logger;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.query.adminservices.AdminServiceQueries;
import goair.model.reservation.Reservation;

public class AdminUtil {
	
	public static Logger logger = Logger.getLogger(AdminUtil.class);
	
	public AdminServiceQueries adminServiceQuery = null;
	
	public AdminUtil()
	{
		adminServiceQuery = new AdminServiceQueries();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return adminServiceQuery.searchFlightsForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] getAllFlightsForAdmin()
	{
		logger.info("searchAllFlightsForAdmin");
		return adminServiceQuery.getAllFlightsForAdmin();
	}
	
	/**
	 * Add a new flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addFlight(Flight flight)
	{
		logger.info("Add a flight : "+flight.toString());
		return adminServiceQuery.addFlight(flight);
	}
	
	/**
	 * Edit flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editFlight(Flight flight)
	{
		logger.info("Edit a flight : "+flight.toString());
		return adminServiceQuery.editFlight(flight);
	}
	
	/**
	 * Delete flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int deleteFlight(Flight flight)
	{
		logger.info("Delete a flight : "+flight.toString());
		return adminServiceQuery.deleteFlight(flight);
	}
	
	/**
	 * This method will get the Customer based on search parameters passed to it
	 * @return Customer[] 
	 */
	public Customer[] searchCustomersForAdmin(SearchParametersForCustomers searchParameters)
	{
		logger.info("searchCustomersForAdmin with params : "+searchParameters.toString());
		return adminServiceQuery.searchCustomersForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Customer based on search parameters passed to it
	 * @return Customer[] 
	 */
	public Customer[] getAllCustomersForAdmin()
	{
		logger.info("searchAllCustomersForAdmin");
		return adminServiceQuery.getAllCustomersForAdmin();
	}
	
	/**
	 * Add a new Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addCustomer(Customer customer)
	{
		logger.info("Add a Customer : "+customer.toString());
		return adminServiceQuery.addCustomer(customer);
	}
	
	/**
	 * Edit Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editCustomer(Customer customer)
	{
		logger.info("Edit a Customer : "+customer.toString());
		return adminServiceQuery.editCustomer(customer);
	}
	
	/**
	 * Delete Customer to the system
	 * @param customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int deleteCustomer(Customer customer)
	{
		logger.info("Delete a Customer : "+customer.toString());
		return adminServiceQuery.deleteCustomer(customer);
	}
	
	/**
	 * This method will get the Employee based on search parameters passed to it
	 * @return Employee[] 
	 */
	public Employee[] searchEmployeesForAdmin(SearchParametersForEmployees searchParameters)
	{
		logger.info("searchEmployeesForAdmin with params : "+searchParameters.toString());
		return adminServiceQuery.searchEmployeesForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Employee based on search parameters passed to it
	 * @return Employee[] 
	 */
	public Employee[] getAllEmployeesForAdmin()
	{
		logger.info("searchAllEmployeesForAdmin");
		return adminServiceQuery.getAllEmployeesForAdmin();
	}
	
	/**
	 * Add a new Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addEmployee(Employee employee)
	{
		logger.info("Add a Employee : "+employee.toString());
		return adminServiceQuery.addEmployee(employee);
	}
	
	/**
	 * Edit Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editEmployee(Employee employee)
	{
		logger.info("Edit a Employee : "+employee.toString());
		return adminServiceQuery.editEmployee(employee);
	}
	
	/**
	 * Delete Employee to the system
	 * @param employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int deleteEmployee(Employee employee)
	{
		logger.info("Delete a Employee : "+employee.toString());
		return adminServiceQuery.deleteEmployee(employee);
	}
	
	/**
	 * This method will get the Reservation based on search parameters passed to it
	 * @return Reservation[] 
	 */
	public Reservation[] searchReservationsForAdmin(SearchParametersForReservation searchParameters)
	{
		logger.info("searchReservationsForAdmin with params : "+searchParameters.toString());
		return adminServiceQuery.searchReservationsForAdmin(searchParameters);
	}
	
	/**
	 * This method will get the Reservation based on search parameters passed to it
	 * @return Reservation[] 
	 */
	public Reservation[] getAllReservationsForAdmin()
	{
		logger.info("searchAllReservationsForAdmin");
		return adminServiceQuery.getAllReservationsForAdmin();
	}
	
	/**
	 * Add a new Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int addReservation(Reservation reservation)
	{
		logger.info("Add a Reservation : "+reservation.toString());
		return adminServiceQuery.addReservation(reservation);
	}
	
	/**
	 * Edit Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int editReservation(Reservation reservation)
	{
		logger.info("Edit a Reservation : "+reservation.toString());
		return adminServiceQuery.editReservation(reservation);
	}
	
	/**
	 * Delete Reservation to the system
	 * @param reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 0, failure : -1
	 */
	public int deleteReservation(Reservation reservation)
	{
		logger.info("Delete a Reservation : "+reservation.toString());
		return adminServiceQuery.deleteReservation(reservation);
	}
}
