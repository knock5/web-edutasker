-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2024 at 06:32 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_web_edutasker`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_complaint_fu`
--

CREATE TABLE `tb_complaint_fu` (
  `follow_up_id` int(11) NOT NULL,
  `follow_up_date` date NOT NULL,
  `follow_up_notes` varchar(150) NOT NULL,
  `follow_up_score` varchar(100) DEFAULT NULL,
  `complaint_id` int(11) DEFAULT NULL,
  `officer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_people`
--

CREATE TABLE `tb_people` (
  `people_id` int(11) NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `position` varchar(50) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_people`
--

INSERT INTO `tb_people` (`people_id`, `address`, `email`, `name`, `phone`, `position`, `profile_picture`) VALUES
(1, 'Jakarta Selatan', 'admin@star.com', 'Admin', '6282190116553', 'admin', 'link-profile-picture.com'),
(2, 'Jakarta Pusat', 'anakin@star.com', 'Anakin Skywalker', '6282190116234', 'dosen', 'link-profile-picture.com'),
(3, 'Jakarta Timur', 'han@star.com', 'Han Solo', '6282190116101', 'mahasiswa', 'link-profile-picture.com');

-- --------------------------------------------------------

--
-- Table structure for table `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `privilege_id` int(11) NOT NULL,
  `privilege_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_privilege`
--

INSERT INTO `tb_privilege` (`privilege_id`, `privilege_name`) VALUES
(1, 'super_admin'),
(2, 'editor_officer'),
(3, 'editor_user');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role`
--

CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_role`
--

INSERT INTO `tb_role` (`role_id`, `role_name`) VALUES
(1, 'admin'),
(2, 'dosen'),
(3, 'mahasiswa');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role_privilege`
--

CREATE TABLE `tb_role_privilege` (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_role_privilege`
--

INSERT INTO `tb_role_privilege` (`role_id`, `privilege_id`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tasks`
--

CREATE TABLE `tb_tasks` (
  `complaint_id` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `complaint_body` varchar(225) NOT NULL,
  `complaint_date` date NOT NULL,
  `complaint_title` varchar(35) NOT NULL,
  `people_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_task_status`
--

CREATE TABLE `tb_task_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_tracking_history`
--

CREATE TABLE `tb_tracking_history` (
  `history_id` int(11) NOT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `complaint_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `is_account_non_locked` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `is_account_non_locked`, `is_enabled`, `password`, `username`) VALUES
(1, b'1', b'1', '$2a$10$gOnY2QvmRq42VSk.smSfk.GBG1haw3XDUmGoG7fDPXm1YpK4/zT2u', 'admin'),
(2, b'1', b'1', '$2a$10$pthfj6a3K586Nfczgezsd.QsW970xRMizpNhQ0i4hkb6oO4MOq72u', 'anakin'),
(3, b'1', b'1', '$2a$10$7U50JnN4D0ar07Mw4.Y07up48eTw4XyKiyUxu4f/Utz5AcUrqRA0e', 'hansolo');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user_role`
--

CREATE TABLE `tb_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user_role`
--

INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_complaint_fu`
--
ALTER TABLE `tb_complaint_fu`
  ADD PRIMARY KEY (`follow_up_id`),
  ADD KEY `FKq21j27a4g2b8g2bw2q4let182` (`complaint_id`),
  ADD KEY `FKliot1hymuhv8wv2bwab3go60k` (`officer_id`);

--
-- Indexes for table `tb_people`
--
ALTER TABLE `tb_people`
  ADD PRIMARY KEY (`people_id`),
  ADD UNIQUE KEY `UK_89wbtr2rb5dtu6nouqrqgsorm` (`email`),
  ADD UNIQUE KEY `UK_3mvnrd05244x2ghn45el2st1q` (`phone`);

--
-- Indexes for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`privilege_id`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_c9lijtmr0x68iu1vxftbu2u33` (`role_name`);

--
-- Indexes for table `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD KEY `FK6gvpbopw3c17inmv17usj8kes` (`privilege_id`),
  ADD KEY `FKsncdml4xq9xf0opvvrxcycq7y` (`role_id`);

--
-- Indexes for table `tb_tasks`
--
ALTER TABLE `tb_tasks`
  ADD PRIMARY KEY (`complaint_id`),
  ADD KEY `FKrd7gxik3cnralnkoyvu8fmboh` (`people_id`),
  ADD KEY `FK6id6e7bmc7j9jvxddvfcu8tn6` (`status_id`);

--
-- Indexes for table `tb_task_status`
--
ALTER TABLE `tb_task_status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `tb_tracking_history`
--
ALTER TABLE `tb_tracking_history`
  ADD PRIMARY KEY (`history_id`),
  ADD KEY `FK5f78oof6mfs68ts3c84l06g4g` (`complaint_id`),
  ADD KEY `FKpwhrlys79q6ajxsd6w1e9oeiw` (`status_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`);

--
-- Indexes for table `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  ADD KEY `FK7vn3h53d0tqdimm8cp45gc0kl` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_complaint_fu`
--
ALTER TABLE `tb_complaint_fu`
  MODIFY `follow_up_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_people`
--
ALTER TABLE `tb_people`
  MODIFY `people_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `privilege_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_tasks`
--
ALTER TABLE `tb_tasks`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_task_status`
--
ALTER TABLE `tb_task_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_tracking_history`
--
ALTER TABLE `tb_tracking_history`
  MODIFY `history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_complaint_fu`
--
ALTER TABLE `tb_complaint_fu`
  ADD CONSTRAINT `FKliot1hymuhv8wv2bwab3go60k` FOREIGN KEY (`officer_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKq21j27a4g2b8g2bw2q4let182` FOREIGN KEY (`complaint_id`) REFERENCES `tb_tasks` (`complaint_id`);

--
-- Constraints for table `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD CONSTRAINT `FK6gvpbopw3c17inmv17usj8kes` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`privilege_id`),
  ADD CONSTRAINT `FKsncdml4xq9xf0opvvrxcycq7y` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`);

--
-- Constraints for table `tb_tasks`
--
ALTER TABLE `tb_tasks`
  ADD CONSTRAINT `FK6id6e7bmc7j9jvxddvfcu8tn6` FOREIGN KEY (`status_id`) REFERENCES `tb_task_status` (`status_id`),
  ADD CONSTRAINT `FKrd7gxik3cnralnkoyvu8fmboh` FOREIGN KEY (`people_id`) REFERENCES `tb_people` (`people_id`);

--
-- Constraints for table `tb_tracking_history`
--
ALTER TABLE `tb_tracking_history`
  ADD CONSTRAINT `FK5f78oof6mfs68ts3c84l06g4g` FOREIGN KEY (`complaint_id`) REFERENCES `tb_tasks` (`complaint_id`),
  ADD CONSTRAINT `FKpwhrlys79q6ajxsd6w1e9oeiw` FOREIGN KEY (`status_id`) REFERENCES `tb_task_status` (`status_id`);

--
-- Constraints for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `FK4aqxqa9nstsmlb0b5k7m9gce7` FOREIGN KEY (`id`) REFERENCES `tb_people` (`people_id`);

--
-- Constraints for table `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
