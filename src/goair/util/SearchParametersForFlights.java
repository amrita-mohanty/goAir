package goair.util;

public class SearchParametersForFlights {
	
	String flightNumber;
	String airlineName;
	String source;
	String destination;
	
	Long departureTime;
	Long arrivalTime;
	
	int numberOfSeatsAvialable; // This is number of seats - number of seats reserved

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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

	public int getNumberOfSeatsAvialable() {
		return numberOfSeatsAvialable;
	}

	public void setNumberOfSeatsAvialable(int numberOfSeatsAvialable) {
		this.numberOfSeatsAvialable = numberOfSeatsAvialable;
	}

	@Override
	public String toString() {
		return "SearchParametersForFlights [flightId=" + flightNumber
				+ ", airlineName=" + airlineName + ", source=" + source
				+ ", destination=" + destination + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime
				+ ", numberOfSeatsAvialable=" + numberOfSeatsAvialable + "]";
	}
}
