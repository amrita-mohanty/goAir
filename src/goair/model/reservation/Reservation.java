package goair.model.reservation;

import goair.model.customer.Customer;
import goair.model.flight.Flight;

import java.util.Date;

public class Reservation {
	public long pnr;
	public Customer customerDetails;
	public Flight flightDetails;
	public int numberOfSeatsBooked;
	public long creditCardNumber;
	public Date dateOfBooking;
	public Date dateOfFlying;
	public double totalPrice;
	public String currentStatus;
	
	public long getPnr() {
		return pnr;
	}
	
	public void setPnr(long pnr) {
		this.pnr = pnr;
	}
	
	public int getNumberOfSeatsBooked() {
		return numberOfSeatsBooked;
	}
	
	public void setNumberOfSeatsBooked(int numberOfSeatsBooked) {
		this.numberOfSeatsBooked = numberOfSeatsBooked;
	}
	
	public long getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	
	public Date getDateOfFlying() {
		return dateOfFlying;
	}
	
	public void setDateOfFlying(Date dateOfFlying) {
		this.dateOfFlying = dateOfFlying;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Customer getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}

	public Flight getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(Flight flightDetails) {
		this.flightDetails = flightDetails;
	}

	@Override
	public String toString() {
		return "Reservation [pnr=" + pnr + ", customerDetails="
				+ customerDetails + ", flightDetails=" + flightDetails
				+ ", numberOfSeatsBooked=" + numberOfSeatsBooked
				+ ", creditCardNumber=" + creditCardNumber + ", dateOfBooking="
				+ dateOfBooking + ", dateOfFlying=" + dateOfFlying
				+ ", totalPrice=" + totalPrice + ", currentStatus="
				+ currentStatus + "]";
	}

}

