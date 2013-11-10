package goair.model.booking;

import goair.model.customer.Customer;
import goair.model.flight.Flight;

public class Booking {
	public Customer customerBean;
	public Flight flightBean;
	public int reservationId;
	
	public Customer getCustomerBean() {
		return customerBean;
	}
	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}
	public Flight getFlightBean() {
		return flightBean;
	}
	public void setFlightBean(Flight flightBean) {
		this.flightBean = flightBean;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	@Override
	public String toString() {
		return "BookingBean [customerBean=" + customerBean + ", flightBean="
				+ flightBean + ", reservationId=" + reservationId + "]";
	}
	
	
	
}

