package goair.util;

import java.util.Date;

public class SearchParametersForFlights {
	String flightName;
	String airlineName;
	String source;
	String destination;
	Date dateOfFlying;
	Date departureTime;
	Date arrivalTime;
	Integer numberOfSeatsAvialable; // This is number of seats - number of seats reserved
	Integer flightId;
	String employeeId;
	Integer customerId;
	
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
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

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getNumberOfSeatsAvialable() {
		return numberOfSeatsAvialable;
	}

	public void setNumberOfSeatsAvialable(Integer numberOfSeatsAvialable) {
		this.numberOfSeatsAvialable = numberOfSeatsAvialable;
	}
	
	public Date getDateOfFlying() {
		return dateOfFlying;
	}

	public void setDateOfFlying(Date dateOfFlying) {
		this.dateOfFlying = dateOfFlying;
	}
	@Override
	public String toString() {
		return "SearchParametersForFlights [flightName=" + flightName
				+ ", airlineName=" + airlineName + ", source=" + source
				+ ", destination=" + destination + ", dateOfFlying="
				+ dateOfFlying + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", numberOfSeatsAvialable="
				+ numberOfSeatsAvialable + ", flightId=" + flightId
				+ ", employeeId=" + employeeId + ", customerId=" + customerId
				+ "]";
	}
}
