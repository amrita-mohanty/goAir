package goair.model.customer;
import java.sql.Timestamp;

import goair.model.general.Person;

public class Customer extends Person{

	int customerId;
    String emailIdString;
    String passportNum ;
    String nationality ;
    
	public Customer() {}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmailIdString() {
		return emailIdString;
	}

	public void setEmailIdString(String emailIdString) {
		this.emailIdString = emailIdString;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", emailIdString="
				+ emailIdString + ", passportNum=" + passportNum
				+ ", nationality=" + nationality + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender
				+ ", address=" + address + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode + ", dob=" + dob + "]";
	}

}

