use  airline;


select * from flight;
select * from employee;
select * from customer;
select * from reservation;
select * from flightflyinginformation;

INSERT INTO `flight`(flightName,airlineName,source,destination,departureTime,arrivalTime,
totalSeats,seatsReserved,daysOfWeek,flyingStartDate,flyingEndDate) values 
(
'GA-110','GoAir','San Francisco','Las Vegas',
STR_TO_DATE('2011-12-21 02:20pm', '%Y-%m-%d %h:%i%p'),
STR_TO_DATE('2011-12-21 02:20pm', '%Y-%m-%d %h:%i%p'),
126,56,'M,F',
STR_TO_DATE('2010-12-25', '%Y-%m-%d'),
STR_TO_DATE('2015-12-24', '%Y-%m-%d')
);

insert into employee(employeeId, emailId, `password`, firstName,lastName, gender,jobDesc, position,
hireDate, address, city, state, zipcode, dob) values (
'123-123-1234',
'a@gmail.com', 
'pwd1',
'Peter', 
'Pan', 'F',
'Pilot','Senior Pilot',STR_TO_DATE('2010-12-25', '%Y-%m-%d'),
'3500 Granada Ave', 'Santa Clara', 'CA', '95051',
STR_TO_DATE('2010-12-25', '%Y-%m-%d')
);
  
insert into flightflyinginformation(flightId,dateOfFlying,employeeid,ticketPrice )
values(1,STR_TO_DATE('2013-09-15', '%Y-%m-%d'), 1,450.00);

select flight.flightid, flight.flightName, flight.airlineName, flight.source, 
flight.destination, flight.departureTime, flight.arrivalTime, flight.totalSeats, 
flight.seatsReserved from flight,flightflyinginformation 
where flight.flightId=flightflyinginformation.flightId and 
flight.source='San Francisco' and flight.destination='Las Vegas' and  
flightflyinginformation.dateOfFlying=2013-09-15;

select flightId from flight where flightname='CX987' and source='Los Angeles' 
and destination='San Francisco' and departureTime='2014-01-01 00:00:00' 
and arrivalTime='2014-01-01 00:00:00' and totalSeats=500 and seatsReserved=100 
and daysOfWeek='Monday,Tuesday' and flyingStartDate=2014-01-01 and flyingEndDate=2014-01-01;

select flightid, flightName, airlineName, source, destination, departureTime, 
arrivalTime, totalSeats, seatsReserved, daysOfWeek, flyingStartDate,flyingEndDate 
from flight;

select r.pnr, r.customerId, r.flightId, r.numberOfSeatsBooked, r.creditCardNumber, r.dateOfBooking, r.dateOfFlying,
r.totalPrice,c.firstName,c.lastName,f.airlineName,f.flightname,f.source,f.destination,f.departureTime from reservation r, 
flight f, customer c where r.flightId = f.flightid and r.customerId = c.customerId;

update employee set  hireDate='2013-11-24',  address='6754 Ellie Ave'  where employeeId=10000;
update flight set seatsReserved= (select seatsReserved + 4 from flight where flightid = 1) where flightid = 1;