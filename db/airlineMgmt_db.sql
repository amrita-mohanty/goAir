CREATE DATABASE IF NOT EXISTS `airline`;
USE `airline`;

DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `flightflyinginformation`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `flight`;

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `emailId` varchar(40) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `passportNum` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `airlineName` varchar(50),
  `jobDesc` varchar(20) NOT NULL,
  `position` varchar(20) NOT NULL,
  `hireDate` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This is flight table to hold all the information related to flight
CREATE TABLE  `flight` (
  `flightId` int(5) NOT NULL AUTO_INCREMENT,
  `flightName` varchar(10) NOT NULL,
  `airlineName` varchar(50),
  `source` int(10)NOT NULL,
  `destination` varchar(40) NOT NULL,
  `departureTime` varchar(40) NOT NULL,
  `arrivalTime` varchar(40) NOT NULL,
  `status` varchar(10) NOT NULL,
  `totalSeats` int(20) NOT NULL,
  `seatsReserved` int(10) NOT NULL,
  `daysOfWeek` varchar(50) NOT NULL, -- Comma separated days for this flight
  `flyingStartDate` TIMESTAMP NOT NULL,
  `flyingEndDate` TIMESTAMP NOT NULL,
  PRIMARY KEY  (`flightId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table holds what all date a flight will fly with its crew id
CREATE TABLE  `flightflyinginformation` (
  `flightId` int(5) NOT NULL,
  `dateOfFlying` TIMESTAMP NOT NULL,
  `employeeId` int(11) NULL, 
  FOREIGN KEY (flightId) REFERENCES flight(flightId),
  FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table will hold the reservation information
CREATE TABLE `reservation`(
	`pnr` int(5) NOT NULL AUTO_INCREMENT,
	`customerId` int(11) NOT NULL,
	`flightId` int(5) NOT NULL,
	`numberOfSeatsBooked` int(5) NOT NULL,
	`creditCardNumber` int(16) NOT NULL,
	`dateOfBooking` TIMESTAMP NOT NULL,
	`dateOfFlying` TIMESTAMP NOT NULL,
	FOREIGN KEY (flightId) REFERENCES flight(flightId),
  	FOREIGN KEY (customerId) REFERENCES customer(customerId),
	PRIMARY KEY  (`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
