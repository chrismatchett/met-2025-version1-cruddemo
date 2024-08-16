CREATE DATABASE  IF NOT EXISTS `tvlicencefines`;
USE `tvlicencefines`;

DROP TABLE IF EXISTS `fine`;


CREATE TABLE `fine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `post_code` varchar(10) DEFAULT NULL,
  `reference` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `fine` (`first_name`,`last_name`,`email`,`reference`,`amount`)
VALUES 
	('Jane','Doe','jane@example.com', 'TVLF-X9SRIV', 150.25),
	('Sean','Mulvenna','sean@example.com', 'TVLF-HDJBRY', 150.60),
	('Jack','Jones','jack@example.com', 'TVLF-BRIM87', 305.20),
	('John','Smith','john@example.com', 'TVLF-N312FR', 100.00),
	('Mary','Lamb','mary@example.com', 'TVLF-0V78PB', 70.55);