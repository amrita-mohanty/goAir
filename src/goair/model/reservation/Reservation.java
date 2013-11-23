package goair.model.reservation;

import java.util.Date;

public class Reservation {
	public String pnr;
	public int customerId;
	public int flightId;
	public int numberOfSeatsBooked;
	public long creditCardNumber;
	public Date dateOfBooking;
	public Date dateOfFlying;
	public double totalPrice;
	public String currentStatus;
	
	public String getPnr() {
		return pnr;
	}
	
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getFlightId() {
		return flightId;
	}
	
	public void setFlightId(int flightId) {
		this.flightId = flightId;
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

	@Override
	public String toString() {
		return "Reservation [pnr=" + pnr + ", customerId=" + customerId
				+ ", flightId=" + flightId + ", numberOfSeatsBooked="
				+ numberOfSeatsBooked + ", creditCardNumber="
				+ creditCardNumber + ", dateOfBooking=" + dateOfBooking
				+ ", dateOfFlying=" + dateOfFlying + ", totalPrice="
				+ totalPrice + ", currentStatus=" + currentStatus+ "]";
	}
}

