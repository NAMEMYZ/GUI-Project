-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2023 at 07:37 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` int(11) NOT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `data_text` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`id`, `column_name`, `keyword`, `data_text`) VALUES
(11, 'Peeraphat', 'Machaithat', 'DIT'),
(12, 'Chawanakorn', 'Sangsavang', 'CAI'),
(14, 'Siraphop', 'Patsopar', 'DIT'),
(15, 'Charinthon', 'Khiaophra-in', 'DIT'),
(16, 'Pimwatchara', 'Namfang', 'CAI'),
(17, 'Sittichok', 'Dorin', 'DIT'),
(18, 'Nutthapan', 'Phobbubpha', 'CAI'),
(19, 'Sorawit', 'Khemthong', 'CAI'),
(20, 'Patcharaphon', 'Samakun', 'CAI'),
(21, 'Joshua', 'Boinier', 'RAE'),
(22, 'Nanthakorn', 'Kaenkaew', 'CAI'),
(23, 'Chawa', 'Sangsang', 'CIA'),
(31, 'Papontee', 'Jaideesakunee', 'DIT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
