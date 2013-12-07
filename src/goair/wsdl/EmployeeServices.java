package goair.wsdl;

import org.apache.log4j.Logger;

import goair.model.employee.Employee;
import goair.model.flight.Flight;
import goair.model.query.AdminServiceQueries;
import goair.model.query.EmployeeServiceQueries;
import goair.util.SearchParametersForFlights;

//When you login as employee you see following hyper links
// 1. Search flight - when you get a searched flight
// 2. View all flights -

public class EmployeeServices {
	public EmployeeServices() {}


	public static Logger logger = Logger.getLogger(EmployeeServices.class);
	public EmployeeServiceQueries employeeServiceQueries = new EmployeeServiceQueries();
	public AdminServiceQueries adminServiceQueries = new AdminServiceQueries();
	

	public Employee employeeLogin(String userName,String password) {
		
		Employee employeeBean = employeeServiceQueries.validateEmployeeLogin(userName, password);
		
		return employeeBean;
	}

	public int editEmployeeProfile(Employee employeeBean){
		logger.info("Edit an Employee : "+employeeBean.toString());
		return adminServiceQueries.editEmployee(employeeBean);
	}


	/**
	 * To get the list of all the flights an employee is associated with
	 * @param Employee
	 * @return Array of Flight
	 */
	public Flight[] viewEmployeeFlight(SearchParametersForFlights searchFlightParam) {
		
		return employeeServiceQueries.viewEmployeeFlights(searchFlightParam);
	}

}
