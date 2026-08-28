SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `granalla_ChemsHub` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `granalla_ChemsHub`;

CREATE TABLE IF NOT EXISTS `Directivas` (
  `IdDirectiva` int(11) NOT NULL AUTO_INCREMENT,
  `HoraEntrada` time NOT NULL,
  `HoraSalida` time NOT NULL,
  PRIMARY KEY (`IdDirectiva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `equipos` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `mac_equipo` varchar(50) NOT NULL,
  `fechaBan` date DEFAULT NULL,
  `horaBan` time DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`idEquipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `LoginEmpleados` (
  `Rut` varchar(12) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellido` varchar(100) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contrasena` varchar(100) NOT NULL,
  `Rol` varchar(2) NOT NULL,
  PRIMARY KEY (`Rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `marcaciones` (
  `idMarcacion` int(11) NOT NULL AUTO_INCREMENT,
  `Rut` varchar(12) NOT NULL,
  `tipo` enum('entrada','salida') NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`idMarcacion`),
  KEY `Rut` (`Rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


ALTER TABLE `marcaciones`
  ADD CONSTRAINT `marcaciones_ibfk_1` FOREIGN KEY (`Rut`) REFERENCES `LoginEmpleados` (`Rut`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
