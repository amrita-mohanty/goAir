package goair.model.payment;

import goair.model.general.Person;

public class PaymentBean extends Person{
	public long  creditcardNumber;
	public int cvvNumber;
	public String expiryDate;
	public long reservationId;
	
	public long getCreditcardNumber() {
		return creditcardNumber;
	}
	public void setCreditcardNumber(long creditcardNumber) {
		this.creditcardNumber = creditcardNumber;
	}
	public int getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	
	@Override
	public String toString() {
		return "PaymentBean [creditcardNumber=" + creditcardNumber
				+ ", cvvNumber=" + cvvNumber + ", expiryDate=" + expiryDate
				+ ", reservationId=" + reservationId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", dob=" + dob + "]";
	}
	
	
}

