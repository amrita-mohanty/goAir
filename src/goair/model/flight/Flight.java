package goair.model.flight;

import java.util.Arrays;

import goair.model.customer.Customer;
import goair.model.employee.Employee;

public class Flight {
	
	String flightName;
	String airlineName;
	String source;
	String destination;
	int numberOfSeats;
	Employee[] crewDetails;

	// Extra attributes that will be used
	String flightId; // This is to uniquely identify the a flight across the table. 
							//(i.e same flight but different dates) 
	
	Long arrivalTime;
	Long departureTime;
	String status;
	int numberOfSeatsReserved;
	Customer[] passengers;
	
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

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Employee[] getCrewDetails() {
		return crewDetails;
	}

	public void setCrewDetails(Employee[] crewDetails) {
		this.crewDetails = crewDetails;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumberOfSeatsReserved() {
		return numberOfSeatsReserved;
	}

	public void setNumberOfSeatsReserved(int numberOfSeatsReserved) {
		this.numberOfSeatsReserved = numberOfSeatsReserved;
	}

	public Customer[] getPassengers() {
		return passengers;
	}

	public void setPassengers(Customer[] passengers) {
		this.passengers = passengers;
	}

	
	@Override
	public String toString() {
		return "Flight [flightName=" + flightName + ", airlineName="
				+ airlineName + ", source=" + source + ", destination="
				+ destination + ", numberOfSeats=" + numberOfSeats
				+ ", crewDetails=" + Arrays.toString(crewDetails)
				+ ", flightId=" + flightId + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", status=" + status
				+ ", numberOfSeatsReserved=" + numberOfSeatsReserved
				+ ", passengers=" + Arrays.toString(passengers) + "]";
	}

	/*
	 * Create a string will all the attributes of this class with pipe separator
	 */
	public String getWsdlString()
	{
		return null;
	}

}
