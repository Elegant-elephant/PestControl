-- --------------------------------------------------------
-- Verkkotietokone:              10.114.32.27
-- Palvelinversio:               5.5.56-MariaDB - MariaDB Server
-- Server OS:                    Linux
-- HeidiSQL Versio:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for PestControl
CREATE DATABASE IF NOT EXISTS `PestControl` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `PestControl`;

-- Dumping structure for taulu PestControl.Address
CREATE TABLE IF NOT EXISTS `Address` (
  `StreetAddress` varchar(40) NOT NULL,
  `PostalCode` varchar(5) NOT NULL,
  PRIMARY KEY (`StreetAddress`),
  KEY `PostalCode` (`PostalCode`),
  CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`PostalCode`) REFERENCES `Postal` (`PostalCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.Customer
CREATE TABLE IF NOT EXISTS `Customer` (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(20) NOT NULL,
  `Lastname` varchar(20) NOT NULL,
  `BillingAddress` varchar(40) NOT NULL,
  PRIMARY KEY (`CustomerId`),
  KEY `BillingAddress` (`BillingAddress`),
  CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`BillingAddress`) REFERENCES `Address` (`StreetAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.CustomerVisit
CREATE TABLE IF NOT EXISTS `CustomerVisit` (
  `VisitId` int(11) NOT NULL AUTO_INCREMENT,
  `Datetime` datetime NOT NULL,
  `Id` int(11) NOT NULL,
  `StreetAddress` varchar(40) NOT NULL,
  PRIMARY KEY (`VisitId`),
  KEY `Id` (`Id`),
  KEY `StreetAddress` (`StreetAddress`),
  CONSTRAINT `CustomerVisit_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `Customer` (`CustomerId`),
  CONSTRAINT `CustomerVisit_ibfk_2` FOREIGN KEY (`StreetAddress`) REFERENCES `Address` (`StreetAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.Pest
CREATE TABLE IF NOT EXISTS `Pest` (
  `PestId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`PestId`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.Postal
CREATE TABLE IF NOT EXISTS `Postal` (
  `PostalCode` varchar(5) NOT NULL,
  `PostRegion` varchar(20) NOT NULL,
  PRIMARY KEY (`PostalCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.Relationship
CREATE TABLE IF NOT EXISTS `Relationship` (
  `VisitId` int(11) NOT NULL,
  `PestId` int(11) NOT NULL,
  PRIMARY KEY (`VisitId`,`PestId`),
  KEY `PestId` (`PestId`),
  CONSTRAINT `Relationship_ibfk_1` FOREIGN KEY (`VisitId`) REFERENCES `CustomerVisit` (`VisitId`),
  CONSTRAINT `Relationship_ibfk_2` FOREIGN KEY (`PestId`) REFERENCES `Pest` (`PestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
