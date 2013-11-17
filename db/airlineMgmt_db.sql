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
  `dob` TIMESTAMP NOT NULL,
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
  `hireDate` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `dob` TIMESTAMP NOT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This is flight table to hold all the information related to flight
CREATE TABLE  `flight` (
  `flightId` INT(5) NOT NULL AUTO_INCREMENT,
  `flightName` VARCHAR(10) NOT NULL,
  `airlineName` VARCHAR(50),
  `source` INT(10)NOT NULL,
  `destination` VARCHAR(40) NOT NULL,
  `departureTime` TIMESTAMP NOT NULL,
  `arrivalTime` TIMESTAMP NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `totalSeats` INT(20) NOT NULL,
  `seatsReserved` INT(10) NOT NULL,
  `daysOfWeek` VARCHAR(50) NOT NULL, -- Comma separated days for this flight
  `flyingStartDate` TIMESTAMP NOT NULL,
  `flyingEndDate` TIMESTAMP NOT NULL,
  PRIMARY KEY  (`flightId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table holds what all date a flight will fly with its crew id
CREATE TABLE  `flightflyinginformation` (
  `flightId` INT(5) NOT NULL,
  `dateOfFlying` TIMESTAMP NOT NULL,
  `employeeId` INT(11) NULL, 
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
	`dateOfBooking` TIMESTAMP NOT NULL,
	`dateOfFlying` TIMESTAMP NOT NULL,
	FOREIGN KEY (flightId) REFERENCES flight(flightId),
  	FOREIGN KEY (customerId) REFERENCES customer(customerId),
	PRIMARY KEY  (`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
