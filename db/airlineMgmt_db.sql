CREATE DATABASE IF NOT EXISTS `airline`;
USE `airline`;

DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `flightflyinginformation`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `flight`;

CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `passportnum` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `airlinename` varchar(50),
  `jobdesc` varchar(20) NOT NULL,
  `position` varchar(20) NOT NULL,
  `hiredate` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This is flight table to hold all the information related to flight
CREATE TABLE  `flight` (
  `flightid` int(5) NOT NULL AUTO_INCREMENT,
  `flightname` varchar(10) NOT NULL,
  `airlinename` varchar(50),
  `source` int(10)NOT NULL,
  `destination` varchar(40) NOT NULL,
  `departuretime` varchar(40) NOT NULL,
  `arrivaltime` varchar(40) NOT NULL,
  `status` varchar(10) NOT NULL,
  `totalseats` int(20) NOT NULL,
  `seatsreserved` int(10) NOT NULL,
  `daysofweek` varchar(50) NOT NULL, -- Comma separated days for this flight
  `flyingstartdate` TIMESTAMP NOT NULL,
  `flyingenddate` TIMESTAMP NOT NULL,
  PRIMARY KEY  (`flightid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table holds what all date a flight will fly with its crew id
CREATE TABLE  `flightflyinginformation` (
  `flightid` int(5) NOT NULL,
  `dateofflying` TIMESTAMP NOT NULL,
  `employeeid` int(11) NULL, 
  FOREIGN KEY (flightid) REFERENCES flight(flightid),
  FOREIGN KEY (employeeid) REFERENCES employee(employeeid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- This table will hold the reservation information
CREATE TABLE `reservation`(
	`pnr` int(5) NOT NULL AUTO_INCREMENT,
	`customerid` int(11) NOT NULL,
	`flightid` int(5) NOT NULL,
	`creditcardnumber` int(16) NOT NULL,
	`dateofbooking` TIMESTAMP NOT NULL,
	`dateofflying` TIMESTAMP NOT NULL,
	FOREIGN KEY (flightid) REFERENCES flight(flightid),
  	FOREIGN KEY (customerid) REFERENCES customer(customerid),
	PRIMARY KEY  (`pnr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
