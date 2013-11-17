CREATE DATABASE IF NOT EXISTS `airline`;
USE `airline`;

DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `flightflyinginformation`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `flight`;

CREATE TABLE `customer` (
  `customerId` INT(11) NOT NULL AUTO_INCREMENT,
  `emailId` VARCHAR(40) NOT NULL,
  `firstname` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `passportNum` VARCHAR(20) NOT NULL,
  `nationality` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `dob` DATE NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `employeeId` INT(11) NOT NULL AUTO_INCREMENT,
  `emailId` VARCHAR(40) NOT NULL,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `airlineName` VARCHAR(50),
  `jobDesc` VARCHAR(20) NOT NULL,
  `position` VARCHAR(20) NOT NULL,
  `hireDate` DATE NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `dob` DATE NOT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This is flight table to hold all the information related to flight
CREATE TABLE  `flight` (
  `flightId` INT(5) NOT NULL AUTO_INCREMENT,
  `flightName` VARCHAR(10) NOT NULL,
  `airlineName` VARCHAR(50),
  `source` VARCHAR(40) NOT NULL,
  `destination` VARCHAR(40) NOT NULL,
  `departureTime` DATETIME NOT NULL,
  `arrivalTime` DATETIME NOT NULL,
  `totalSeats` INT(20) NOT NULL,
  `seatsReserved` INT(10) NOT NULL,
  `daysOfWeek` VARCHAR(50) NOT NULL, -- Comma separated days for this flight eg. for Monday and Thursday it will be M,Th
  `flyingStartDate` DATE NOT NULL,
  `flyingEndDate` DATE NOT NULL,
  PRIMARY KEY  (`flightId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table holds what all date a flight will fly with its crew id
CREATE TABLE  `flightflyinginformation` (
  `flightId` INT(5) NOT NULL,
  `dateOfFlying` DATE NOT NULL,
  `status` VARCHAR(20),
  `employeeId` INT(11) NOT NULL,
  `ticketPrice` DECIMAL(10,2) NOT NULL, 
  FOREIGN KEY (flightId) REFERENCES flight(flightId),
  FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table will hold the reservation information
CREATE TABLE `reservation`(
	`pnr` INT(5) NOT NULL AUTO_INCREMENT,
	`customerId` INT(11) NOT NULL,
	`flightId` INT(5) NOT NULL,
	`numberOfSeatsBooked` INT(5) NOT NULL,
	`creditCardNumber` INT(16) NOT NULL,
	`dateOfBooking` DATE NOT NULL,
	`dateOfFlying` DATE NOT NULL,
	FOREIGN KEY (flightId) REFERENCES flight(flightId),
  	FOREIGN KEY (customerId) REFERENCES customer(customerId),
	PRIMARY KEY  (`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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

insert into employee(emailId, firstName,lastName, gender,jobDesc, position,
hireDate, address, city, state, zipcode, dob) values (
'a@gmail.com', 
'Peter', 
'Pan', 'F',
'Pilot','Senior Pilot',STR_TO_DATE('2010-12-25', '%Y-%m-%d'),
'3500 Granada Ave', 'Santa Clara', 'CA', '95051',
STR_TO_DATE('2010-12-25', '%Y-%m-%d')
);

  
insert into flightflyinginformation(flightId,dateOfFlying,employeeid,ticketPrice )
values(1,STR_TO_DATE('2013-09-15', '%Y-%m-%d'), 1,450.00);

select * from flight;
select * from flight;
select flight.flightid, flight.flightName, flight.airlineName, flight.source, flight.destination, flight.departureTime, flight.arrivalTime, flight.totalSeats, flight.seatsReserved from flight,flightflyinginformation where flight.flightId=flightflyinginformation.flightId and flight.source='San Francisco' and flight.destination='Las Vegas' and  flightflyinginformation.dateOfFlying=2013-09-15;
