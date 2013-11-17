package goair.wsdl;

import goair.Exception.AirlineException;
import goair.model.booking.Booking;
import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.payment.PaymentBean;
import goair.util.SearchParametersForFlights;

public class CustomerServices extends AirlineServices{
        
        public CustomerServices() {}
        
        /**
         * Search flights - This method searches all the flights with a given criteria
         * and returns only those columns which a customer can view
         * @return Flight[] - Return array of flights.
         *  
         */
        public Flight[] searchFlights(SearchParametersForFlights searchFlight) throws AirlineException
        {
                return null;
        }
        
        public Flight[] viewAllFlights(SearchParametersForFlights searchFlight) throws AirlineException
        {
                return null;
        }
        
        public int customerLogin(String userName,String Password) throws AirlineException{
                return 1;
        }
        
        public int customerRegistration(Customer customerBean) throws AirlineException{
                return 1;
        }
        
        public int editCustomerProfile(Customer customerBean) throws AirlineException{
                return 1;
        }
        
        public int makePayment(PaymentBean paymentBean) throws AirlineException{
                return 1;
        }
        
        public Booking[] viewBookingHistory(long customerId) throws AirlineException{
                return null;
        }
        
        public Booking viewBooking(long reservationid) throws AirlineException{
        	return null;
        }
        
        
        
}