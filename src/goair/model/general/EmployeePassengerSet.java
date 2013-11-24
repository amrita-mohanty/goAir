package goair.model.general;

import goair.model.employee.Employee;
import goair.model.customer.Customer;

import java.util.HashSet;
import java.util.Set;

public class EmployeePassengerSet {
	
	double ticketPrice = 0d;
	
	Set<Employee> employeeSet = new HashSet<Employee>();
	Set<Customer> passengerSet = new HashSet<Customer>();

	public void addEmployee(Employee employee) {
		employeeSet.add(employee);
	}
	
	public Employee[] getEmployees() {
		return employeeSet.toArray(new Employee[employeeSet.size()]);
	}
	
	public void addPassenger(Customer passenger) {
		passengerSet.add(passenger);
	}
	
	public Customer[] getPassenger() {
		return passengerSet.toArray(new Customer[passengerSet.size()]);
	}
	
	public double getTicketPrice()
	{
		return ticketPrice;
	}
	
	public void setTicketPrice(double ticketPrice)
	{
		this.ticketPrice = ticketPrice;
	}
}
