-- Ceated By: Cherni Ahlem


--
-- Creation de la base de données
--
DROP DATABASE IF EXISTS iteam_db;
CREATE DATABASE iteam_db;


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