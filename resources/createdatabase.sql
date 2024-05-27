-- Cherni Ahlem


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
    `firstName` varchar(100) DEFAULT NULL,
    `lastName` varchar(100) DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    `id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`),
    UNIQUE KEY `USER_UN` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `users` (`firstName`, `lastName`, `email`, `password`) VALUES
('Cherni', 'Ahlem', 'Ahlem.cherni112@gmail.com', 'ahlem1234'),
('Walid', 'Barrada', 'Walid@gmail.com', 'walid1234');