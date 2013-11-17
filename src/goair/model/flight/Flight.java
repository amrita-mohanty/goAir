package goair.model.flight;

import java.util.Arrays;
import java.util.Date;

import goair.model.customer.Customer;
import goair.model.employee.Employee;

public class Flight {
	
	Integer flightId;
    String flightName;
    String airlineName;
    String source;
    String destination;
    Date departureTime;
    Date arrivalTime;
    String status;
    int totalSeats;
    int seatsReserved;
    int seatsAvailable;
	String daysOfWeek;
    Date flyingStartDate;
    Date flyingEndDate;
    
	Employee[] crewDetails;
	Customer[] passengers;
	
	public Integer getFlightId() {
		return flightId;
	}
	
	public void setFlightId(Integer flightId) {
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
	
	public Date getFlyingStartDate() {
		return flyingStartDate;
	}
	
	public void setFlyingStartDate(Date flyingStartDate) {
		this.flyingStartDate = flyingStartDate;
	}
	
	public Date getFlyingEndDate() {
		return flyingEndDate;
	}
	
	public void setFlyingEndDate(Date flyingEndDate) {
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
	
	 public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName
				+ ", airlineName=" + airlineName + ", source=" + source
				+ ", destination=" + destination + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", status="
				+ status + ", totalSeats=" + totalSeats + ", seatsReserved="
				+ seatsReserved + ", seatsAvailable=" + seatsAvailable
				+ ", daysOfWeek=" + daysOfWeek + ", flyingStartDate="
				+ flyingStartDate + ", flyingEndDate=" + flyingEndDate
				+ ", crewDetails=" + Arrays.toString(crewDetails)
				+ ", passengers=" + Arrays.toString(passengers) + "]";
	}
}
