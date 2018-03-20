CREATE DATABASE `agenda`;
USE agenda;

CREATE TABLE `localidades`
(
	`idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
    `Nombre` varchar(45) NOT NULL,
    PRIMARY KEY (`idLocalidad`)
);

CREATE TABLE `tiposContacto`
(
	`idTipoContacto` int(11) NOT NULL AUTO_INCREMENT,
    `Nombre` varchar(45) NOT NULL,
    PRIMARY KEY (`idTipoContacto`)
);

CREATE TABLE `personas` 
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Email` varchar(50),
  `FechaNacimiento` date,
  `Calle` varchar(45),
  `Altura` int(6),
  `Piso` int(3),
  `Depto` varchar(10),
  `idLocalidad` int(11),
  `idTipoContacto` int(11),
  FOREIGN KEY (`idLocalidad`) REFERENCES `localidades`(`idLocalidad`),
  FOREIGN KEY (`idTipoContacto`) REFERENCES `tiposContacto`(`idTipoContacto`),
  PRIMARY KEY (`idPersona`)
);