package goair.wsdl;

import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.SearchParametersForFlights;

//When you login as employee you see following hyper links
// 1. Search flight - when you get a searched flight
// 2. View all flights -

public class EmployeeServices {
	public EmployeeServices() {}

	public int employeeLogin(String userName,String Password){
		return 1;
	}

	public int editEmployeeProfile(Employee employeeBean){
		return 1;
	}
	
	public Flight[] searchEmployeeFlights(SearchParametersForFlights searchFlight)
	{
		return null;
	}
	
	/**
	 * To get the list of all the flights an employee is associated with
	 * @param Employee
	 * @return Array of Flight
	 */
	public Flight[] viewEmployeeFlight(Employee crew){
              return null;
	}
	
}
