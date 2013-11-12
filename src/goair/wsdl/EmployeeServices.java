package goair.wsdl;

import goair.Exception.AirlineException;
import goair.model.employee.Crew;
import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.util.SearchParametersForFlights;

//When you login as employee you see following hyper links
// 1. Search flight - when you get a searched flight
// 2. View all flights -

public class EmployeeServices {
	public EmployeeServices() {}

	public int employeeLogin(String userName,String Password) throws AirlineException{
		return 1;
	}

	public int editEmployeeProfile(Employee employeeBean) throws AirlineException{
		return 1;
	}
	
	public Flight[] searchEmployeeFlights(SearchParametersForFlights searchFlight) throws AirlineException
	{
		return null;
	}
	
	/**
	 * To get the list of all the flights an employee is associated with
	 * @param Employee
	 * @return Array of Flight
	 */
	public Flight[] viewEmployeeFlight(Crew crew) throws AirlineException{
              return null;
	}
	
}
