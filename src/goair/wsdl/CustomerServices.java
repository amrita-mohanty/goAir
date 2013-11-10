package goair.wsdl;

import java.awt.print.Book;
import java.util.List;

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
         * @return String[] - Return array of strings. Each string contains pipe separated flight details.
         *  
         */
        public List<Flight> searchFlights(SearchParametersForFlights searchFlight) throws AirlineException
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
        
        public List<Booking> viewBookingHistory(long customerId) throws AirlineException{
                return null;
        }
        
        public Book viewBooking(long reservationid) throws AirlineException{
        	return null;
        }
        
        
        
}