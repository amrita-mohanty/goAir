package goair.model.customer;

import java.text.SimpleDateFormat;

import goair.model.general.Person;

public class Customer extends Person{

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	int customerId;
    String passportNum ;
    String nationality ;

    public Customer() {}

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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", passportNum=" + passportNum
				+ ", nationality=" + nationality + ", emailId=" + emailId
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender
				+ ", address=" + address + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode + ", dob=" + dateFormat.format(dob.getTime())
				+ ", currentStatus=" + currentStatus + "]";
	}
	
}

