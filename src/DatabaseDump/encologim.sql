-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.1.73-community - MySQL Community Server (GPL)
-- SE du serveur:                Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour encologim
CREATE DATABASE IF NOT EXISTS `encologim` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `encologim`;


-- Export de la structure de table encologim. profiles
CREATE TABLE IF NOT EXISTS `profiles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITRE` char(50) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Export de données de la table encologim.profiles : 2 rows
DELETE FROM `profiles`;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` (`ID`, `TITRE`) VALUES
	(0, 'Super Administrateur'),
	(1, 'Médecin');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;


-- Export de la structure de table encologim. users
CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATE` int(1) NOT NULL DEFAULT '0',
  `USERNAME` char(50) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` char(50) COLLATE utf8_bin DEFAULT NULL,
  `PROFILE` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Export de données de la table encologim.users : 4 rows
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`ID`, `STATE`, `USERNAME`, `PASSWORD`, `PROFILE`) VALUES
	(1, 1, 'talcorp', '25f9e794323b453885f5181f1b624d0b', 0),
	(2, 0, 'test', '098f6bcd4621d373cade4e832627b4f6', 1),
	(3, 0, 'taleb', '202cb962ac59075b964b07152d234b70', 1),
	(4, 0, 'Charef Amine', 'c9ee6a13c915dbf0ec04f32293f026ae', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Export de la structure de table encologim. usersstate
CREATE TABLE IF NOT EXISTS `usersstate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITRE` char(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Export de données de la table encologim.usersstate : 2 rows
DELETE FROM `usersstate`;
/*!40000 ALTER TABLE `usersstate` DISABLE KEYS */;
INSERT INTO `usersstate` (`ID`, `TITRE`) VALUES
	(0, 'inactive'),
	(1, 'active');
/*!40000 ALTER TABLE `usersstate` ENABLE KEYS */;


-- Export de la structure de vue encologim. userprofile
-- Création d'une table temporaire pour palier aux erreurs de dépendances de VIEW
CREATE TABLE `userprofile` (
	`ID` INT(11) NOT NULL
) ENGINE=MyISAM;


-- Export de la structure de vue encologim. userprofile
-- Suppression de la table temporaire et création finale de la structure d'une vue
DROP TABLE IF EXISTS `userprofile`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `userprofile` AS select s.ID from users s left join profiles p on p.id=s.profile ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
