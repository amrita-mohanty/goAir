package goair.model.flight;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import goair.model.customer.Customer;
import goair.model.employee.Employee;

public class Flight {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	Integer flightId;
	String flightName;
	String airlineName;
	String source;
	String destination;
	Date departureTime;
	Date arrivalTime;
	String flightStatus;
	int totalSeats;
	int seatsReserved;
	int seatsAvailable;
	String daysOfWeek;
	Date flyingStartDate;
	Date flyingEndDate;
	Date flyingDate;
	Double ticketPrice;
	String currentStatus;

	Employee[] crewDetails;
	Customer[] passengers;

	// Default Constructor
	public Flight()
	{
		
	}
	
	// Create a shallow copy of the flight object
	public Flight(Flight flight) {
		super();
		this.flightId = flight.getFlightId();
		this.flightName = flight.getFlightName();
		this.airlineName = flight.getAirlineName();
		this.source = flight.getSource();
		this.destination = flight.getDestination();
		this.departureTime = flight.getDepartureTime();
		this.arrivalTime = flight.getArrivalTime();
		this.flightStatus = flight.getFlightStatus();
		this.totalSeats = flight.getTotalSeats();
		this.seatsReserved = flight.getSeatsReserved();
		this.seatsAvailable = flight.getSeatsAvailable();
		this.daysOfWeek = flight.getDaysOfWeek();
		this.flyingStartDate = flight.getFlyingStartDate();
		this.flyingEndDate = flight.getFlyingEndDate();
		this.flyingDate = flight.getFlyingDate();
		this.ticketPrice = flight.getTicketPrice();
		this.currentStatus = flight.getCurrentStatus();
		this.crewDetails = flight.getCrewDetails();
		this.passengers = flight.getPassengers();
	}

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

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
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



	public Date getFlyingDate() {
		return flyingDate;
	}

	public void setFlyingDate(Date flyingDate) {
		this.flyingDate = flyingDate;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	 @Override
		public String toString() {
			return "Flight [airlineName="
					+ airlineName 
					+ ", arrivalTime=" + arrivalTime != null ? dateFormatTime.format(arrivalTime.getTime()) : null
					+ ", crewDetails=" + Arrays.toString(crewDetails)
					+ ", currentStatus=" + currentStatus + ", daysOfWeek="
					+ daysOfWeek 
					+ ", departureTime=" + departureTime != null ? dateFormatTime.format(departureTime.getTime()) : null
					+ ", destination=" + destination + ", flightId=" + flightId
					+ ", flightName=" + flightName + ", flightStatus="
					+ flightStatus 
					+ ", flyingEndDate=" + flyingEndDate != null ? dateFormat.format(flyingEndDate.getTime()) : null
					+ ", flyingStartDate=" + flyingStartDate != null ? dateFormat.format(flyingStartDate.getTime()) : null 
					+ ", flyingDate=" + flyingDate != null ? dateFormat.format(flyingDate.getTime()) : null
					+ ", passengers="
					+ Arrays.toString(passengers) + ", seatsAvailable="
					+ seatsAvailable + ", seatsReserved=" + seatsReserved
					+ ", source=" + source + ", ticketPrice=" + ticketPrice
					+ ", totalSeats=" + totalSeats + "]";
		}

}
