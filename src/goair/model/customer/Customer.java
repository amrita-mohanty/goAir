package goair.model.customer;
import goair.model.general.Person;

public class Customer extends Person{

	public Customer() {}

	String customerId;
	int passportNo;
	String nationality;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(int passportNo) {
		this.passportNo = passportNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", passportNo="
				+ passportNo + ", nationality=" + nationality + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", dob=" + dob + "]";
	}

}

