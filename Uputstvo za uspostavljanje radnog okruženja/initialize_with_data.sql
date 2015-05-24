CREATE DATABASE  IF NOT EXISTS `tim4` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `tim4`;
create user 'EtfSI2014'@'localhost' identified by '2014SIEtf';
GRANT ALL PRIVILEGES ON *.* TO 'EtfSI2014'@'localhost' IDENTIFIED BY '2014SIEtf' WITH GRANT OPTION;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: tim4
-- ------------------------------------------------------
-- Server version	5.6.12-log

--
-- Table structure for table `fakture_iznajmljivanje`
--

DROP TABLE IF EXISTS `fakture_iznajmljivanje`;
CREATE TABLE `fakture_iznajmljivanje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broj_fakture` varchar(20) DEFAULT NULL,
  `komitent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `komitent` (`komitent`),
  CONSTRAINT `fakture_iznajmljivanje_ibfk_1` FOREIGN KEY (`komitent`) REFERENCES `komitenti` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fakture_iznajmljivanje`
--

LOCK TABLES `fakture_iznajmljivanje` WRITE;
INSERT INTO `fakture_iznajmljivanje` VALUES (1,'F2-2015',16),(2,'F23-2015',16),(3,'F24-2015',16),(4,'F25-2015',16),(5,'F26-2015',16),(6,'F27-2015',15),(7,'F28-2015',15),(8,'F31-2015',15),(9,'F32-2015',15),(10,'F33-2015',15),(11,'F34-2015',15),(12,'F36-2015',15),(13,'F37-2015',15),(14,'F38-2015',15);
UNLOCK TABLES;

--
-- Table structure for table `fakture_prodaja`
--

DROP TABLE IF EXISTS `fakture_prodaja`;
CREATE TABLE `fakture_prodaja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broj_fakture` varchar(20) DEFAULT NULL,
  `komitent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `komitent` (`komitent`),
  CONSTRAINT `fakture_prodaja_ibfk_1` FOREIGN KEY (`komitent`) REFERENCES `komitenti` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fakture_prodaja`
--

LOCK TABLES `fakture_prodaja` WRITE;
INSERT INTO `fakture_prodaja` VALUES (1,'F1-2015',16),(2,'F3-1',16),(3,'F4-1',16),(4,'F5-1',15),(5,'F6-1',15),(6,'F7-1',15),(7,'F8-1',15),(8,'F9-1',16),(9,'F10-1',16),(10,'F11-1',16),(11,'F12-1',16),(12,'F13-1',15),(13,'F14-1',15),(14,'F15-1',15),(15,'F16-1',15),(16,'F17-1',15),(17,'F18-1',15),(18,'F19-1',15),(19,'F20-1',15),(20,'F21-1',15),(21,'F22-1',15),(22,'F29-2015',16),(23,'F30-2015',16),(24,'F35-2015',16),(25,'F39-2015',16);
UNLOCK TABLES;

--
-- Table structure for table `izvjestaji`
--

DROP TABLE IF EXISTS `izvjestaji`;
CREATE TABLE `izvjestaji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_izvjestaja` varchar(100) DEFAULT NULL,
  `broj_izvjestaja` varchar(10) DEFAULT NULL,
  `datum_izvjestaja` date DEFAULT NULL,
  `parametar_izvjestaja` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `izvjestaji`
--

LOCK TABLES `izvjestaji` WRITE;
INSERT INTO `izvjestaji` VALUES (2,'Izvjestaj za pojedinacni plinski rezervoar','1-115','2015-05-18','123456'),(3,'Izvjestaj za pojedinacni plinski rezervoar','2-2015','2015-05-18','123456'),(4,'Izvjestaj za pojedinacni plinski rezervoar','3-2015','2015-05-18','123456'),(5,'Izvjestaj za pojedinacni plinski rezervoar','4-2015','2015-05-19','123456'),(6,'Izvjestaj za pojedinacni plinski rezervoar','5-2015','2015-05-19','123456'),(7,'Izvjestaj za pojedinacni plinski rezervoar','6-2015','2015-05-19','123476'),(8,'Izvjestaj za pojedinacni plinski rezervoar','7-2015','2015-05-19','123456'),(9,'Izvjestaj za pojedinacni plinski rezervoar','8-2015','2015-05-20','Tito Titic'),(10,'Izvjestaj za pojedinacni plinski rezervoar','9-2015','2015-05-20','Tito Titic'),(11,'Izvjestaj za pojedinacni plinski rezervoar','10-2015','2015-05-20','Firma d.o.o'),(12,'Izvjestaj za pojedinacni plinski rezervoar','11-2015','2015-05-20','123456'),(13,'Izvjestaj za pojedinacni plinski rezervoar','12-2015','2015-05-20','123456'),(14,'Izvjestaj za pojedinacni plinski rezervoar','13-2015','2015-05-21','Firma d.o.o'),(15,'Izvjestaj za pojedinacni plinski rezervoar','14-2015','2015-05-21','Tito Titic'),(16,'Izvjestaj za pojedinacni plinski rezervoar','15-2015','2015-05-23','123456'),(17,'Izvjestaj za pojedinacni plinski rezervoar','16-2015','2015-05-23','Titova Titica'),(18,'Izvjestaj za pojedinacni plinski rezervoar','17-2015','2015-05-23','123456'),(19,'Izvjestaj za pojedinacni plinski rezervoar','18-2015','2015-05-23','Titova Titica'),(20,'Izvjestaj za pojedinacni plinski rezervoar','19-2015','2015-05-23','Titova Titica'),(21,'Izvjestaj za pojedinacni plinski rezervoar','20-2015','2015-05-23','Titoxxxxx Konj'),(22,'Izvjestaj za pojedinacni plinski rezervoar','21-2015','2015-05-23','Titova Titica'),(23,'Izvjestaj za pojedinacni plinski rezervoar','22-2015','2015-05-23','123456'),(24,'Izvjestaj za pojedinacni plinski rezervoar','23-2015','2015-05-23','Titoxxxxx Konj'),(25,'Izvjestaj za pojedinacni plinski rezervoar','24-2015','2015-05-23','Neki Covjek'),(26,'Izvjestaj za pojedinacni plinski rezervoar','25-2015','2015-05-23','Firma d.o.o'),(27,'Izvjestaj za pojedinacni plinski rezervoar','26-2015','2015-05-23','Kompanija d.d');
UNLOCK TABLES;

--
-- Table structure for table `izvjestaji_stanja_skladiste`
--

DROP TABLE IF EXISTS `izvjestaji_stanja_skladiste`;
CREATE TABLE `izvjestaji_stanja_skladiste` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_izvjestaja` varchar(20) DEFAULT NULL,
  `broj_izvjestaja` varchar(10) DEFAULT NULL,
  `datum_izvjestaja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `izvjestaji_stanja_skladiste`
--

LOCK TABLES `izvjestaji_stanja_skladiste` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `izvjestaji_stanja_stavke`
--

DROP TABLE IF EXISTS `izvjestaji_stanja_stavke`;
CREATE TABLE `izvjestaji_stanja_stavke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `izvjestaj` int(11) DEFAULT NULL,
  `rezervoar` int(11) DEFAULT NULL,
  `kapacitet` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `izvjestaj` (`izvjestaj`),
  KEY `rezervoar` (`rezervoar`),
  CONSTRAINT `izvjestaji_stanja_stavke_ibfk_1` FOREIGN KEY (`izvjestaj`) REFERENCES `izvjestaji_stanja_skladiste` (`id`),
  CONSTRAINT `izvjestaji_stanja_stavke_ibfk_2` FOREIGN KEY (`rezervoar`) REFERENCES `plinski_rezervoari` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `izvjestaji_stanja_stavke`
--

LOCK TABLES `izvjestaji_stanja_stavke` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `komitenti`
--

DROP TABLE IF EXISTS `komitenti`;
CREATE TABLE `komitenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_komitenta` varchar(20) DEFAULT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  `broj_telefona` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `JMB` varchar(13) DEFAULT NULL,
  `broj_licne_karte` varchar(10) DEFAULT NULL,
  `nazivFirme` varchar(100) DEFAULT NULL,
  `pdvBroj` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `komitenti`
--

LOCK TABLES `komitenti` WRITE;
INSERT INTO `komitenti` VALUES (15,'Fizicko lice','Adema Buce 22','062754896','covjek@covjek.ba','Neki','Covjek','1201994150003','22BTZ9856',NULL,NULL),(16,'Pravno lice','Adema Buce 22','062754896','titox@titox.ba',NULL,NULL,NULL,NULL,'Firma d.o.o','11256398541'),(17,'Pravno lice','Adema Cube 29','061256987','kompanija@kompanija.ba',NULL,NULL,NULL,NULL,'Kompanija d.d','11234455698714523');
UNLOCK TABLES;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip` varchar(30) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `lozinka` varchar(100) DEFAULT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `broj_licne_karte` varchar(10) DEFAULT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  `broj_telefona` varchar(15) DEFAULT NULL,
  `datum_zaposljavanja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
INSERT INTO `korisnici` VALUES (1,'Administrator','admin','admin','Adminko','Admincic','11BZT290','Tite 28','033456786','2015-05-22'),(2,'Korisnik','korisnik','korisnik','korisnik','korisnikovic','11BZU7894','Tuzlanska bb','065123456','2015-05-22');
UNLOCK TABLES;

--
-- Table structure for table `plinski_rezervoari`
--

DROP TABLE IF EXISTS `plinski_rezervoari`;
CREATE TABLE `plinski_rezervoari` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serijski_broj` varchar(20) DEFAULT NULL,
  `kapacitet` int(11) NOT NULL,
  `tezina` int(11) NOT NULL,
  `napunjenost` int(11) NOT NULL,
  `tip` varchar(15) DEFAULT NULL,
  `datum_zadnjeg_bazdarenja` date DEFAULT NULL,
  `lokacija` varchar(100) DEFAULT NULL,
  `trenutni_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serijski_broj` (`serijski_broj`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plinski_rezervoari`
--

LOCK TABLES `plinski_rezervoari` WRITE;
INSERT INTO `plinski_rezervoari` VALUES (1,'123456',50,50,1,'Nadzemni','2010-05-20','Adema Buce 22','Prodat'),(3,'123476',50,50,1,'Nadzemni','2010-05-20','Adema Buce 22','Prodat'),(4,'123489',150,150,1,'Nadzemni','2015-05-21','Adema Buce 22','Iznajmljen do 28-05-2015'),(6,'123488',100,100,1,'Nadzemni','2015-05-29','Adema Buce 22','Iznajmljen do 28-05-2015'),(7,'123452',200,200,1,'Nadzemni','2015-05-27','Adema Buce 22','Iznajmljen do 29-05-2015'),(8,'125648',130,130,1,'Nadzemni','2015-05-21','Adema Buce 22','Prodat'),(9,'125897',180,180,1,'Nadzemni','2015-05-21','Adema Buce 22','Iznajmljen do 27-05-2015'),(10,'125478',125,125,1,'Nadzemni','2015-05-29','Adema Buce 22','Iznajmljen do 27-05-2015'),(11,'125469',120,120,1,'Nadzemni','2015-05-29','Adema Buce 22','Iznajmljen do 30-05-2015'),(12,'125468',130,130,1,'Nadzemni','2015-05-29','Skladiste','Skladiste'),(13,'102546',150,150,1,'Nadzemni','2017-05-16','Skladiste','Skladiste'),(14,'125487',160,160,1,'Nadzemni','2013-05-16','Skladiste','Skladiste');
UNLOCK TABLES;

--
-- Table structure for table `promjene_na_rezervoarima`
--

DROP TABLE IF EXISTS `promjene_na_rezervoarima`;
CREATE TABLE `promjene_na_rezervoarima` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serijski_broj` varchar(20) DEFAULT NULL,
  `datum_promjene` date DEFAULT NULL,
  `tip_promjene` int(11) DEFAULT NULL,
  `opis_promjene` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `serijski_broj` (`serijski_broj`),
  KEY `tip_promjene` (`tip_promjene`),
  CONSTRAINT `promjene_na_rezervoarima_ibfk_1` FOREIGN KEY (`serijski_broj`) REFERENCES `plinski_rezervoari` (`serijski_broj`),
  CONSTRAINT `promjene_na_rezervoarima_ibfk_2` FOREIGN KEY (`tip_promjene`) REFERENCES `sifarnik_promjena` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promjene_na_rezervoarima`
--

LOCK TABLES `promjene_na_rezervoarima` WRITE;
INSERT INTO `promjene_na_rezervoarima` VALUES (5,'123456','2015-05-17',2,'Promjena lokacije na Adresa bb'),(6,'123456','2015-05-17',3,'Punjenje na 50 l'),(9,'123456','2015-05-17',1,'Bazdarenje datum sljedeceg bazdarenja 2021-05-17');
UNLOCK TABLES;

--
-- Table structure for table `sifarnik_promjena`
--

DROP TABLE IF EXISTS `sifarnik_promjena`;
CREATE TABLE `sifarnik_promjena` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sifra_promjene` int(11) DEFAULT NULL,
  `naziv_promjene` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sifra_promjene` (`sifra_promjene`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sifarnik_promjena`
--

LOCK TABLES `sifarnik_promjena` WRITE;
INSERT INTO `sifarnik_promjena` VALUES (1,1,'Bazdarenje'),(2,2,'Promjena lokacije'),(3,3,'Punjenje');
UNLOCK TABLES;

--
-- Table structure for table `skladiste_plinskih_boca`
--

DROP TABLE IF EXISTS `skladiste_plinskih_boca`;
CREATE TABLE `skladiste_plinskih_boca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kapacitet` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `cijena` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skladiste_plinskih_boca`
--

LOCK TABLES `skladiste_plinskih_boca` WRITE;
INSERT INTO `skladiste_plinskih_boca` VALUES (1,5,101,10),(2,10,105,20),(3,15,105,30);
UNLOCK TABLES;

--
-- Table structure for table `stavkefakture_iznajmljivanje`
--

DROP TABLE IF EXISTS `stavkefakture_iznajmljivanje`;
CREATE TABLE `stavkefakture_iznajmljivanje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `faktura` int(11) DEFAULT NULL,
  `rezervoar` varchar(20) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `tip_rezervoara` varchar(20) DEFAULT NULL,
  `iznajmljeno_do` date DEFAULT NULL,
  `cijena` double DEFAULT NULL,
  `kapacitetBoce` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faktura` (`faktura`),
  KEY `rezervoar` (`rezervoar`),
  CONSTRAINT `stavkefakture_iznajmljivanje_ibfk_1` FOREIGN KEY (`faktura`) REFERENCES `fakture_iznajmljivanje` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stavkefakture_iznajmljivanje`
--

LOCK TABLES `stavkefakture_iznajmljivanje` WRITE;
INSERT INTO `stavkefakture_iznajmljivanje` VALUES (1,1,NULL,50,NULL,'2015-05-23',100,20),(2,1,NULL,50,NULL,'2015-05-23',100,30),(3,1,'123456',1,'Nadzemni','2015-05-23',500,100),(7,5,'3',1,'Nadzemni','2015-05-25',525,50),(8,5,NULL,5,'Plinska boca','2015-05-25',10,5),(9,5,NULL,10,'Plinska boca','2015-05-25',20,10),(10,6,'1',1,'Nadzemni','2015-05-28',525,50),(11,6,NULL,5,'Plinska boca','2015-05-28',10,5),(12,6,NULL,10,'Plinska boca','2015-05-28',20,10),(13,6,NULL,20,'Plinska boca','2015-05-28',30,15),(14,7,'3',1,'Nadzemni','2015-05-28',525,50),(15,7,NULL,5,'Plinska boca','2015-05-28',10,5),(16,7,NULL,10,'Plinska boca','2015-05-28',20,10),(17,7,NULL,15,'Plinska boca','2015-05-28',30,15),(18,8,'4',1,'Nadzemni','2015-05-28',1575,150),(19,8,NULL,10,'Plinska boca','2015-05-28',10,5),(20,8,NULL,10,'Plinska boca','2015-05-28',20,10),(21,8,NULL,10,'Plinska boca','2015-05-28',30,15),(22,9,'4',1,'Nadzemni','2015-05-28',1575,150),(23,10,'6',1,'Nadzemni','2015-05-28',1050,100),(24,10,NULL,5,'Plinska boca','2015-05-28',10,5),(25,10,NULL,5,'Plinska boca','2015-05-28',20,10),(26,11,'7',1,'Nadzemni','2015-05-29',2100,200),(27,11,NULL,20,'Plinska boca','2015-05-29',10,5),(28,11,NULL,20,'Plinska boca','2015-05-29',20,10),(29,11,NULL,20,'Plinska boca','2015-05-29',30,15),(30,12,'9',1,'Nadzemni','2015-05-27',1890,180),(31,12,NULL,5,'Plinska boca','2015-05-27',10,5),(32,12,NULL,5,'Plinska boca','2015-05-27',20,10),(33,12,NULL,5,'Plinska boca','2015-05-27',30,15),(34,13,'10',1,'Nadzemni','2015-05-27',1312.5,125),(35,13,NULL,5,'Plinska boca','2015-05-27',10,5),(36,13,NULL,5,'Plinska boca','2015-05-27',20,10),(37,13,NULL,5,'Plinska boca','2015-05-27',30,15),(38,14,'11',1,'Nadzemni','2015-05-30',1260,120),(39,14,NULL,5,'Plinska boca','2015-05-30',10,5),(40,14,NULL,5,'Plinska boca','2015-05-30',20,10),(41,14,NULL,5,'Plinska boca','2015-05-30',30,15);
UNLOCK TABLES;

--
-- Table structure for table `stavkefakture_prodaja`
--

DROP TABLE IF EXISTS `stavkefakture_prodaja`;
CREATE TABLE `stavkefakture_prodaja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `faktura` int(11) DEFAULT NULL,
  `rezervoar` varchar(20) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `tip_rezervoara` varchar(100) DEFAULT NULL,
  `cijena` double DEFAULT NULL,
  `kapacitetBoce` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faktura` (`faktura`),
  KEY `rezervoar` (`rezervoar`),
  CONSTRAINT `stavkefakture_prodaja_ibfk_1` FOREIGN KEY (`faktura`) REFERENCES `fakture_prodaja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stavkefakture_prodaja`
--

LOCK TABLES `stavkefakture_prodaja` WRITE;
INSERT INTO `stavkefakture_prodaja` VALUES (5,1,'123456',1,'Veliki plinski rezervoar',100,100),(6,1,'123476',1,'Veliki plinski rezervoar',100,100),(9,8,NULL,5,'Plinska boca',10,5),(10,8,NULL,5,'Plinska boca',10,5),(11,8,NULL,5,'Plinska boca',10,5),(12,8,NULL,5,'Plinska boca',10,5),(13,8,NULL,5,'Plinska boca',10,5),(14,8,NULL,5,'Plinska boca',20,10),(15,8,NULL,5,'Plinska boca',20,10),(16,8,NULL,5,'Plinska boca',20,10),(17,8,NULL,5,'Plinska boca',20,10),(18,8,NULL,5,'Plinska boca',20,10),(19,9,NULL,5,'Plinska boca',10,5),(20,9,NULL,5,'Plinska boca',10,5),(21,9,NULL,5,'Plinska boca',10,5),(22,9,NULL,5,'Plinska boca',10,5),(23,9,NULL,5,'Plinska boca',10,5),(24,9,NULL,5,'Plinska boca',20,10),(25,9,NULL,5,'Plinska boca',20,10),(26,9,NULL,5,'Plinska boca',20,10),(27,9,NULL,5,'Plinska boca',20,10),(28,9,NULL,5,'Plinska boca',20,10),(29,10,NULL,5,'Plinska boca',10,5),(30,10,NULL,5,'Plinska boca',10,5),(31,10,NULL,5,'Plinska boca',10,5),(32,10,NULL,5,'Plinska boca',10,5),(33,10,NULL,5,'Plinska boca',10,5),(34,10,NULL,5,'Plinska boca',20,10),(35,10,NULL,5,'Plinska boca',20,10),(36,10,NULL,5,'Plinska boca',20,10),(37,10,NULL,5,'Plinska boca',20,10),(38,10,NULL,5,'Plinska boca',20,10),(39,11,'1',1,'Nadzemni',525,50),(40,11,NULL,5,'Plinska boca',10,5),(41,11,NULL,5,'Plinska boca',10,5),(42,11,NULL,5,'Plinska boca',10,5),(43,11,NULL,5,'Plinska boca',10,5),(44,11,NULL,5,'Plinska boca',10,5),(45,11,NULL,5,'Plinska boca',20,10),(46,11,NULL,5,'Plinska boca',20,10),(47,11,NULL,5,'Plinska boca',20,10),(48,11,NULL,5,'Plinska boca',20,10),(49,11,NULL,5,'Plinska boca',20,10),(50,12,'1',1,'Nadzemni',525,50),(51,12,NULL,5,'Plinska boca',10,5),(52,12,NULL,5,'Plinska boca',10,5),(53,12,NULL,5,'Plinska boca',10,5),(54,12,NULL,5,'Plinska boca',10,5),(55,12,NULL,5,'Plinska boca',10,5),(56,12,NULL,5,'Plinska boca',20,10),(57,12,NULL,5,'Plinska boca',20,10),(58,12,NULL,5,'Plinska boca',20,10),(59,12,NULL,5,'Plinska boca',20,10),(60,12,NULL,5,'Plinska boca',20,10),(61,13,'1',1,'Nadzemni',525,50),(62,13,NULL,5,'Plinska boca',10,5),(63,13,NULL,5,'Plinska boca',10,5),(64,13,NULL,5,'Plinska boca',10,5),(65,13,NULL,5,'Plinska boca',10,5),(66,13,NULL,5,'Plinska boca',10,5),(67,13,NULL,5,'Plinska boca',20,10),(68,13,NULL,5,'Plinska boca',20,10),(69,13,NULL,5,'Plinska boca',20,10),(70,13,NULL,5,'Plinska boca',20,10),(71,13,NULL,5,'Plinska boca',20,10),(72,14,'1',1,'Nadzemni',525,50),(73,14,NULL,5,'Plinska boca',10,5),(74,14,NULL,5,'Plinska boca',10,5),(75,14,NULL,5,'Plinska boca',10,5),(76,14,NULL,5,'Plinska boca',10,5),(77,14,NULL,5,'Plinska boca',10,5),(78,14,NULL,5,'Plinska boca',20,10),(79,14,NULL,5,'Plinska boca',20,10),(80,14,NULL,5,'Plinska boca',20,10),(81,14,NULL,5,'Plinska boca',20,10),(82,14,NULL,5,'Plinska boca',20,10),(83,15,'1',1,'Nadzemni',525,50),(84,15,NULL,5,'Plinska boca',10,5),(85,15,NULL,5,'Plinska boca',10,5),(86,15,NULL,5,'Plinska boca',10,5),(87,15,NULL,5,'Plinska boca',10,5),(88,15,NULL,5,'Plinska boca',10,5),(89,15,NULL,5,'Plinska boca',20,10),(90,15,NULL,5,'Plinska boca',20,10),(91,15,NULL,5,'Plinska boca',20,10),(92,15,NULL,5,'Plinska boca',20,10),(93,15,NULL,5,'Plinska boca',20,10),(94,16,'1',1,'Nadzemni',525,50),(95,16,NULL,5,'Plinska boca',10,5),(96,16,NULL,5,'Plinska boca',10,5),(97,16,NULL,5,'Plinska boca',10,5),(98,16,NULL,5,'Plinska boca',10,5),(99,16,NULL,5,'Plinska boca',10,5),(100,16,NULL,5,'Plinska boca',20,10),(101,16,NULL,5,'Plinska boca',20,10),(102,16,NULL,5,'Plinska boca',20,10),(103,16,NULL,5,'Plinska boca',20,10),(104,16,NULL,5,'Plinska boca',20,10),(105,17,'1',1,'Nadzemni',525,50),(106,17,NULL,5,'Plinska boca',10,5),(107,17,NULL,5,'Plinska boca',10,5),(108,17,NULL,5,'Plinska boca',10,5),(109,17,NULL,5,'Plinska boca',10,5),(110,17,NULL,5,'Plinska boca',10,5),(111,17,NULL,5,'Plinska boca',20,10),(112,17,NULL,5,'Plinska boca',20,10),(113,17,NULL,5,'Plinska boca',20,10),(114,17,NULL,5,'Plinska boca',20,10),(115,17,NULL,5,'Plinska boca',20,10),(116,18,'1',1,'Nadzemni',525,50),(117,18,NULL,5,'Plinska boca',10,5),(118,18,NULL,5,'Plinska boca',10,5),(119,18,NULL,5,'Plinska boca',10,5),(120,18,NULL,5,'Plinska boca',10,5),(121,18,NULL,5,'Plinska boca',10,5),(122,18,NULL,5,'Plinska boca',20,10),(123,18,NULL,5,'Plinska boca',20,10),(124,18,NULL,5,'Plinska boca',20,10),(125,18,NULL,5,'Plinska boca',20,10),(126,18,NULL,5,'Plinska boca',20,10),(127,19,'1',1,'Nadzemni',525,50),(128,19,NULL,5,'Plinska boca',10,5),(129,19,NULL,5,'Plinska boca',10,5),(130,19,NULL,5,'Plinska boca',10,5),(131,19,NULL,5,'Plinska boca',10,5),(132,19,NULL,5,'Plinska boca',10,5),(133,19,NULL,5,'Plinska boca',20,10),(134,19,NULL,5,'Plinska boca',20,10),(135,19,NULL,5,'Plinska boca',20,10),(136,19,NULL,5,'Plinska boca',20,10),(137,19,NULL,5,'Plinska boca',20,10),(138,20,'1',1,'Nadzemni',525,50),(139,20,NULL,5,'Plinska boca',10,5),(140,20,NULL,5,'Plinska boca',10,5),(141,20,NULL,5,'Plinska boca',10,5),(142,20,NULL,5,'Plinska boca',10,5),(143,20,NULL,5,'Plinska boca',10,5),(144,20,NULL,5,'Plinska boca',20,10),(145,20,NULL,5,'Plinska boca',20,10),(146,20,NULL,5,'Plinska boca',20,10),(147,20,NULL,5,'Plinska boca',20,10),(148,20,NULL,5,'Plinska boca',20,10),(149,21,'1',1,'Nadzemni',525,50),(150,21,NULL,5,'Plinska boca',10,5),(151,21,NULL,5,'Plinska boca',20,10),(152,22,'1',1,'Nadzemni',525,50),(153,22,NULL,5,'Plinska boca',10,5),(154,22,NULL,10,'Plinska boca',20,10),(155,22,NULL,20,'Plinska boca',30,15),(156,23,'3',1,'Nadzemni',525,50),(157,23,NULL,5,'Plinska boca',10,5),(158,23,NULL,5,'Plinska boca',20,10),(159,23,NULL,5,'Plinska boca',30,15),(160,24,'8',1,'Nadzemni',1365,130),(161,24,NULL,5,'Plinska boca',10,5),(162,24,NULL,5,'Plinska boca',20,10),(163,24,NULL,5,'Plinska boca',30,15),(164,25,NULL,1,'Plinska boca',10,5);
UNLOCK TABLES;