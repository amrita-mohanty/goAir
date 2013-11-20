package goair.wsdl;

import goair.model.customer.Customer;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.reservation.Reservation;
import goair.util.SearchParametersForCustomers;
import goair.util.SearchParametersForEmployees;
import goair.util.SearchParametersForFlights;
import goair.util.AdminUtil;
import goair.util.SearchParametersForReservation;

public class AdminServices {
	
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
	

	public AdminUtil adminUtil = new AdminUtil();
	
	/**
	 * Search flights - This method searches all the flights with a given criteria
	 * @return Flight[] -  Return list of flights
	 *  
	 */
	public Flight[] searchFlights(SearchParametersForFlights searchParameters)
	{
		return adminUtil.searchFlightsForAdmin(searchParameters);
	}
	
	/**
	 * Get all the flights in the system
	 * @return Flight[] - Return list of all the flights
	 */
	public Flight[] getFlights()
	{
		return adminUtil.getAllFlightsForAdmin();
	}
	
	/**
	 * Add a new flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int addFlight(Flight flight)
	{
		return adminUtil.addFlight(flight);
	}
	
	/**
	 * Edit flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int editFlight(Flight flight)
	{
		return adminUtil.editFlight(flight);
	}
	

	/**
	 * Delete flight to the system
	 * @param flight
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int deleteFlight(Flight flight)
	{
		return adminUtil.deleteFlight(flight);
	}
	
	/**
	 * Search Customers - This method searches all the Customer with a given criteria
	 * @return Customer[] - Return list of Customers.
	 *  
	 */
	public Customer[] searchCustomers(SearchParametersForCustomers searchParameters)
	{
		return adminUtil.searchCustomersForAdmin(searchParameters);
	}
	
	/**
	 * Get all the Customers in the system
	 * @return Customer[] - Return list of all the Customers
	 */
	public Customer[] getCustomers()
	{
		return adminUtil.getAllCustomersForAdmin();
	}
	
	/**
	 * Add a new Customer to the system
	 * @param Customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int addCustomer(Customer customer)
	{
		return adminUtil.addCustomer(customer);
	}
	
	/**
	 * Edit Customer to the system
	 * @param Customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int editCustomer(Customer customer)
	{
		return adminUtil.editCustomer(customer);
	}
	
	/**
	 * Delete Customer to the system
	 * @param Customer
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int deleteCustomer(Customer customer)
	{
		return adminUtil.deleteCustomer(customer);
	}
	
	/**
	 * Search Employee - This method searches all the Employee with a given criteria
	 * @return Employee[] - Return list of Employee.
	 *  
	 */
	public Employee[] searchEmployees(SearchParametersForEmployees searchParameters)
	{
		return adminUtil.searchEmployeesForAdmin(searchParameters);
	}
	
	/**
	 * Get all the Employees in the system
	 * @return Customer[] - Return list of all the Employees
	 */
	public Employee[] getEmployees()
	{
		return adminUtil.getAllEmployeesForAdmin();
	}
	
	/**
	 * Add a new Employee to the system
	 * @param Employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int addEmployee(Employee employee)
	{
		return adminUtil.addEmployee(employee);
	}
	
	/**
	 * Edit Employee to the system
	 * @param Employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int editEmployee(Employee employee)
	{
		return adminUtil.editEmployee(employee);
	}
	
	/**
	 * Delete Employee to the system
	 * @param Employee
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int deleteEmployee(Employee employee)
	{
		return adminUtil.deleteEmployee(employee);
	}
	
	/**
	 * Search Reservation - This method searches all the Reservation with a given criteria
	 * @return Reservation[] - Return list of Reservation.
	 *  
	 */
	public Reservation[] searchReservations(SearchParametersForReservation searchParameters)
	{
		return adminUtil.searchReservationsForAdmin(searchParameters);
	}
	
	/**
	 * Get all the Reservation in the system
	 * @return Reservation[] - Return list of all the Reservations
	 */
	public Reservation[] getAllReservations()
	{
		return adminUtil.getAllReservationsForAdmin();
	}
	
	/**
	 * Get all the Reservation in the system
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int addReservation(Reservation reservation)
	{
		return adminUtil.addReservation(reservation);
	}
	
	/**
	 * Edit Reservation to the system
	 * @param Reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int editReservation(Reservation reservation)
	{
		return adminUtil.editReservation(reservation);
	}
	
	/**
	 * Delete / Cancel Reservation to the system
	 * @param Reservation
	 * @return int status of the operation this maps to the list of error codes defined 
	 * for the system.
	 */
	public int deleteReservation(Reservation reservation)
	{
		return adminUtil.deleteReservation(reservation);
	}
	
}
