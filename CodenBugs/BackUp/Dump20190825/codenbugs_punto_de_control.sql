-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: codenbugs
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `punto_de_control`
--

DROP TABLE IF EXISTS `punto_de_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `punto_de_control` (
  `id_pc` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_pc` varchar(20) NOT NULL,
  `vacio` tinyint(1) DEFAULT NULL,
  `tarifa` decimal(10,2) DEFAULT NULL,
  `capacidad` int(11) NOT NULL,
  `operador_asignado` int(11) NOT NULL,
  `ruta` int(11) DEFAULT NULL,
  `id_siguiente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pc`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto_de_control`
--

LOCK TABLES `punto_de_control` WRITE;
/*!40000 ALTER TABLE `punto_de_control` DISABLE KEYS */;
INSERT INTO `punto_de_control` VALUES (1,'Mexico',NULL,50.00,10,2,13,14),(2,'Guatemala',NULL,62.00,8,2,8,10),(6,'Panama',NULL,50.00,10,3,NULL,8),(8,'Bolivia',NULL,20.00,5,3,14,28),(9,'Espana',NULL,75.00,12,3,15,31),(10,'Bodega Elsalvador',NULL,30.00,10,2,8,23),(13,'El salvador 2',NULL,20.00,10,2,12,NULL),(14,'Mexico 2',NULL,10.00,10,2,13,NULL),(15,'Guatemala Villa',NULL,20.00,10,2,12,13),(16,'Mexico DF',NULL,10.00,5,2,11,17),(17,'Nuevo Mexico',NULL,10.00,5,2,11,18),(18,'Whasington DC',NULL,10.00,10,2,11,27),(19,'San Salvador',NULL,5.00,5,3,10,20),(20,'Guadalajara',NULL,7.00,5,3,10,NULL),(21,'Salvador 5',NULL,6.00,10,3,9,22),(22,'Monterrey',NULL,10.00,10,3,9,25),(23,'salvador 07',NULL,5.00,10,10,8,24),(24,'salvador final',NULL,5.00,10,10,8,NULL),(25,'chihuhua',NULL,6.00,10,10,9,26),(26,'toluca',NULL,7.00,8,10,9,NULL),(27,'nueva York',NULL,10.00,15,10,11,NULL),(28,'Bolivia 1',NULL,5.00,10,10,14,29),(29,'Bolivia 2',NULL,5.00,10,10,14,30),(30,'bolivia 3',NULL,10.00,10,10,14,NULL),(31,'Malaga',NULL,8.00,10,10,15,32),(32,'Valencia',NULL,5.00,10,10,15,33),(33,'Madrid',NULL,5.00,10,2,15,NULL);
/*!40000 ALTER TABLE `punto_de_control` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-25 20:39:35
