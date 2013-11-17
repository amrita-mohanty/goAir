package goair.model.flight;

import java.util.Arrays;

import goair.model.customer.Customer;
import goair.model.employee.Employee;

public class Flight {
	
	String flightId;
    String flightName;
    String airlineName;
    String source;
    String destination;
    Long departureTime;
    Long arrivalTime;
    String status;
    int totalSeats;
    int seatsReserved;
    String daysOfWeek;
    Long flyingStartDate;
    Long flyingEndDate;
    
	Employee[] crewDetails;
	Customer[] passengers;
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public String getFlightName() {
		return flightName;
	}
	
	public void setFlightName(String flightName) {
		this.flightName = flightName;
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
	
	public Long getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}
	
	public Long getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public int getSeatsReserved() {
		return seatsReserved;
	}
	
	public void setSeatsReserved(int seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
	
	public String getDaysOfWeek() {
		return daysOfWeek;
	}
	
	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	
	public Long getFlyingStartDate() {
		return flyingStartDate;
	}
	
	public void setFlyingStartDate(Long flyingStartDate) {
		this.flyingStartDate = flyingStartDate;
	}
	
	public Long getFlyingEndDate() {
		return flyingEndDate;
	}
	
	public void setFlyingEndDate(Long flyingEndDate) {
		this.flyingEndDate = flyingEndDate;
	}
	
	public Employee[] getCrewDetails() {
		return crewDetails;
	}
	
	public void setCrewDetails(Employee[] crewDetails) {
		this.crewDetails = crewDetails;
	}
	
	public Customer[] getPassengers() {
		return passengers;
	}
	
	public void setPassengers(Customer[] passengers) {
		this.passengers = passengers;
	}
	
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName
				+ ", airlineName=" + airlineName + ", source=" + source
				+ ", destination=" + destination + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", status="
				+ status + ", totalSeats=" + totalSeats + ", seatsReserved="
				+ seatsReserved + ", daysOfWeek=" + daysOfWeek
				+ ", flyingStartDate=" + flyingStartDate + ", flyingEndDate="
				+ flyingEndDate + ", crewDetails="
				+ Arrays.toString(crewDetails) + ", passengers="
				+ Arrays.toString(passengers) + "]";
	}
	
	
}
