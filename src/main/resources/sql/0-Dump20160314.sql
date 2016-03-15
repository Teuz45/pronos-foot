-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: pronos
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baremespoints`
--

DROP TABLE IF EXISTS `baremespoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baremespoints` (
  `cdBareme` varchar(10) NOT NULL,
  `nbPoints` int(11) NOT NULL,
  PRIMARY KEY (`cdBareme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baremespoints`
--

LOCK TABLES `baremespoints` WRITE;
/*!40000 ALTER TABLE `baremespoints` DISABLE KEYS */;
/*!40000 ALTER TABLE `baremespoints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bonus`
--

DROP TABLE IF EXISTS `bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonus` (
  `idBonus` int(11) NOT NULL AUTO_INCREMENT,
  `cdBonus` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `nbPointsBonus` int(11) DEFAULT '0',
  PRIMARY KEY (`idBonus`),
  KEY `fkuserbonus_idx` (`user`),
  CONSTRAINT `fkuserbonus` FOREIGN KEY (`user`) REFERENCES `users` (`login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonus`
--

LOCK TABLES `bonus` WRITE;
/*!40000 ALTER TABLE `bonus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bonus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipes`
--

DROP TABLE IF EXISTS `equipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipes` (
  `cdEquipe` varchar(3) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `coeffUEFA` int(11) DEFAULT NULL,
  `logo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cdEquipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipes`
--

LOCK TABLES `equipes` WRITE;
/*!40000 ALTER TABLE `equipes` DISABLE KEYS */;
INSERT INTO `equipes` VALUES ('ALB','Albanie',NULL,NULL),('ALL','Allemagne',NULL,NULL),('ANG','Angleterre',NULL,NULL),('AUT','Autriche',NULL,NULL),('BEL','Belgique',NULL,NULL),('CRO','Croatie',NULL,NULL),('ESP','Espagne',NULL,NULL),('FRA','France',NULL,NULL),('GAL','Pays de Galles',NULL,NULL),('HON','Hongrie',NULL,NULL),('IRL','République d\'Irlande',NULL,NULL),('IRN','Irlande du Nors',NULL,NULL),('ISL','Islande',NULL,NULL),('ITA','Italie',NULL,NULL),('POL','Pologne',NULL,NULL),('POR','Portugal',NULL,NULL),('ROU','Roumanie',NULL,NULL),('RUS','Russie',NULL,NULL),('SLO','Slovaquie',NULL,NULL),('SUE','Suède',NULL,NULL),('SUI','Suisse',NULL,NULL),('TCH','République tchèque',NULL,NULL),('TUR','Turquie',NULL,NULL),('UKR','Ukraine',NULL,NULL);
/*!40000 ALTER TABLE `equipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchs`
--

DROP TABLE IF EXISTS `matchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchs` (
  `numMatch` int(11) NOT NULL AUTO_INCREMENT,
  `phase` varchar(10) NOT NULL,
  `groupe` varchar(1) DEFAULT NULL,
  `localisation` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`numMatch`),
  KEY `fkmatchsphases_idx` (`phase`),
  KEY `fkmatchsstades_idx` (`localisation`),
  CONSTRAINT `fkmatchsphases` FOREIGN KEY (`phase`) REFERENCES `phases` (`cdPhase`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkmatchsstades` FOREIGN KEY (`localisation`) REFERENCES `stades` (`idStade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchs`
--

LOCK TABLES `matchs` WRITE;
/*!40000 ALTER TABLE `matchs` DISABLE KEYS */;
INSERT INTO `matchs` VALUES (1,'GROUPES','A',1,'2016-06-10 21:00:00'),(2,'GROUPES','A',10,'2016-06-11 15:00:00'),(3,'GROUPES','B',4,'2016-06-11 18:00:00'),(4,'GROUPES','B',3,'2016-06-11 21:00:00'),(5,'GROUPES','D',2,'2016-06-12 15:00:00'),(6,'GROUPES','C',9,'2016-06-12 18:00:00'),(7,'GROUPES','C',7,'2016-06-12 21:00:00'),(8,'GROUPES','D',6,'2016-06-13 15:00:00'),(9,'GROUPES','E',1,'2016-06-13 18:00:00'),(10,'GROUPES','E',5,'2016-06-13 21:00:00'),(11,'GROUPES','F',4,'2016-06-14 18:00:00'),(12,'GROUPES','F',8,'2016-06-14 21:00:00'),(13,'GROUPES','B',7,'2016-06-15 15:00:00'),(14,'GROUPES','A',2,'2016-06-15 18:00:00'),(15,'GROUPES','A',3,'2016-06-15 21:00:00'),(16,'GROUPES','B',10,'2016-06-16 15:00:00'),(17,'GROUPES','C',5,'2016-06-16 18:00:00'),(18,'GROUPES','C',1,'2016-06-16 21:00:00'),(19,'GROUPES','E',6,'2016-06-17 15:00:00'),(20,'GROUPES','D',8,'2016-06-17 18:00:00'),(21,'GROUPES','D',9,'2016-06-17 21:00:00'),(22,'GROUPES','E',4,'2016-06-18 15:00:00'),(23,'GROUPES','F',3,'2016-06-18 18:00:00'),(24,'GROUPES','F',2,'2016-06-18 21:00:00'),(25,'GROUPES','A',5,'2016-06-19 21:00:00'),(26,'GROUPES','A',7,'2016-06-19 21:00:00'),(27,'GROUPES','B',6,'2016-06-20 21:00:00'),(28,'GROUPES','B',8,'2016-06-20 21:00:00'),(29,'GROUPES','C',3,'2016-06-21 18:00:00'),(30,'GROUPES','C',2,'2016-06-21 18:00:00'),(31,'GROUPES','D',10,'2016-06-21 21:00:00'),(32,'GROUPES','D',4,'2016-06-21 21:00:00'),(33,'GROUPES','F',1,'2016-06-22 18:00:00'),(34,'GROUPES','F',5,'2016-06-22 18:00:00'),(35,'GROUPES','E',7,'2016-06-22 21:00:00'),(36,'GROUPES','E',9,'2016-06-22 21:00:00'),(37,'HUITIEMES',NULL,8,'2016-06-25 15:00:00'),(38,'HUITIEMES',NULL,2,'2016-06-25 18:00:00'),(39,'HUITIEMES',NULL,10,'2016-06-25 21:00:00'),(40,'HUITIEMES',NULL,5,'2016-06-26 15:00:00'),(41,'HUITIEMES',NULL,7,'2016-06-26 18:00:00'),(42,'HUITIEMES',NULL,6,'2016-06-26 21:00:00'),(43,'HUITIEMES',NULL,1,'2016-06-27 18:00:00'),(44,'HUITIEMES',NULL,9,'2016-06-27 21:00:00'),(45,'QUARTS',NULL,3,'2016-06-30 21:00:00'),(46,'QUARTS',NULL,7,'2016-07-01 21:00:00'),(47,'QUARTS',NULL,4,'2016-07-02 21:00:00'),(48,'QUARTS',NULL,1,'2016-07-03 21:00:00'),(49,'DEMIS',NULL,5,'2016-07-04 21:00:00'),(50,'DEMIS',NULL,3,'2016-07-07 21:00:00'),(51,'FINALE',NULL,1,'2016-07-10 21:00:00');
/*!40000 ALTER TABLE `matchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phases`
--

DROP TABLE IF EXISTS `phases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phases` (
  `cdPhase` varchar(10) NOT NULL,
  `libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`cdPhase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phases`
--

LOCK TABLES `phases` WRITE;
/*!40000 ALTER TABLE `phases` DISABLE KEYS */;
INSERT INTO `phases` VALUES ('DEMIS','Demi-finales'),('FINALE','Finale'),('GROUPES','Groupes'),('HUITIEMES','Huitièmes de finale'),('QUARTS','Quarts de finale');
/*!40000 ALTER TABLE `phases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pronosticsmatchs`
--

DROP TABLE IF EXISTS `pronosticsmatchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pronosticsmatchs` (
  `idPronosticMatch` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `match` int(11) NOT NULL,
  `equipeDom` varchar(3) NOT NULL,
  `equipeExt` varchar(3) NOT NULL,
  `scoreDom` int(11) NOT NULL,
  `scoreExt` int(11) NOT NULL,
  `scorePenDom` int(11) DEFAULT NULL,
  `scorePenExt` int(11) DEFAULT NULL,
  `nbPointsMatch` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPronosticMatch`),
  KEY `fkuserpronostics_idx` (`user`),
  KEY `fkpronosticsequipeaway_idx` (`equipeExt`),
  KEY `fkpronosticsequipehome_idx` (`equipeDom`),
  KEY `fkpronosticsmatch_idx` (`match`),
  CONSTRAINT `fkpronosticsequipeaway` FOREIGN KEY (`equipeExt`) REFERENCES `equipes` (`cdEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkpronosticsequipehome` FOREIGN KEY (`equipeDom`) REFERENCES `equipes` (`cdEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkpronosticsmatch` FOREIGN KEY (`match`) REFERENCES `matchs` (`numMatch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkuserpronostics` FOREIGN KEY (`user`) REFERENCES `users` (`login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pronosticsmatchs`
--

LOCK TABLES `pronosticsmatchs` WRITE;
/*!40000 ALTER TABLE `pronosticsmatchs` DISABLE KEYS */;
/*!40000 ALTER TABLE `pronosticsmatchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultats`
--

DROP TABLE IF EXISTS `resultats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resultats` (
  `idMatch` int(11) NOT NULL,
  `equipeDom` varchar(3) NOT NULL,
  `equipeExt` varchar(3) NOT NULL,
  `scoreDom` int(11) DEFAULT NULL,
  `scoreExt` int(11) DEFAULT NULL,
  `scorePenDom` int(11) DEFAULT NULL,
  `scorePenExt` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMatch`),
  KEY `fkresultatsequipeaway_idx` (`equipeExt`),
  KEY `fkresultatsequipehome_idx` (`equipeDom`),
  CONSTRAINT `fkresultatsequipeaway` FOREIGN KEY (`equipeExt`) REFERENCES `equipes` (`cdEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkresultatsequipehome` FOREIGN KEY (`equipeDom`) REFERENCES `equipes` (`cdEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkresultatsmatchs` FOREIGN KEY (`idMatch`) REFERENCES `matchs` (`numMatch`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultats`
--

LOCK TABLES `resultats` WRITE;
/*!40000 ALTER TABLE `resultats` DISABLE KEYS */;
INSERT INTO `resultats` VALUES (1,'FRA','ROU',NULL,NULL,NULL,NULL),(2,'ALB','SUI',NULL,NULL,NULL,NULL),(3,'GAL','SLO',NULL,NULL,NULL,NULL),(4,'ANG','RUS',NULL,NULL,NULL,NULL),(5,'TUR','CRO',NULL,NULL,NULL,NULL),(6,'POL','IRN',NULL,NULL,NULL,NULL),(7,'ALL','UKR',NULL,NULL,NULL,NULL),(8,'ESP','TCH',NULL,NULL,NULL,NULL),(9,'IRL','SUE',NULL,NULL,NULL,NULL),(10,'BEL','ITA',NULL,NULL,NULL,NULL),(11,'AUT','HON',NULL,NULL,NULL,NULL),(12,'POR','ISL',NULL,NULL,NULL,NULL),(13,'RUS','SLO',NULL,NULL,NULL,NULL),(14,'ROU','SUI',NULL,NULL,NULL,NULL),(15,'FRA','ALB',NULL,NULL,NULL,NULL),(16,'ANG','GAL',NULL,NULL,NULL,NULL),(17,'UKR','IRN',NULL,NULL,NULL,NULL),(18,'ALL','POL',NULL,NULL,NULL,NULL),(19,'ITA','SUE',NULL,NULL,NULL,NULL),(20,'TCH','CRO',NULL,NULL,NULL,NULL),(21,'ESP','TUR',NULL,NULL,NULL,NULL),(22,'BEL','IRL',NULL,NULL,NULL,NULL),(23,'ISL','HON',NULL,NULL,NULL,NULL),(24,'POR','AUT',NULL,NULL,NULL,NULL),(25,'ROU','ALB',NULL,NULL,NULL,NULL),(26,'SUI','FRA',NULL,NULL,NULL,NULL),(27,'RUS','GAL',NULL,NULL,NULL,NULL),(28,'SLO','ANG',NULL,NULL,NULL,NULL),(29,'UKR','POL',NULL,NULL,NULL,NULL),(30,'IRN','ALL',NULL,NULL,NULL,NULL),(31,'TCH','TUR',NULL,NULL,NULL,NULL),(32,'CRO','ESP',NULL,NULL,NULL,NULL),(33,'ISL','AUT',NULL,NULL,NULL,NULL),(34,'HON','POR',NULL,NULL,NULL,NULL),(35,'ITA','IRL',NULL,NULL,NULL,NULL),(36,'SUE','BEL',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `resultats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stades`
--

DROP TABLE IF EXISTS `stades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stades` (
  `idStade` int(11) NOT NULL AUTO_INCREMENT,
  `nomStade` varchar(45) NOT NULL,
  `ville` varchar(45) NOT NULL,
  `pays` varchar(45) NOT NULL,
  PRIMARY KEY (`idStade`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stades`
--

LOCK TABLES `stades` WRITE;
/*!40000 ALTER TABLE `stades` DISABLE KEYS */;
INSERT INTO `stades` VALUES (1,'Stade de France','Saint-Denis','France'),(2,'Parc des Princes','Paris','France'),(3,'Stade Vélodrome','Marseille','France'),(4,'Stade Bordeaux Atlantique','Bordeaux','France'),(5,'Stade des Lumières','Lyon','France'),(6,'Stadium de Toulouse','Toulouse','France'),(7,'Stade Pierre Mauroy','Lille','France'),(8,'Stade Geoffroy Guichard','Saint-Etienne','France'),(9,'Allianz Riviera','Nice','France'),(10,'Stade Bollaert-Delelis','Lens','France');
/*!40000 ALTER TABLE `stades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `login` varchar(45) NOT NULL,
  `profil` varchar(10) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-14 17:14:42
