package goair.util;

import java.util.Date;

public class SearchParametersForCustomers {
	
	int customerId;
    String passportNum;
    String nationality;
    
    String emailId;
	String password;
	String firstName;
	String lastName;
	String gender;
	String address;
	String city;
	String state;
	String zipcode;
	Date dob;
	String currentStatus;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPassportNum() {
		return passportNum;
	}
	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Override
	public String toString() {
		return "SearchParametersForCustomers [customerId=" + customerId
				+ ", passportNum=" + passportNum + ", nationality="
				+ nationality + ", emailId=" + emailId + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", dob=" + dob + ", currentStatus=" + currentStatus
				+ "]";
	}
}
