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
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruta` (
  `id_ruta` int(11) NOT NULL AUTO_INCREMENT,
  `inicio` varchar(25) NOT NULL,
  `destino` varchar(25) NOT NULL,
  `estado_ruta` tinyint(1) DEFAULT NULL,
  `cuota_destino` decimal(10,2) NOT NULL DEFAULT '0.00',
  `ingresos_ruta` decimal(20,2) DEFAULT '0.00',
  `costo_ruta` decimal(20,2) DEFAULT '0.00',
  `ganancia_ruta` decimal(20,2) DEFAULT '0.00',
  `paquetes_entregados` int(11) DEFAULT '0',
  `paquetes_endestino` int(11) DEFAULT '0',
  `pc_inicio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ruta`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (8,'Guatemala','El Salvador',1,50.00,1257.25,7578.00,0.00,10,3,2),(9,'Guatemala','México',1,75.00,0.00,30.00,0.00,0,2,21),(10,'El Salvador','México',1,80.00,0.00,0.00,0.00,0,0,19),(11,'Guatemala','USA',1,120.00,0.00,150.00,0.00,0,1,16),(12,'Guatemala','El Salvador',1,125.00,0.00,0.00,0.00,0,0,15),(13,'Guatemala','México',1,100.00,265.50,30.00,0.00,2,0,1),(14,'Panama','Bolivia',1,100.00,NULL,NULL,NULL,NULL,1,6),(15,'Guatemala','España',1,125.00,NULL,NULL,NULL,NULL,1,9);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-25 20:39:37
