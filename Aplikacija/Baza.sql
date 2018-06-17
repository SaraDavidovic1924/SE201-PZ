-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2018 at 08:11 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs102`
--

-- --------------------------------------------------------

--
-- Table structure for table `dnevni_unos`
--

CREATE TABLE `dnevni_unos` (
  `ID_DNEVNI_UNOS` int(11) NOT NULL,
  `KORISNIK_ID` int(11) DEFAULT NULL,
  `INSULIN` text NOT NULL,
  `DATUM_DU` datetime NOT NULL,
  `KOLICINA_JEDINICA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dnevni_unos`
--

INSERT INTO `dnevni_unos` (`ID_DNEVNI_UNOS`, `KORISNIK_ID`, `INSULIN`, `DATUM_DU`, `KOLICINA_JEDINICA`) VALUES
(1, 13, 'brzo delujuci', '2017-12-20 14:13:00', 44);

-- --------------------------------------------------------

--
-- Table structure for table `istorija_merenja`
--

CREATE TABLE `istorija_merenja` (
  `ISTORIJA_MERENJA_ID` int(11) NOT NULL,
  `KORISNIK_ID` int(11) DEFAULT NULL,
  `DATUM_I_VREME_IM` datetime NOT NULL,
  `VREDNOST` decimal(3,0) NOT NULL,
  `TIP_INSULINA` text NOT NULL,
  `TIP_UNOSA` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `istorija_merenja`
--

INSERT INTO `istorija_merenja` (`ISTORIJA_MERENJA_ID`, `KORISNIK_ID`, `DATUM_I_VREME_IM`, `VREDNOST`, `TIP_INSULINA`, `TIP_UNOSA`) VALUES
(1, 2, '2017-12-27 19:25:00', '55', '', 'Glikemija'),
(2, 2, '2017-12-27 19:29:00', '55', '', 'Glikemija'),
(3, 2, '2017-12-27 19:12:00', '55', 'brzo delujuci', 'Insulin'),
(4, 2, '2017-12-27 19:59:00', '55', 'sporo delujuci', 'Insulin'),
(5, 2, '2016-12-27 19:59:00', '55', 'sporo delujuci', 'Insulin'),
(6, 2, '2017-10-27 19:59:00', '55', 'sporo delujuci', 'Insulin'),
(7, 2, '2017-12-27 19:53:00', '20', '', 'Glikemija'),
(8, 13, '2018-01-10 16:54:00', '13', '', 'Glikemija'),
(9, 13, '2018-01-10 16:54:00', '13', '', 'Glikemija'),
(10, 13, '2018-01-10 16:54:00', '22', 'sporo delujuci', 'Insulin'),
(11, 13, '2018-01-10 16:54:00', '13', '', 'Glikemija'),
(12, 13, '2018-01-10 16:54:00', '22', 'sporo delujuci', 'Insulin'),
(13, 13, '2017-12-09 16:56:00', '55', '', ''),
(14, 13, '2018-01-10 16:59:00', '55', '', 'Glikemija'),
(15, 13, '2018-01-10 16:59:00', '55', '', 'Glikemija'),
(16, 13, '2018-01-10 17:56:00', '2', '', 'Glikemija');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `KORISNIK_ID` int(11) NOT NULL,
  `JMBG` int(11) DEFAULT NULL,
  `IME_KORISNIK` char(250) NOT NULL,
  `PREZIME_KORISNIK` char(250) NOT NULL,
  `LBO` int(11) DEFAULT NULL,
  `TELEFON_KORISNIK` int(11) DEFAULT NULL,
  `MOB_KORISNIK` int(11) DEFAULT NULL,
  `DATUM_DU` date DEFAULT NULL,
  `VISINA_KORISNIK` int(11) DEFAULT NULL,
  `TEZINA_KORISNIK` int(11) DEFAULT NULL,
  `POL_KORISNIK` char(250) DEFAULT NULL,
  `NAPOMENA` char(250) DEFAULT NULL,
  `MAIL` char(30) NOT NULL,
  `LOZINKA` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`KORISNIK_ID`, `JMBG`, `IME_KORISNIK`, `PREZIME_KORISNIK`, `LBO`, `TELEFON_KORISNIK`, `MOB_KORISNIK`, `DATUM_DU`, `VISINA_KORISNIK`, `TEZINA_KORISNIK`, `POL_KORISNIK`, `NAPOMENA`, `MAIL`, `LOZINKA`) VALUES
(0, 56789, 'Sara2', 'Smaracica', 4567, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'sara2@smaracica.rs', 'jasamsmaracicaopet'),
(1, 56789, 'Sara', 'Smaracica', 4567, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'sara@smaracica.rs', 'jasamsmaracica'),
(2, 1, 'Sarita', 'Davidovic', 789654123, 9876, 112666630, '2017-11-11', 169, 59, 'zenski', 'sara je car', 'sara@sara.com', 'sara'),
(3, NULL, 's', 's', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 's', 's'),
(4, NULL, 's', 's', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 's', 's'),
(9, NULL, 's', 's', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 's', 's'),
(10, NULL, 'sara', 'david', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 's@s.com', 'sara1234'),
(11, NULL, 'ivan', 'ivic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i@i.com', 'ivan1234'),
(12, NULL, 'pera', 'peric', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'pera@com', 'pera'),
(13, 123456789, 'mika', 'mikic', 123456, 60456123, 551564, '2014-02-10', 169, 55, 'muski', 'fdaijfdklsaj;', 'mika', 'mika'),
(14, NULL, 'sara', 'david', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 's@s.com', 'ssss');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dnevni_unos`
--
ALTER TABLE `dnevni_unos`
  ADD PRIMARY KEY (`ID_DNEVNI_UNOS`),
  ADD KEY `FK_DNEVNI_U_RELATIONS_KORISNIK` (`KORISNIK_ID`);

--
-- Indexes for table `istorija_merenja`
--
ALTER TABLE `istorija_merenja`
  ADD PRIMARY KEY (`ISTORIJA_MERENJA_ID`),
  ADD KEY `RELATIONSHIP_3_FK` (`KORISNIK_ID`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`KORISNIK_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dnevni_unos`
--
ALTER TABLE `dnevni_unos`
  ADD CONSTRAINT `FK_DNEVNI_U_RELATIONS_KORISNIK` FOREIGN KEY (`KORISNIK_ID`) REFERENCES `korisnik` (`KORISNIK_ID`);

--
-- Constraints for table `istorija_merenja`
--
ALTER TABLE `istorija_merenja`
  ADD CONSTRAINT `FK_ISTORIJA_RELATIONS_KORISNIK` FOREIGN KEY (`KORISNIK_ID`) REFERENCES `korisnik` (`KORISNIK_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
