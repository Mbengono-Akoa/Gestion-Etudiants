-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Sam 11 Avril 2020 à 12:32
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bddgesnote`
--

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE IF NOT EXISTS `etudiant` (
  `matricule` int(11) NOT NULL,
  `nom` varchar(70) NOT NULL,
  `prenom` varchar(70) NOT NULL,
  `datnaissance` varchar(15) NOT NULL,
  `sexe` varchar(10) NOT NULL,
  `filiere` varchar(20) NOT NULL,
  `niveau` varchar(1) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`matricule`, `nom`, `prenom`, `datnaissance`, `sexe`, `filiere`, `niveau`) VALUES
(8030, 'MELOM', 'PATRICIA', ' 19-11-1996', 'FEM', 'GI', '1'),
(8031, 'ISSA', 'ADOUM', '   11-06-1994', 'MASC', 'GI', '1'),
(8032, 'NANTIGUA', 'RODOLPH', '   11-12-1995', 'MASC', 'GI', '1'),
(8033, 'ACHTA', 'FATIME', '   09-02-1995', 'FEM', 'GI', '1'),
(8034, 'BAOMEKILA', 'SERGE', '   19-08-1994', 'MASC', 'GI', '1'),
(8035, 'IDRISS', 'SENOUSSI', '   17-06-1995', 'MASC', 'GI', '1'),
(8036, 'KALZEBE', 'ZOUTENE', '   20-07-1994', 'MASC', 'GI', '1'),
(8037, 'MENOUDJI', 'PHILOMENE', '   17-11-1995', 'FEM', 'GI', '1'),
(8038, 'DOKOUBOU', 'CYRIL', '   12-12-1996', 'MASC', 'GI', '1'),
(8039, 'MOUSSA', 'IDRISS', '   11-12-1994', 'MASC', 'GI', '1'),
(8040, 'TOGBE', 'RODRIGUE', '   17-09-1993', 'MASC', 'GI', '2'),
(8041, 'DIBRILA', 'YOUNOUS', '11-09-1993', 'MASC', 'GM', '1'),
(8042, 'DIDA', 'ISAAC', '11-09-1990', 'MASC', 'GM', '3');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE IF NOT EXISTS `matiere` (
  `idmat` varchar(30) NOT NULL,
  `appelation` varchar(100) NOT NULL,
  `filiere` varchar(50) NOT NULL,
  `niveau` varchar(50) NOT NULL,
  PRIMARY KEY (`idmat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`idmat`, `appelation`, `filiere`, `niveau`) VALUES
('ALGO1', 'ALGORITHMIQUE 1', 'GI', '1'),
('MF', 'MACHINE FRIGORIFIQUE', 'GM', '2'),
('OL', 'OUTILS LOGICIELS', 'GI', '1'),
('PM', 'PROGRAMMATION MODULAIRE', 'GI', '1'),
('POO', 'JAVA', 'GI', '2'),
('SDD', 'STRUCTURE DE DONNEES', 'GI', '1'),
('THERMO', 'THERMODINAMIQUE', 'GM', '1');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE IF NOT EXISTS `note` (
  `idnote` int(11) NOT NULL AUTO_INCREMENT,
  `idetudiant` int(11) NOT NULL,
  `idmatiere` varchar(20) NOT NULL,
  `controle` double(4,2) DEFAULT NULL,
  `examen` double(4,2) DEFAULT NULL,
  `tp` double(4,2) DEFAULT NULL,
  PRIMARY KEY (`idnote`),
  KEY `fk_etudiant` (`idetudiant`),
  KEY `fk_matiere` (`idmatiere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`idnote`, `idetudiant`, `idmatiere`, `controle`, `examen`, `tp`) VALUES
(1, 8030, 'SDD', 12.00, 8.00, 14.00),
(2, 8030, 'OL', 15.00, 6.00, 12.00),
(3, 8030, 'PM', 11.00, 8.00, 11.00),
(4, 8030, 'ALGO1', 14.00, 17.00, 15.00),
(5, 8031, 'OL', 17.00, 19.00, 15.00),
(6, 8031, 'SDD', 11.00, 12.00, 14.00),
(7, 8031, 'PM', 6.00, 9.00, 12.50),
(8, 8031, 'ALGO1', 14.00, 10.75, 13.00),
(9, 8032, 'SDD', 12.00, 7.00, 17.75),
(10, 8032, 'OL', 12.00, 13.50, 11.00),
(11, 8032, 'PM', 10.50, 12.00, 13.00),
(12, 8032, 'ALGO1', 11.00, 8.00, 11.50),
(13, 8033, 'SDD', 15.00, 12.00, 16.00),
(14, 8033, 'OL', 12.00, 5.00, 14.00),
(15, 8033, 'PM', 11.00, 10.00, 12.00),
(16, 8033, 'ALGO1', 6.00, 8.00, 13.00),
(17, 8034, 'SDD', 16.00, 14.00, 18.00),
(18, 8034, 'OL', 8.00, 11.00, 12.00),
(19, 8034, 'PM', 5.00, 12.00, 14.00),
(20, 8034, 'ALGO1', 12.00, 9.00, 14.50);

-- --------------------------------------------------------

--
-- Structure de la table `secret1`
--

CREATE TABLE IF NOT EXISTS `secret1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `secret1`
--

INSERT INTO `secret1` (`id`, `login`, `password`) VALUES
(1, 'chris', 'chris'),
(2, 'target', 'target');

-- --------------------------------------------------------

--
-- Structure de la table `secret2`
--

CREATE TABLE IF NOT EXISTS `secret2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `secret2`
--

INSERT INTO `secret2` (`id`, `login`, `password`) VALUES
(1, 'christian', 'christian'),
(2, 'trajet', 'trajet');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_etd_note`
--
CREATE TABLE IF NOT EXISTS `v_etd_note` (
`idmat` varchar(30)
,`nom` varchar(70)
,`prenom` varchar(70)
,`appelation` varchar(100)
,`moyenne` double(23,6)
);
-- --------------------------------------------------------

--
-- Structure de la vue `v_etd_note`
--
DROP TABLE IF EXISTS `v_etd_note`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_etd_note` AS select `matiere`.`idmat` AS `idmat`,`etudiant`.`nom` AS `nom`,`etudiant`.`prenom` AS `prenom`,`matiere`.`appelation` AS `appelation`,(((`note`.`controle` + `note`.`examen`) + `note`.`tp`) / 3) AS `moyenne` from ((`etudiant` join `note` on((`etudiant`.`matricule` = `note`.`idetudiant`))) join `matiere` on((`note`.`idmatiere` = `matiere`.`idmat`)));

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_etudiant` FOREIGN KEY (`idetudiant`) REFERENCES `etudiant` (`matricule`),
  ADD CONSTRAINT `fk_matiere` FOREIGN KEY (`idmatiere`) REFERENCES `matiere` (`idmat`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
