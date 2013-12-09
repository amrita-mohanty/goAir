CREATE DATABASE IF NOT EXISTS `airline`;
USE `airline`;

DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `flightflyinginformation`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `flight`;

CREATE TABLE `customer` (
  `customerId` VARCHAR(20) NOT NULL,
  `emailId` VARCHAR(40) NOT NULL UNIQUE, -- This is the username when user logs in
  `password` VARCHAR(100) NOT NULL, -- This is the password to check when user logs in
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(10),
  `passportNum` VARCHAR(20),
  `nationality` VARCHAR(20),
  `address` VARCHAR(50),
  `city` VARCHAR(20),
  `state` VARCHAR(20),
  `zipcode` VARCHAR(20),
  `dob` DATE,
  `currentStatus` VARCHAR(10) default 'Active', -- Active or Deleted
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `customer` AUTO_INCREMENT = 10000;

CREATE TABLE `employee` (
  `employeeId` VARCHAR(40) NOT NULL,  -- This is in ssn format.
  `emailId` VARCHAR(40) NOT NULL UNIQUE, -- This is the username when user logs in
  `password` VARCHAR(100) NOT NULL, -- This is the password to check when user logs in
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `airlineName` VARCHAR(50),
  `jobDesc` VARCHAR(20) NOT NULL,
  `position` VARCHAR(20) NOT NULL,
  `hireDate` DATE NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `dob` DATE NOT NULL,
  `currentStatus` VARCHAR(10) default 'Active', -- Active or Deleted
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `employee` AUTO_INCREMENT = 10000;

-- This is flight table to hold all the information related to flight
CREATE TABLE  `flight` (
  `flightId` INT(5) NOT NULL AUTO_INCREMENT,
  `flightName` VARCHAR(10) NOT NULL UNIQUE,
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
  `currentStatus` VARCHAR(10) default 'Active', -- Active, Inactive or Deleted
  PRIMARY KEY  (`flightId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table holds what all date a flight will fly with its crew id
CREATE TABLE  `flightflyinginformation` (
  `flightId` INT(5) NOT NULL,
  `dateOfFlying` DATE NOT NULL,
  `flightStatus` VARCHAR(20),
  `employeeId` VARCHAR(40), -- This can be null as we can add employees as we go.
  `ticketPrice` DECIMAL(10,2) NOT NULL, 
  FOREIGN KEY (flightId) REFERENCES flight(flightId),
  FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table will hold the reservation information
CREATE TABLE `reservation`(
	`pnr` INT(5) NOT NULL AUTO_INCREMENT,
	`customerId` VARCHAR(20) NOT NULL,
	`flightId` INT(5) NOT NULL,
	`numberOfSeatsBooked` INT(5) NOT NULL,
	`creditCardNumber` VARCHAR(20) NOT NULL,
	`dateOfBooking` DATE NOT NULL,
	`dateOfFlying` DATE NOT NULL,
	`totalPrice` DECIMAL(10,2) NOT NULL, 
	`currentStatus` VARCHAR(10) default 'Active', -- Active, Inactive or Deleted
	FOREIGN KEY (flightId) REFERENCES flight(flightId),
  	FOREIGN KEY (customerId) REFERENCES customer(customerId),
	PRIMARY KEY  (`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;