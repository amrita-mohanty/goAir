package goair.model.reservation;

import java.util.Date;

public class Reservation {
	long pnr;
	int customerId;
	int flightId;
	int numberOfSeatsBooked;
	long creditCardNumber;
	Date dateOfBooking;
	Date dateOfFlying;
	double totalPrice;
	String currentStatus;
	
	String customerFirstName;
	String customerLastName;
	
	String airlineName;
	String flightName;
	String source;
	String destination;
	Date departureTime;
	
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

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "Reservation [pnr=" + pnr + ", customerId=" + customerId
				+ ", flightId=" + flightId + ", numberOfSeatsBooked="
				+ numberOfSeatsBooked + ", creditCardNumber="
				+ creditCardNumber + ", dateOfBooking=" + dateOfBooking
				+ ", dateOfFlying=" + dateOfFlying + ", totalPrice="
				+ totalPrice + ", currentStatus=" + currentStatus
				+ ", customerFirstName=" + customerFirstName
				+ ", customerLastName=" + customerLastName + ", airlineName="
				+ airlineName + ", flightName=" + flightName + ", source="
				+ source + ", destination=" + destination + ", departureTime="
				+ departureTime + "]";
	}


}

