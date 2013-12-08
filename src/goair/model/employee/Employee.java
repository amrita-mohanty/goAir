package goair.model.employee;

import java.util.Date;

import goair.model.general.Person;

public class Employee extends Person {
	public Employee() {}

	String employeeId; // This should be in format of SSN (123-123-1234)
	String airlineName;
	String jobDesc;
	String position;
	Date hireDate;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", airlineName="
				+ airlineName + ", jobDesc=" + jobDesc + ", position="
				+ position + ", hireDate=" + hireDate 
				+ ", emailId=" + emailId
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender
				+ ", address=" + address + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode 
				+ ", dob=" + dob
				+ ", currentStatus=" + currentStatus + "]";
	}
	
}

