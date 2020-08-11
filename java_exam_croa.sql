-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-08-2020 a las 12:30:03
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `java_exam_croa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities_users`
--

CREATE TABLE `authorities_users` (
  `usuario_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `authorities_users`
--

INSERT INTO `authorities_users` (`usuario_id`, `authority_id`) VALUES
(22, 2),
(25, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authority`
--

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `authority`
--

INSERT INTO `authority` (`id`, `authority`) VALUES
(1, 'borrower'),
(2, 'lender');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(29),
(29);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `loan`
--

CREATE TABLE `loan` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `id_person` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `loan`
--

INSERT INTO `loan` (`id`, `amount`, `description`, `reason`, `id_person`) VALUES
(27, 3000000, 'I love my girlfriend and I would like to surprise her and take her on a great trip.', 'Travel with mi girfriend', 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `id_person_type` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`id`, `first_name`, `last_name`, `id_person_type`, `id_user`) VALUES
(23, 'Josep', 'Anniston', 1, 22),
(26, 'Jean', 'Boausejour', 2, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person_type`
--

CREATE TABLE `person_type` (
  `id` bigint(20) NOT NULL,
  `short_desc_label` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `person_type`
--

INSERT INTO `person_type` (`id`, `short_desc_label`, `type`) VALUES
(1, 'lender', 'Lender'),
(2, 'borrower', 'Borrower');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `enabled`, `password`, `username`) VALUES
(22, b'1', '$2a$04$9d/TWPYB0LrONobEtoxSL.waw.Jp4wrkXi3hvRiS/Q9zlOrwV8lri', 'lender@lender.cl'),
(25, b'1', '$2a$04$ht6mCQz8DcSYQxGk01K2w.PvbulyY6yCQv/V9DL43kSMIwIl9Z32S', 'borrower@borrower.cl');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `wallet`
--

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `id_loan` bigint(20) DEFAULT NULL,
  `id_person` bigint(20) DEFAULT NULL,
  `influx` int(11) DEFAULT NULL,
  `output` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `wallet`
--

INSERT INTO `wallet` (`id`, `id_loan`, `id_person`, `influx`, `output`) VALUES
(24, NULL, 23, 50000000, NULL),
(28, 27, 23, NULL, 10000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities_users`
--
ALTER TABLE `authorities_users`
  ADD PRIMARY KEY (`usuario_id`,`authority_id`),
  ADD KEY `FK1hk335nyb5icwqy64y2mhov2v` (`authority_id`);

--
-- Indices de la tabla `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqce3ugki56pt13qw0oew4xc6d` (`id_person`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkv7t7bgtyorop603bmmif7cln` (`id_person_type`),
  ADD KEY `FKas2niaqx78in8m06j3s6djmou` (`id_user`);

--
-- Indices de la tabla `person_type`
--
ALTER TABLE `person_type`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `wallet`
--
ALTER TABLE `wallet`
  ADD KEY `FK2n67e5ss0b50nyclkt7h2koke` (`id_loan`),
  ADD KEY `FK1pcsg5l78u895fwovovwv6q2` (`id_person`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities_users`
--
ALTER TABLE `authorities_users`
  ADD CONSTRAINT `FK1hk335nyb5icwqy64y2mhov2v` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
  ADD CONSTRAINT `FKe10yx3h6h4yikcfimegqs8y71` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FKqce3ugki56pt13qw0oew4xc6d` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`);

--
-- Filtros para la tabla `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FKas2niaqx78in8m06j3s6djmou` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKkv7t7bgtyorop603bmmif7cln` FOREIGN KEY (`id_person_type`) REFERENCES `person_type` (`id`);

--
-- Filtros para la tabla `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `FK1pcsg5l78u895fwovovwv6q2` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK2n67e5ss0b50nyclkt7h2koke` FOREIGN KEY (`id_loan`) REFERENCES `loan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
