
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
  `AddressId` int(11) NOT NULL AUTO_INCREMENT,
  `StreetAddress` varchar(40) NOT NULL,
  `PostalCode` varchar(5) NOT NULL,
  PRIMARY KEY (`AddressId`),
  KEY `PostalCode` (`PostalCode`),
  CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`PostalCode`) REFERENCES `Postal` (`PostalCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.Customer
CREATE TABLE IF NOT EXISTS `Customer` (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(20) NOT NULL,
  `Lastname` varchar(20) NOT NULL,
  `BillingAddress` int(11) NOT NULL,
  PRIMARY KEY (`CustomerId`),
  KEY `BillingAddress` (`BillingAddress`),
  CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`BillingAddress`) REFERENCES `Address` (`AddressId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Tietojen vientiä ei oltu valittu.
-- Dumping structure for taulu PestControl.CustomerVisit
CREATE TABLE IF NOT EXISTS `CustomerVisit` (
  `CustomerVisitId` int(11) NOT NULL AUTO_INCREMENT,
  `Datetime` datetime NOT NULL,
  `Customer` int(11) NOT NULL,
  `Address` int(11) NOT NULL,
  PRIMARY KEY (`CustomerVisitId`),
  KEY `Customer` (`Customer`),
  KEY `Address` (`Address`),
  CONSTRAINT `CustomerVisit_ibfk_1` FOREIGN KEY (`Customer`) REFERENCES `Customer` (`CustomerId`),
  CONSTRAINT `CustomerVisit_ibfk_2` FOREIGN KEY (`Address`) REFERENCES `Address` (`AddressId`)
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
CREATE TABLE IF NOT EXISTS `CustomerVisitPest` (
  `CustomerVisitId` int(11) NOT NULL,
  `PestId` int(11) NOT NULL,
  PRIMARY KEY (`CustomerVisitId`,`PestId`),
  KEY `PestId` (`PestId`),
  CONSTRAINT `Relationship_ibfk_1` FOREIGN KEY (`CustomerVisitId`) REFERENCES `CustomerVisit` (`CustomerVisitId`),
  CONSTRAINT `Relationship_ibfk_2` FOREIGN KEY (`PestId`) REFERENCES `Pest` (`PestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* TODO:
Add indexing for Address.StreetAddress
Add onDelete and onDelete rules for tables
*/

-- Tietojen vientiä ei oltu valittu.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;