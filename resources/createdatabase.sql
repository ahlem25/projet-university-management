-- Ceated By: Cherni Ahlem


--
-- Creation de la base de données
--
DROP DATABASE IF EXISTS iteam_db;
CREATE DATABASE iteam_db;

use `iteam_db`;
--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` varchar(100) DEFAULT NULL,
    `lastName` varchar(100) DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `USER_UN` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `users` (`firstName`, `lastName`, `email`, `password`) VALUES
('Cherni', 'Ahlem', 'Ahlem.cherni112@gmail.com', 'ahlem1234'),
('Walid', 'Barrada', 'Walid@gmail.com', 'walid1234');

--
-- Structure de la table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
    `id` int NOT NULL AUTO_INCREMENT,
    `firstName` varchar(100) DEFAULT NULL,
    `lastName` varchar(100) DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `cin` varchar(100) DEFAULT NULL,
    `level` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `USER_UN` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Déchargement des données de la table `students`
--

INSERT INTO `students` (`firstName`, `lastName`, `email`, `cin`, `level`) VALUES
('Cherni', 'Ahlem', 'Ahlem.cherni112@gmail.com', '09927888', '1ere GL'),
('Walid', 'Barrada', 'Walid@gmail.com', '01346509', '1ere GL');


--
-- Structure de la table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `comment` varchar(100) DEFAULT NULL,
    `of_year` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `USER_UN` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `classes`
--
INSERT INTO `classes` (`name`, `comment`, `of_year`) VALUES
('1ere GL', '1ere mastère en génie logiciel', '2024'),
('2eme GL', '2eme mastère en génie logiciel', '2024');

--
-- Structure de la table `payements`
--

DROP TABLE IF EXISTS `payements`;

CREATE TABLE IF NOT EXISTS `payements` (
    `id` int NOT NULL AUTO_INCREMENT,
    `amount` varchar(100) DEFAULT NULL,
    `date` date DEFAULT NULL,
    `comment` varchar(255) DEFAULT NULL,
    `student_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `student_FK` (`student_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Structure de la table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;

CREATE TABLE IF NOT EXISTS `subscriptions` (
    `id` int NOT NULL AUTO_INCREMENT,
    `year` varchar(100) DEFAULT NULL,
    `student_id` int DEFAULT NULL,
    `class_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `student_FK` (`student_id`),
    KEY `class_FK` (`class_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

