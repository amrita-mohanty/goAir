package goair.wsdl;

import goair.Exception.AirlineException;
import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.reservation.Reservation;

public class AirlineServices {
	
	
	
	/**
	 * Create Reservation - This method creates a new airline reservation
	 * @return int - 1 for success, 0 for failure
	 *  
	 */
	public int createReservation(Customer customer, Flight flight) throws AirlineException
	{
		return 1;
	}

	
	/**
	 * Delete Reservation - This method deletes an existing reservation
	 * @return int - Returns 1 for success, 0 for failure
	 *  
	 */
	public int cancelReservation(Reservation booking) throws AirlineException
	{
		return 1;
	}
	

}
