package goair.wsdl;

import goair.Exception.AirlineException;
import goair.model.booking.Reservation;
import goair.model.customer.Customer;
import goair.model.flight.Flight;
import goair.model.payment.PaymentBean;
import goair.model.query.CustomerServiceQuery;
import goair.util.SearchParametersForFlights;

public class CustomerServices extends AirlineServices{
        
        public CustomerServices() {}
        
        /**
         * Search flights - This method searches all the flights with a given criteria
         * and returns only those columns which a customer can view
         * @return Flight[] - Return array of flights.
         *  
         */
        public Flight[] searchFlights(SearchParametersForFlights searchFlightParam) throws AirlineException
        {
        	Flight[] searchResult = null;
        	CustomerServiceQuery customerServiceQuery = new CustomerServiceQuery();
        	searchResult = customerServiceQuery.searchFlightsForCustomer(searchFlightParam);
            
        	return searchResult;
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
        
        public Reservation[] viewBookingHistory(long customerId) throws AirlineException{
                return null;
        }
        
        public Reservation viewBooking(long reservationid) throws AirlineException{
        	return null;
        }
        
        
        public static void main(String args[]){
        }
        
}