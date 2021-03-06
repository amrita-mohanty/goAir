package goair.model.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.jms.JMSException;

import org.apache.log4j.Logger;

import goair.jms.UpdateFlightStatusJms;
import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.query.adminservices.*;
import goair.model.reservation.Reservation;
import goair.util.DbConnection;
import goair.util.SearchParametersForCustomers;
import goair.util.SearchParametersForEmployees;
import goair.util.SearchParametersForFlights;
import goair.util.SearchParametersForReservation;

public class AdminServiceQueries 
{
	public static Logger logger = Logger.getLogger(AdminServiceQueries.class);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	DbConnection dbConnection 	= null;
	Connection connection 		= null;
	
	// Query classes
	SearchFlightsForAdminQuery searchFlightsForAdmin 		= null;
	GetAllFlightsForAdminQuery getAllFlightsForAdmin 		= null;
	AddFlightQuery addFlightQuery 							= null;
	EditFlightQuery editFlightQuery 					    = null;
	DeleteFlightQuery deleteFlightQuery 					= null;
	
	GetAllCustomersForAdminQuery getAllCustomersForAdmin 	= null;
	SearchCustomersForAdminQuery searchCustomersForAdmin 	= null;
	AddCustomerQuery addCustomerQuery 						= null;
	EditCustomerQuery editCustomerQuery 					= null;
	DeleteCustomerQuery deleteCustomerQuery 				= null;
	
	GetAllEmployeesForAdminQuery getAllEmployeesForAdmin 	= null;
	SearchEmployeesForAdminQuery searchEmployeesForAdmin 	= null;
	AddEmployeeQuery addEmployeeQuery 						= null;
	EditEmployeeQuery editEmployeeQuery 					= null;
	DeleteEmployeeQuery deleteEmployeeQuery 				= null;
	
	GetAllReservationsForAdminQuery getAllReservationsForAdminQuery = null;
	SearchReservationsForAdminQuery searchReservationsForAdminQuery = null;
	EditReservationQuery			editReservationQuery			= null;
	AddReservationQuery				addReservationQuery				= null;
	CancelReservationQuery			cancelReservationQuery			= null;
	GetCustomersForFlightQuery      getCustomersForFlightQuery		= null;
	GetEmployeesForFlightQuery      getEmployeesForFlightQuery		= null;
	
	// Jms call
	UpdateFlightStatusJms updateFlightStatusJms = new UpdateFlightStatusJms();
	
	public AdminServiceQueries()
	{
		dbConnection 				= new DbConnection();
		connection 					= dbConnection.createDbConnection();
	}
	
	/**
	 * This method will get the flight based on search parameters passed to it
	 * @return Flight[] 
	 */
	public Flight[] searchFlightsForAdmin(SearchParametersForFlights searchParameters)
	{
		if(searchFlightsForAdmin == null)
		{
			searchFlightsForAdmin 		= new SearchFlightsForAdminQuery();
		}
		logger.info("searchFlightsForAdmin with params : "+searchParameters.toString());
		return searchFlightsForAdmin.searchFlightsForAdmin(searchParameters, connection);
	}

	/**
	 * Get all the flights in the system
	 * @return Flight[] - Return list of all the flights
	 */
	public Flight[] getAllFlightsForAdmin()
	{
		if(getAllFlightsForAdmin == null)
		{
			getAllFlightsForAdmin 		= new GetAllFlightsForAdminQuery();
		}
		logger.info("searchAllFlightsForAdmin");
		return getAllFlightsForAdmin.getAllFlightsForAdmin(connection);
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
		if(addFlightQuery == null)
		{
			addFlightQuery 				= new AddFlightQuery();
		}
		logger.info("Add a flight : "+flight.toString());
		return addFlightQuery.addNewFlight(flight, connection);
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
		if(editFlightQuery == null)
		{
			editFlightQuery				= new EditFlightQuery();
		}
		logger.info("Edit a flight : "+flight.toString());
		return editFlightQuery.editFlight(flight, connection);
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
		if(deleteFlightQuery == null)
		{
			deleteFlightQuery			= new DeleteFlightQuery();
		}
		logger.info("Delete a flight : "+flight.toString());
		return deleteFlightQuery.deleteFlight(flight, connection);
	}
	
	/**
	 * This method will get the Customer based on search parameters passed to it
	 * @return Customer[] 
	 */
	public Customer[] searchCustomersForAdmin(SearchParametersForCustomers searchParameters)
	{
		if(searchCustomersForAdmin == null)
		{
			searchCustomersForAdmin 	= new SearchCustomersForAdminQuery();
		}
		logger.info("searchCustomersForAdmin with params : "+searchParameters.toString());
		return searchCustomersForAdmin.searchCustomersForAdmin(connection, searchParameters);
	}

	/**
	 * Get all the Customers in the system
	 * @return Customer[] - Return list of all the Customers
	 */
	public Customer[] getAllCustomersForAdmin()
	{
		if(getAllCustomersForAdmin == null)
		{
			getAllCustomersForAdmin 	= new GetAllCustomersForAdminQuery();
		}
		logger.info("searchAllCustomersForAdmin");
		return getAllCustomersForAdmin.getAllCustomersForAdmin(connection);
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
		if(addCustomerQuery == null)
		{
			addCustomerQuery = new AddCustomerQuery();
		}
		logger.info("Add a Customer : "+customer.toString());
		return addCustomerQuery.addNewCustomer(customer, connection);
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
		if(editCustomerQuery == null)
		{
			editCustomerQuery = new EditCustomerQuery();
		}
		logger.info("Edit a Customer : "+customer.toString());
		return editCustomerQuery.editCustomer(customer, connection);
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
		if(deleteCustomerQuery == null)
		{
			deleteCustomerQuery = new DeleteCustomerQuery();
		}
		logger.info("Delete a Customer : "+customer.toString());
		return deleteCustomerQuery.deleteCustomer(customer, connection);
	}
	
	/**
	 * This method will get the Employee based on search parameters passed to it
	 * @return Employee[] 
	 */
	public Employee[] searchEmployeesForAdmin(SearchParametersForEmployees searchParameters)
	{
		if(searchEmployeesForAdmin == null)
		{
			searchEmployeesForAdmin = new SearchEmployeesForAdminQuery();
		}
		logger.info("searchEmployeesForAdmin with params : "+searchParameters.toString());
		return searchEmployeesForAdmin.searchEmployeesForAdmin(connection, searchParameters);
	}

	/**
	 * Get all the Employees in the system
	 * @return Employee[] - Return list of all the Employees
	 */
	public Employee[] getAllEmployeesForAdmin()
	{
		if(getAllEmployeesForAdmin == null)
		{
			getAllEmployeesForAdmin = new GetAllEmployeesForAdminQuery();
		}
		logger.info("searchAllEmployeesForAdmin");
		return getAllEmployeesForAdmin.getAllEmployeesForAdmin(connection);
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
		if(addEmployeeQuery == null)
		{
			addEmployeeQuery = new AddEmployeeQuery();
		}
		logger.info("Add a Employee : "+employee.toString());
		return addEmployeeQuery.addNewEmployee(employee, connection);
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
		if(editEmployeeQuery == null)
		{
			editEmployeeQuery = new EditEmployeeQuery();
		}
		logger.info("Edit a Employee : "+employee.toString());
		return editEmployeeQuery.editEmployee(employee, connection);
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
		if(deleteEmployeeQuery == null)
		{
			deleteEmployeeQuery = new DeleteEmployeeQuery();
		}
		logger.info("Delete a Employee : "+employee.toString());
		return deleteEmployeeQuery.deleteEmployee(employee, connection);
	}
	
	/**
	 * This method will get the Reservation based on search parameters passed to it
	 * @return Reservation[] 
	 */
	public Reservation[] searchReservationsForAdmin(SearchParametersForReservation searchParameters)
	{
		if(searchReservationsForAdminQuery == null)
		{
			searchReservationsForAdminQuery = new SearchReservationsForAdminQuery();
		}
		logger.info("searchReservationsForAdmin with params : "+searchParameters.toString());
		return searchReservationsForAdminQuery.searchReservationForAdmin(connection, searchParameters);
	}

	/**
	 * Get all the Reservations in the system
	 * @return Reservation[] - Return list of all the Reservations
	 */
	public Reservation[] getAllReservationsForAdmin()
	{
		if(getAllReservationsForAdminQuery == null)
		{
			getAllReservationsForAdminQuery = new GetAllReservationsForAdminQuery();
		}
		logger.info("searchAllReservationsForAdmin");
		return getAllReservationsForAdminQuery.getAllReservationsForAdmin(connection);
	}
	
	/**
	 * Add a new Reservation to the system
	 * @param Reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int addReservation(Reservation Reservation)
	{
		if(addReservationQuery == null)
		{
			addReservationQuery = new AddReservationQuery();
		}
		logger.info("Add a Reservation : "+Reservation.toString());
		return addReservationQuery.addNewReservation(Reservation, connection);
	}
	
	/**
	 * Edit Reservation to the system
	 * @param Reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int editReservation(Reservation Reservation)
	{
		if(editReservationQuery == null)
		{
			editReservationQuery = new EditReservationQuery();
		}
		logger.info("Edit a Reservation : "+Reservation.toString());
		return editReservationQuery.editReservation(Reservation, connection);
	}
	
	/**
	 * Cancel Reservation in the system
	 * @param Reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 * success : 1, failure : -1
	 */
	public int cancelReservation(Reservation Reservation)
	{
		if(cancelReservationQuery == null)
		{
			cancelReservationQuery = new CancelReservationQuery();
		}
		logger.info("Delete a Reservation : "+Reservation.toString());
		return cancelReservationQuery.cancelReservation(Reservation, connection);
	}

	/*
	 * Get all the customers in a flight
	 * Input : dateOfFlying , flightId
	 */
	public Customer[] getCustomersForFlight(int flightId) {
		logger.info("Get all customers in a Flight: "+ flightId);
		if(getCustomersForFlightQuery == null)
		{
			getCustomersForFlightQuery = new GetCustomersForFlightQuery();
		}
		return getCustomersForFlightQuery.searchCustomersForFlight(flightId,connection);
	}

	/*
	 * Get all the employee in a flight
	 * Input : dateOfFlying , flightId
	 */
	public Employee[] getEmployeesForFlight(int flightId) {
		logger.info("Get all employees in a Flight: "+ flightId);
		if(getEmployeesForFlightQuery == null)
		{
			getEmployeesForFlightQuery = new GetEmployeesForFlightQuery();
		}
		return getEmployeesForFlightQuery.searchEmployeesForFlight(flightId,connection);
	}
	
	public void updateFlightStatusQuery(int flightId, String status) {
		logger.info("Updating flightstatus for Flight: " + 
				flightId + ", status : "+status);
		String flightFlyingInfoTableQuery = "update  flightflyinginformation set flightStatus='"
				+ status +"' where"
				+ "flightId=" + flightId;
		try {
			Statement statement = connection.createStatement();
			statement.execute(flightFlyingInfoTableQuery);
			updateFlightStatusJms.updateFlightStatus();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
