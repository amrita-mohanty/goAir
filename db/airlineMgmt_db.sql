CREATE DATABASE  IF NOT EXISTS `airline`
USE `airline`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `passportnum` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `description` varchar(20) NOT NULL,
  `position` varchar(20) NOT NULL,
  `hiredate` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `flight`;
CREATE TABLE  `flight` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `flightnumber` int(5)NOT NULL,
  `airlinename` varchar(50) NOT NULL,
  `source` int(10)NOT NULL,
  `totalseats` int(20) NOT NULL,
  `destination` varchar(40) NOT NULL,
  `arrivaltime` varchar(40) NOT NULL,
  `status` varchar(10) NOT NULL,
  `numberseatsreserved` int(10)NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
