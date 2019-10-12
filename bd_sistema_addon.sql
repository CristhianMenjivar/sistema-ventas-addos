-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 12-10-2019 a las 20:27:36
-- Versión del servidor: 5.7.24
-- Versión de PHP: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_sistema_addon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

DROP TABLE IF EXISTS `boleta`;
CREATE TABLE IF NOT EXISTS `boleta` (
  `idboleta` int(11) NOT NULL AUTO_INCREMENT,
  `boleta` varchar(25) CHARACTER SET latin1 NOT NULL,
  `tipoboleta` varchar(25) CHARACTER SET latin1 NOT NULL,
  `estadoboleta` varchar(25) CHARACTER SET latin1 NOT NULL,
  `total` double(10,2) NOT NULL,
  `formapago` varchar(25) CHARACTER SET latin1 NOT NULL,
  `numdocumento` int(25) NOT NULL,
  `banco` varchar(25) CHARACTER SET latin1 NOT NULL,
  `fecha` date NOT NULL,
  `monto` double(10,2) NOT NULL,
  PRIMARY KEY (`idboleta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`idboleta`, `boleta`, `tipoboleta`, `estadoboleta`, `total`, `formapago`, `numdocumento`, `banco`, `fecha`, `monto`) VALUES
(1, '122344', 'Manual', 'Cancelado', 20000.00, 'Efectivo', 1, 'Banco AztecaÃ³', '2019-09-27', 30000.00),
(2, '38277', 'Automatica', 'Cancelado', 200530.00, 'Transferencia', 2, 'Banco Azteca', '2019-09-27', 20000.00),
(3, 'a', 'Automatica', 'Pendiente', 6000.00, 'Debito', 22, 'agricola', '2019-10-12', 30000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_categoria` varchar(50) NOT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`),
  UNIQUE KEY `Nom_categoria_UNIQUE` (`Nom_categoria`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idcategoria`, `Nom_categoria`, `descripcion`) VALUES
(3, 'Lacteos', 'leche de vaca'),
(2, 'Carnicos', 'categorias carnicos'),
(4, 'Frutas', 'descripcion de frutas'),
(6, 'Electronicos', 'TV, Phones , etc'),
(7, 'BEBIDAS', 'bebidas de frutas, bebidas con pikete'),
(8, 'muebles', 'muebles grandes y pequeÃ±os');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 NOT NULL,
  `apellido` varchar(50) CHARACTER SET latin1 NOT NULL,
  `direccion` varchar(75) CHARACTER SET latin1 DEFAULT NULL,
  `telefono` varchar(8) CHARACTER SET latin1 DEFAULT NULL,
  `numfactura` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `tipofactura` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `estadofactura` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `formapago` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `numdocumento` int(25) DEFAULT NULL,
  `banco` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `monto` double(10,3) DEFAULT NULL,
  `rut` varchar(15) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idcliente`, `nombre`, `apellido`, `direccion`, `telefono`, `numfactura`, `tipofactura`, `estadofactura`, `total`, `formapago`, `numdocumento`, `banco`, `fecha`, `monto`, `rut`) VALUES
(1, 'CRISTHIAN', 'MENJIVAR', 'LAGUNA SECA', '70804980', '232342424', 'GuÃ­a', 'Cancelado', 20000.00, 'Efectivo', 1, 'Banco Azteca', '2019-09-20', 20000.000, '2335463'),
(2, 'cristhian 2', 'Menjivar 2', 'laguna seca', '80808080', '345353533', 'Electronica', 'Pendiente', 39999.09, 'Debito', 23, 'Banco Azteca', '2019-09-23', 200000.000, '3724689'),
(3, 'cristhian 3', 'Menjivar', 'laguna seca', '70804980', '23889749', 'Electronica', 'Pendiente', 230000.00, 'Credito', 34, 'Banco Azteca', '2019-09-24', 120000.000, '8739879'),
(4, 'cristhian 4', 'Menjivar', 'laguna seca', '70804980', '7829836', 'Electronica', 'Pendiente', 500000.00, 'Cheque', 34, 'Banco Azteca', '2019-09-25', 400000.000, '1309843'),
(5, 'cristhian 5', 'Menjivar', 'laguna seca', '70804980', '2389789', 'Electronica', 'Pendiente', 700000.00, 'Tranferencia', 55, 'Banco Azteca', '2019-09-27', 6000000.000, '1307925'),
(6, 'cristhian 6', 'Menjivar', 'laguna seca', '70804980', '2040289', 'Electronica', 'Cancelada', 8000000.00, 'Debito', 45, 'Banco Azteca', '2019-09-29', 7000000.000, '4370925'),
(7, 'cristhian 7', 'Menjivar', 'laguna seca', '70804980', '394839', 'Boleta', 'Pendiente', 328939.00, 'Tranferencia', 10, 'Banco Azteca', '2019-09-24', 32234.000, '3989470');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

DROP TABLE IF EXISTS `compras`;
CREATE TABLE IF NOT EXISTS `compras` (
  `idcompra` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  PRIMARY KEY (`idcompra`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`idcompra`, `fecha`, `monto`) VALUES
(1, '2019-03-15', 700),
(2, '2019-03-15', 11650),
(3, '2019-03-15', 290),
(4, '2019-03-15', 290),
(5, '2019-03-15', 300),
(6, '2019-03-15', 2800),
(7, '2019-03-15', 705),
(8, '2019-03-15', 33500),
(9, '2019-03-15', 2325),
(10, '2019-03-22', 1500),
(11, '2019-03-22', 20),
(12, '2019-03-22', 100),
(13, '2019-03-22', 100),
(14, '2019-03-23', 1322.5),
(15, '2019-03-24', 107.5),
(16, '2019-03-26', 9445),
(17, '2019-04-17', 258),
(18, '2019-09-11', 1075),
(19, '2019-09-17', 64.5),
(20, '2019-10-05', 125),
(21, '2019-10-05', 107.5),
(22, '2019-10-05', 293),
(23, '2019-10-05', 43),
(24, '2019-10-05', 43),
(25, '2019-10-12', 107.5),
(26, '2019-10-12', 100),
(27, '2019-10-12', 443),
(28, '2019-10-12', 443),
(29, '2019-10-12', 143),
(30, '2019-10-12', 43),
(31, '2019-10-12', 43),
(32, '2019-10-12', 43),
(33, '2019-10-12', 43),
(34, '2019-10-12', 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `creditos`
--

DROP TABLE IF EXISTS `creditos`;
CREATE TABLE IF NOT EXISTS `creditos` (
  `idcredito` int(11) NOT NULL AUTO_INCREMENT,
  `idventa` int(11) NOT NULL,
  `monto` float NOT NULL,
  `fecha` date NOT NULL,
  `idcliente` int(11) NOT NULL,
  PRIMARY KEY (`idcredito`),
  KEY `fkcredito_venta_idx` (`idventa`),
  KEY `idcliente` (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `creditos`
--

INSERT INTO `creditos` (`idcredito`, `idventa`, `monto`, `fecha`, `idcliente`) VALUES
(1, 162, 222, '2019-10-12', 1),
(2, 162, 222, '2019-10-12', 1),
(3, 162, 33, '2019-10-12', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
CREATE TABLE IF NOT EXISTS `cuentas` (
  `idcuenta` int(11) NOT NULL AUTO_INCREMENT,
  `o` varchar(5) DEFAULT NULL,
  `t` varchar(5) NOT NULL,
  `fecha` date NOT NULL,
  `emisor` varchar(25) NOT NULL,
  `descripcion` varchar(25) DEFAULT NULL,
  `ctacont` varchar(25) DEFAULT NULL,
  `centro` varchar(25) DEFAULT NULL,
  `usuario` varchar(25) NOT NULL,
  `pago` double(10,3) NOT NULL,
  `monto` double(10,3) NOT NULL,
  PRIMARY KEY (`idcuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`idcuenta`, `o`, `t`, `fecha`, `emisor`, `descripcion`, `ctacont`, `centro`, `usuario`, `pago`, `monto`) VALUES
(1, '?', 'FE', '2019-09-29', 'Jose Manuel', 'Planchas', '???', '???', 'administrador', 10500.000, 20000.000),
(3, 'ewt', 'FE', '2019-09-30', 'Industria muebles', 'Muebles', 'esrwer', 'eww', 'administrador', 10000.000, 20000.000),
(4, 'cris', 'as', '2019-10-12', 'cristhian menjivar', 'cris descripcion', 'asas', 'asas', 'asas', 6162.000, 26262.000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
CREATE TABLE IF NOT EXISTS `detalle_compra` (
  `iddetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idproducto` int(11) NOT NULL,
  `idcompra` int(11) NOT NULL,
  `cantidad` double NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`iddetalle`),
  KEY `idcompra` (`idcompra`),
  KEY `idproducto` (`idproducto`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_compra`
--

INSERT INTO `detalle_compra` (`iddetalle`, `idproducto`, `idcompra`, `cantidad`, `importe`) VALUES
(1, 20, 1, 20, 700),
(2, 20, 2, 10, 350),
(3, 55, 2, 20, 800),
(4, 100, 2, 30, 10500),
(5, 20, 3, 2, 40),
(6, 200, 3, 5, 250),
(7, 20, 4, 2, 40),
(8, 200, 4, 5, 250),
(9, 20, 5, 5, 100),
(10, 200, 5, 4, 200),
(11, 2000, 6, 1, 300),
(12, 500, 6, 5, 2500),
(13, 301, 7, 5, 5),
(14, 100, 7, 3, 600),
(15, 200, 7, 2, 100),
(16, 20, 8, 50, 1000),
(17, 55, 8, 100, 2500),
(18, 100, 8, 150, 30000),
(19, 20, 9, 10, 200),
(20, 55, 9, 5, 125),
(21, 100, 9, 10, 2000),
(22, 2000, 10, 5, 1500),
(23, 20, 11, 1, 20),
(24, 20, 12, 5, 100),
(25, 20, 13, 5, 100),
(26, 20, 14, 15, 322.5),
(27, 500, 14, 2, 1000),
(28, 20, 15, 5, 107.5),
(29, 20, 16, 10, 215),
(30, 20, 16, 20, 430),
(31, 55, 16, 30, 750),
(32, 100, 16, 20, 4000),
(33, 150, 16, 50, 1000),
(34, 300, 16, 100, 3050),
(35, 20, 17, 12, 258),
(36, 20, 18, 50, 1075),
(37, 20, 19, 3, 64.5),
(38, 55, 20, 1, 25),
(39, 150, 20, 5, 100),
(40, 20, 21, 5, 107.5),
(41, 20, 22, 2, 43),
(42, 200, 22, 5, 250),
(43, 20, 23, 2, 43),
(44, 20, 24, 2, 43),
(45, 20, 25, 1, 21.5),
(46, 20, 25, 1, 21.5),
(47, 20, 25, 3, 64.5),
(48, 200, 26, 2, 100),
(49, 20, 27, 2, 43),
(50, 100, 27, 2, 400),
(51, 20, 28, 2, 43),
(52, 100, 28, 2, 400),
(53, 20, 29, 2, 43),
(54, 200, 29, 2, 100),
(55, 20, 30, 2, 43),
(56, 20, 31, 2, 43),
(57, 20, 32, 2, 43),
(58, 20, 33, 2, 43),
(59, 20, 34, 2, 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_credito`
--

DROP TABLE IF EXISTS `detalle_credito`;
CREATE TABLE IF NOT EXISTS `detalle_credito` (
  `iddetalle_credito` int(11) NOT NULL AUTO_INCREMENT,
  `idcredito` int(11) NOT NULL,
  `pago` double NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`iddetalle_credito`),
  KEY `fkdetallecredito_credito_idx` (`idcredito`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_credito`
--

INSERT INTO `detalle_credito` (`iddetalle_credito`, `idcredito`, `pago`, `fecha`) VALUES
(67, 15, 249.55, '2019-03-14'),
(59, 15, 100.99, '2019-03-14'),
(66, 15, 250.45, '2019-03-14'),
(72, 1, 30, '2019-09-11'),
(5, 1, 30, '2019-02-25'),
(6, 1, 30, '2019-02-25'),
(7, 1, 30, '2019-02-25'),
(8, 1, 30, '2019-02-25'),
(70, 1, 150, '2019-03-26'),
(39, 13, 200, '2019-03-13'),
(33, 9, 200, '2019-03-13'),
(71, 3, 1325, '2019-04-17'),
(65, 15, 524.01, '2019-03-14'),
(37, 9, 300, '2019-03-13'),
(40, 13, 100, '2019-03-13'),
(41, 13, 100, '2019-03-13'),
(42, 13, 50, '2019-03-13'),
(49, 14, 100, '2019-03-14'),
(50, 14, 200, '2019-03-14'),
(51, 14, 150, '2019-03-14'),
(52, 14, 50, '2019-03-14'),
(53, 14, 20, '2019-03-14'),
(24, 3, 20, '2019-03-13'),
(25, 3, 10, '2019-03-13'),
(26, 3, 50, '2019-03-13'),
(27, 3, 100, '2019-03-13'),
(28, 3, 25, '2019-03-02'),
(54, 14, 30, '2019-03-14'),
(55, 14, 100, '2019-03-14'),
(63, 15, 75, '2019-03-14'),
(74, 17, 200, '2019-10-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

DROP TABLE IF EXISTS `detalle_ventas`;
CREATE TABLE IF NOT EXISTS `detalle_ventas` (
  `iddetalle_ventas` int(11) NOT NULL AUTO_INCREMENT,
  `idproducto` int(11) NOT NULL,
  `idventa` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  PRIMARY KEY (`iddetalle_ventas`),
  KEY `fkdetalleventa_producto_idx` (`idproducto`),
  KEY `fkdetalle_venta_ventas_idx` (`idventa`)
) ENGINE=MyISAM AUTO_INCREMENT=375 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`iddetalle_ventas`, `idproducto`, `idventa`, `cantidad`, `importe`) VALUES
(1, 200, 1, 1, 70),
(2, 200, 1, 1, 70),
(3, 200, 1, 1, 70),
(4, 200, 2, 1, 70),
(5, 200, 2, 1, 70),
(6, 200, 2, 1, 70),
(7, 200, 2, 1, 70),
(8, 200, 2, 1, 70),
(9, 200, 2, 1, 70),
(10, 200, 3, 1, 70),
(11, 200, 3, 1, 70),
(12, 200, 3, 1, 70),
(13, 200, 3, 1, 70),
(14, 200, 4, 1, 70),
(15, 200, 4, 1, 70),
(16, 200, 4, 1, 70),
(17, 200, 4, 1, 70),
(18, 101, 5, 1, 300),
(19, 500, 6, 3, 2175),
(20, 300, 6, 1, 40.5),
(21, 500, 6, 1, 600),
(22, 300, 7, 1, 40.5),
(23, 500, 7, 1, 700),
(24, 300, 7, 1, 40.5),
(25, 500, 8, 1, 700),
(26, 300, 8, 1, 40.5),
(27, 500, 8, 1, 700),
(28, 300, 8, 1, 40.5),
(29, 150, 9, 1, 30),
(30, 300, 9, 1, 40.5),
(31, 200, 9, 1, 70),
(32, 500, 9, 1, 700),
(33, 500, 10, 1, 700),
(34, 150, 10, 1, 30),
(35, 150, 10, 1, 30),
(36, 300, 10, 1, 40.5),
(37, 200, 10, 1, 70),
(38, 150, 11, 2, 60),
(39, 200, 11, 2, 140),
(40, 2000, 11, 1, 500),
(41, 150, 12, 2, 60),
(42, 200, 12, 2, 140),
(43, 2000, 12, 1, 500),
(44, 200, 13, 2, 140),
(45, 300, 13, 500, 20250),
(46, 150, 14, 2, 60),
(47, 500, 15, 2, 1400),
(48, 200, 16, 2, 140),
(49, 300, 16, 5, 202.5),
(50, 300, 17, 2, 81),
(51, 200, 18, 2, 140),
(52, 200, 19, 2, 140),
(53, 150, 20, 2, 60),
(54, 2000, 20, 4, 2000),
(55, 300, 21, 50, 2025),
(56, 2000, 22, 2, 1000),
(57, 500, 22, 1, 700),
(58, 500, 23, 2, 1400),
(59, 150, 23, 2, 60),
(60, 500, 24, 2, 1400),
(61, 300, 25, 1, 40.5),
(62, 150, 25, 4, 120),
(63, 2000, 25, 2, 1000),
(64, 500, 25, 1, 700),
(65, 200, 27, 4, 280),
(66, 150, 27, 2, 60),
(67, 500, 27, 2, 1400),
(68, 200, 28, 2, 140),
(69, 300, 28, 2, 81),
(70, 2000, 28, 2, 1000),
(71, 200, 29, 1, 70),
(72, 2000, 29, 1, 500),
(73, 500, 29, 2, 1400),
(74, 300, 29, 2, 81),
(75, 2000, 30, 5, 2500),
(76, 500, 31, 1, 700),
(77, 150, 31, 1, 30),
(78, 200, 31, 1, 70),
(79, 150, 31, 1, 30),
(80, 500, 31, 1, 700),
(81, 150, 32, 1, 30),
(82, 200, 32, 1, 70),
(83, 300, 33, 1, 40.5),
(84, 300, 33, 2, 81),
(85, 2000, 33, 2, 1000),
(86, 200, 34, 1, 70),
(87, 500, 34, 2, 1400),
(88, 2000, 35, 5, 2500),
(89, 150, 35, 2, 60),
(90, 500, 35, 2, 1400),
(91, 150, 36, 2, 60),
(92, 150, 37, 2, 60),
(93, 300, 38, 5, 202.5),
(94, 500, 38, 4, 2800),
(95, 150, 39, 5, 150),
(96, 500, 39, 5, 3500),
(97, 150, 40, 1, 30),
(98, 150, 40, 2, 60),
(99, 150, 40, 1, 30),
(100, 150, 40, 2, 60),
(101, 150, 40, 1, 30),
(102, 150, 40, 1, 30),
(103, 150, 40, 4, 120),
(104, 150, 41, 5, 150),
(105, 150, 41, 5, 150),
(106, 500, 41, 1, 700),
(107, 500, 41, 1, 700),
(108, 500, 41, 5, 3500),
(109, 500, 42, 5, 3500),
(110, 300, 42, 2, 81),
(111, 300, 42, 2, 81),
(112, 500, 42, 1, 700),
(113, 150, 43, 4, 120),
(114, 150, 44, 1, 30),
(115, 200, 44, 1, 70),
(116, 300, 44, 1, 40.5),
(117, 500, 44, 1, 700),
(118, 2000, 44, 1, 500),
(119, 150, 45, 1, 30),
(120, 200, 45, 1, 70),
(121, 200, 46, 2, 140),
(122, 300, 46, 1, 40.5),
(123, 2000, 46, 1, 500),
(124, 500, 46, 1, 700),
(125, 150, 47, 5, 150),
(126, 150, 47, 2, 60),
(127, 150, 47, 2, 60),
(128, 500, 47, 1, 700),
(129, 200, 48, 5, 350),
(130, 2000, 48, 2, 1000),
(131, 500, 48, 1, 700),
(132, 2000, 48, 1, 500),
(133, 150, 48, 1, 30),
(134, 150, 49, 1, 30),
(135, 200, 49, 1, 70),
(136, 300, 49, 1, 40.5),
(137, 500, 49, 1, 700),
(138, 2000, 49, 1, 500),
(139, 150, 50, 5, 150),
(140, 150, 51, 5, 150),
(141, 150, 52, 2, 60),
(142, 150, 52, 2, 60),
(143, 500, 52, 1, 700),
(144, 150, 53, 5, 150),
(145, 500, 53, 2, 1400),
(146, 2000, 53, 1, 500),
(147, 150, 54, 5, 150),
(148, 150, 54, 5, 150),
(149, 150, 54, 8, 240),
(150, 150, 55, 1, 30),
(151, 200, 55, 1, 70),
(152, 500, 55, 1, 700),
(153, 2000, 55, 1, 500),
(154, 150, 56, 2, 60),
(155, 200, 56, 2, 140),
(156, 150, 56, 5, 150),
(157, 2000, 56, 1, 500),
(158, 500, 56, 1, 700),
(159, 500, 57, 1, 700),
(160, 2000, 57, 2, 1000),
(161, 2000, 58, 1, 500),
(162, 150, 58, 70, 2100),
(163, 2000, 58, 1, 500),
(164, 200, 59, 9, 630),
(165, 300, 59, 3, 121.5),
(166, 500, 59, 9, 6300),
(167, 2000, 59, 5, 2500),
(168, 150, 59, 5, 150),
(169, 150, 60, 25, 750),
(170, 200, 60, 15, 1050),
(171, 300, 60, 185, 7492.5),
(172, 500, 60, 25, 17500),
(173, 150, 61, 5, 150),
(174, 150, 62, 3, 90),
(175, 150, 63, 2, 60),
(176, 150, 64, 5, 150),
(177, 150, 65, 2, 60),
(178, 150, 66, 2, 60),
(179, 150, 67, 2, 60),
(180, 300, 67, 4, 162),
(181, 150, 68, 10, 300),
(182, 300, 69, 1, 40.5),
(183, 150, 70, 2, 60),
(184, 150, 70, 5, 150),
(185, 500, 71, 1, 700),
(186, 2000, 71, 1, 500),
(187, 150, 72, 5, 150),
(188, 500, 72, 1, 700),
(189, 2000, 72, 2, 1000),
(190, 150, 73, 1, 30),
(191, 500, 73, 1, 700),
(192, 500, 74, 1, 700),
(193, 150, 75, 1, 30),
(194, 300, 76, 5, 202.5),
(195, 150, 77, 1, 30),
(196, 2000, 77, 1, 500),
(197, 500, 78, 1, 700),
(198, 2000, 78, 1, 500),
(199, 150, 79, 1, 30),
(200, 2000, 80, 1, 500),
(201, 150, 81, 1, 30),
(202, 150, 82, 1, 30),
(203, 150, 83, 1, 30),
(204, 200, 83, 1, 70),
(205, 150, 84, 2, 60),
(206, 150, 85, 1, 30),
(207, 2000, 85, 1, 500),
(208, 150, 86, 1, 30),
(209, 2000, 86, 1, 500),
(210, 150, 87, 1, 30),
(211, 150, 87, 1, 30),
(212, 150, 87, 1, 30),
(213, 150, 87, 1, 30),
(214, 150, 88, 1, 30),
(215, 200, 88, 1, 70),
(216, 150, 88, 1, 30),
(217, 500, 88, 1, 700),
(218, 150, 88, 1, 30),
(219, 300, 88, 1, 40.5),
(220, 150, 89, 1, 30),
(221, 150, 90, 1, 30),
(222, 150, 91, 1, 30),
(223, 150, 92, 1, 30),
(224, 200, 92, 1, 70),
(225, 300, 92, 1, 40.5),
(226, 500, 92, 1, 700),
(227, 2000, 92, 1, 500),
(228, 150, 93, 1, 30),
(229, 200, 93, 1, 70),
(230, 2000, 93, 1, 500),
(231, 150, 93, 1, 30),
(232, 150, 93, 5, 150),
(233, 150, 94, 1, 30),
(234, 200, 94, 1, 70),
(235, 300, 94, 1, 40.5),
(236, 500, 94, 1, 700),
(237, 2000, 94, 1, 500),
(238, 150, 95, 1, 30),
(239, 150, 96, 1, 30),
(240, 150, 97, 1, 30),
(241, 200, 97, 1, 70),
(242, 2000, 98, 1, 500),
(243, 500, 98, 1, 700),
(244, 300, 98, 1, 40.5),
(245, 200, 98, 1, 70),
(246, 150, 98, 1, 30),
(247, 150, 99, 1, 30),
(248, 200, 99, 1, 70),
(249, 300, 99, 1, 40.5),
(250, 500, 99, 1, 700),
(251, 2000, 99, 1, 500),
(252, 300, 100, 1, 40.5),
(253, 100, 101, 5, 1750),
(254, 100, 102, 2, 700),
(255, 100, 102, 2, 700),
(256, 20, 103, 1, 35),
(257, 20, 104, 24, 840),
(258, 20, 105, 1, 35),
(259, 20, 106, 1, 35),
(260, 100, 106, 1, 350),
(261, 100, 106, 1, 350),
(262, 20, 107, 1, 35),
(263, 300, 108, 1, 40.5),
(264, 20, 109, 5, 175),
(265, 20, 109, 5, 175),
(266, 20, 109, 5, 175),
(267, 20, 109, 5, 175),
(268, 20, 110, 2, 70),
(269, 20, 110, 2, 70),
(270, 20, 110, 2, 70),
(271, 20, 110, 2, 70),
(272, 20, 110, 2, 70),
(273, 20, 111, 1, 35),
(274, 100, 111, 1, 350),
(275, 200, 112, 2, 140),
(276, 20, 112, 4, 140),
(277, 500, 113, 1, 700),
(278, 200, 114, 5, 350),
(279, 20, 115, 1, 35),
(280, 100, 115, 1, 350),
(281, 150, 115, 1, 30),
(282, 200, 115, 1, 70),
(283, 300, 115, 1, 40.5),
(284, 301, 115, 1, 2.5),
(285, 302, 115, 1, 0),
(286, 500, 115, 1, 700),
(287, 2000, 115, 1, 500),
(288, 20, 116, 1, 35),
(289, 100, 117, 50, 17500),
(290, 150, 117, 50, 1500),
(291, 301, 117, 30, 75),
(292, 2000, 117, 1, 500),
(293, 2000, 118, 2, 1000),
(294, 566, 119, 8, 5600),
(295, 566, 120, 1, 700),
(296, 500, 121, 1, 700),
(297, 55, 122, 15, 600),
(298, 20, 123, 1, 35),
(299, 55, 123, 1, 40),
(300, 100, 123, 1, 350),
(301, 150, 123, 1, 30),
(302, 200, 123, 1, 70),
(303, 301, 123, 1, 2.5),
(304, 301, 123, 1, 2.5),
(305, 20, 124, 50, 1750),
(306, 55, 124, 100, 4000),
(307, 100, 124, 150, 52500),
(308, 200, 125, 2, 140),
(309, 150, 125, 5, 150),
(310, 100, 126, 1, 350),
(311, 300, 126, 10, 405),
(317, 300, 129, 2, 81),
(316, 20, 129, 1, 35),
(315, 302, 129, 5, 0),
(318, 20, 130, 2, 70),
(319, 20, 131, 2, 70),
(320, 100, 131, 1, 350),
(321, 150, 132, 5, 150),
(322, 200, 133, 2, 140),
(323, 566, 133, 5, 3500),
(324, 20, 134, 5, 175),
(325, 20, 135, 15, 525),
(326, 100, 136, 5, 1750),
(327, 2000, 136, 2, 1000),
(328, 100, 136, 1, 350),
(329, 20, 137, 5, 175),
(330, 500, 138, 2, 1400),
(331, 300, 138, 5, 202.5),
(332, 55, 139, 1, 40),
(333, 55, 139, 1, 40),
(334, 566, 140, 1, 700),
(335, 150, 141, 1, 30),
(336, 302, 141, 2, 20),
(337, 566, 141, 1, 700),
(338, 2000, 141, 2, 1000),
(339, 20, 142, 1, 35),
(340, 55, 142, 1, 40),
(341, 100, 142, 1, 350),
(342, 150, 142, 1, 30),
(343, 200, 143, 2, 140),
(344, 20, 144, 2, 70),
(345, 2000, 144, 2, 1000),
(346, 20, 145, 5, 175),
(347, 20, 146, 2, 70),
(348, 20, 146, 2, 70),
(349, 566, 147, 2, 1400),
(350, 20, 148, 2, 70),
(351, 20, 149, 2, 70),
(352, 20, 150, 3, 105),
(353, 301, 151, 27, 67.5),
(354, 20, 152, 5, 175),
(355, 300, 153, 2, 81),
(356, 150, 153, 10, 300),
(357, 150, 153, 1, 30),
(358, 20, 154, 2, 70),
(359, 20, 155, 2, 70),
(360, 20, 155, 1, 35),
(361, 20, 156, 1, 35),
(362, 20, 157, 1, 35),
(363, 20, 158, 1, 35),
(364, 20, 159, 2, 70),
(365, 20, 159, 1, 35),
(366, 20, 159, 1, 35),
(367, 20, 160, 2, 70),
(368, 500, 160, 2, 1400),
(369, 20, 161, 1, 35),
(370, 55, 161, 1, 40),
(371, 150, 161, 1, 30),
(372, 300, 161, 1, 40.5),
(373, 100, 162, 2, 700),
(374, 20, 163, 2, 70);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `idempleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(75) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `salario_devengado` float NOT NULL,
  `comision` float DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  UNIQUE KEY `idempleado_UNIQUE` (`idempleado`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idempleado`, `nombre`, `apellido`, `direccion`, `telefono`, `cargo`, `salario_devengado`, `comision`) VALUES
(1, 'Roberto', 'Acosta', 'PUERTO RIKO', '70804980', 'JEFE', 700, 300),
(2, 'cris 2', 'men 2', 'lag 2', '70778899', 'vendedor', 350, 30),
(3, 'cris 3', 'men 3', 'lag 3', '70663366', 'gerente', 500, 30),
(4, 'cris 4', 'men 4', 'lag 4', '70888888', 'vendedor', 350, 30),
(7, 'cristian asdrubal', 'menjivar recinos', 'laguna seca, nueva concepcion', '78896633', 'Jefe de Unidad de personal', 500, 40.23),
(6, 'cristhian 6', 'menjivar 6', 'laguna seca 6', '6000000', 'supervisor', 400, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos`
--

DROP TABLE IF EXISTS `gastos`;
CREATE TABLE IF NOT EXISTS `gastos` (
  `idgasto` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_gasto` varchar(50) NOT NULL,
  `total` double NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idgasto`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `gastos`
--

INSERT INTO `gastos` (`idgasto`, `tipo_gasto`, `total`, `fecha`) VALUES
(1, 'Pago a Empleados ac', 500, '2019-03-07'),
(2, 'Pago a Empleados', 500, '2019-03-07'),
(3, 'Otros', 400, '2019-03-04'),
(4, 'Pago a Empleados', 50, '2019-01-23'),
(5, 'gastos medicos', 650, '2019-01-21'),
(6, 'Pago a Empleados', 300, '2019-02-25'),
(8, 'gastos diarios', 405, '2019-03-08'),
(9, 'Pago a Empleados ac', 500.99, '2019-01-05'),
(10, 'Papeleria y Utiles', 205.55, '2019-03-11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `guia`
--

DROP TABLE IF EXISTS `guia`;
CREATE TABLE IF NOT EXISTS `guia` (
  `idguia` int(11) NOT NULL AUTO_INCREMENT,
  `rutguia` varchar(25) NOT NULL,
  `numguia` varchar(25) NOT NULL,
  `tipoguia` varchar(25) NOT NULL,
  `estadoguia` varchar(25) NOT NULL,
  `total` double(10,3) NOT NULL,
  PRIMARY KEY (`idguia`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `guia`
--

INSERT INTO `guia` (`idguia`, `rutguia`, `numguia`, `tipoguia`, `estadoguia`, `total`) VALUES
(1, '32554', '23131', 'Electronica', 'Pendiente', 20000.000),
(2, '79857200', '52384920', 'GuÃ­a', 'Cancelado', 300000.000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `idmarca` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`idmarca`, `marca`) VALUES
(13, 'SAMSUMG'),
(2, 'RIBOX'),
(3, 'NIKE'),
(4, 'Steel'),
(6, 'nueva'),
(7, 'seventy 7'),
(8, 'ocho'),
(9, 'nueve');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `masvendidos`
--

DROP TABLE IF EXISTS `masvendidos`;
CREATE TABLE IF NOT EXISTS `masvendidos` (
  `idproductomas` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `idproducto` int(11) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idproductomas`),
  KEY `fkmasvendidos_producto` (`idproducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
CREATE TABLE IF NOT EXISTS `notificaciones` (
  `idNotificacion` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `NombreNoti` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `AsuntoNoti` varchar(800) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fechaNoti` date NOT NULL,
  `visto` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idNotificacion`),
  KEY `idusuario` (`idusuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `idproducto` int(11) NOT NULL,
  `medida` varchar(20) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `color` varchar(25) DEFAULT NULL,
  `serie` varchar(35) DEFAULT NULL,
  `modelo` varchar(35) DEFAULT NULL,
  `enLinea` tinyint(1) NOT NULL,
  `precio_compra` double NOT NULL,
  `precio_sugerido` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `inventario` int(11) DEFAULT NULL,
  `averiado` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  `idproveedor` int(11) DEFAULT NULL,
  `idcategoria` int(11) DEFAULT NULL,
  `tipoRegistro` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `fkproductoo_marca` (`idmarca`),
  KEY `fkproductoo_categoria` (`idcategoria`),
  KEY `idproveedor` (`idproveedor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idproducto`, `medida`, `nombre`, `color`, `serie`, `modelo`, `enLinea`, `precio_compra`, `precio_sugerido`, `stock`, `inventario`, `averiado`, `idmarca`, `idproveedor`, `idcategoria`, `tipoRegistro`) VALUES
(200, '7.5', 'zapato', 'blanco y negro', 'serie seria', 'model fashon', 0, 50, 70, 5, 19, 2, 2, 1, 4, NULL),
(150, ' no', 'piza', 'roja', 'no', 'no', 1, 20, 30, 5, 986, 0, 6, 3, 3, NULL),
(300, 'una cuarta', 'Salchichas', 'no', 'no', 'No', 1, 30.5, 40.5, 500, 1087, 0, 9, 4, 2, NULL),
(500, '15 pulgadas', 'computadora ', 'gris', 'xpabillon', 'modelo', 1, 500, 700, 2, 1003, 0, 4, 4, 3, NULL),
(2000, '15 pulgadas', 'TELEVISOR', 'NEGRO', 'NO', 'NO', 1, 300, 500, 800, 1000, 0, 13, 2, 6, NULL),
(100, 'no', 'Celular', 'negro', 'no', 'no', 1, 200, 350, 5, 2, 0, 13, 4, 6, NULL),
(20, '22xs', 'Locion IVON', 'clara', 'no', 'no', 1, 21.5, 35, 25, 86, 0, 4, 7, 6, NULL),
(301, '12 onzas', 'Machipuchi', 'Azul, amarillo y verde', 'no', 'no', 1, 1, 2.5, 5, 946, 0, 3, 6, 7, NULL),
(302, 'no', 'no', '0', '0', '0', 1, 0, 10, 0, 992, 0, 2, 1, 2, NULL),
(566, 'asas', 'taza de baÃ?Â±o', 'blanco', 'no', 'no', 1, 500, 700, 2, 10, 0, 8, 3, 2, NULL),
(55, '2*2', 'Silla', 'marron', 'no', 'modelo actual', 1, 25, 40, 5, 32, 0, 8, 4, 8, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `emitidopor` varchar(50) DEFAULT NULL,
  `moneda` varchar(25) NOT NULL,
  `tipodocumento` varchar(50) DEFAULT NULL,
  `fecha` date NOT NULL,
  `periododeclarado` varchar(75) DEFAULT NULL,
  `comentario` varchar(100) DEFAULT NULL,
  `mtoaf` varchar(50) DEFAULT NULL,
  `mtoe` varchar(50) DEFAULT NULL,
  `iva` double(10,3) NOT NULL,
  `total` double(10,3) NOT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`idproveedor`, `emitidopor`, `moneda`, `tipodocumento`, `fecha`, `periododeclarado`, `comentario`, `mtoaf`, `mtoe`, `iva`, `total`) VALUES
(2, 'Karla Martel', 'Colon', 'PDF', '2019-09-18', 'Septiembre', 'A tiempo', 'Septiembre', 'Septiembre', 0.300, 40000.000),
(1, 'Karen', 'Peso', 'Word', '2019-09-18', 'Pasado', 'A tiempo', 'dmdjm', 'aksjmjk', 0.100, 20000.000),
(3, 'Raul', 'Cordoba', 'Legal', '2019-09-11', 'Octubre', 'A tiempo', 'mocs', 'scjkdnj', 0.400, 5000.000),
(4, 'Raul Rivera', 'Peso', 'PDF', '2019-09-07', 'Diciembre', 'A tiempo', 'Diciembre', 'Diciembre', 0.300, 10000.000),
(5, 'Jose Lujan', 'Peso', 'Word', '2019-09-03', 'Diciembre', 'A tiempo', 'Diciembre', 'Diciembre', 0.600, 7000.000),
(6, 'Alexys Lozada', 'Colon', 'PDF', '2019-09-01', 'Diciembre', 'A tiempo', 'Diciembre', 'Diciembre', 0.300, 15000.000),
(7, 'Juan Villalvaso', 'Peso', 'Word', '2019-09-05', 'Enero', 'A tiempo', 'Enero', 'Enero', 0.800, 8000.000),
(18, 'Yesi', 'Peso', 'PDF', '2019-09-26', 'Pasado', 'A tiempo', 'asaxax', 'cscsa', 0.200, 21000.000),
(19, 'Carlos Arcila dias', 'Peso', 'PDF', '2019-09-25', 'Pasado', 'A tiempo', 'asaxax', 'cscsa', 0.300, 21000.000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(32) NOT NULL,
  `tipo_usuario` varchar(25) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `usuario`, `contraseña`, `tipo_usuario`) VALUES
(77, 'admin2', '81dc9bdb52d04dc20036dbd8313ed055', 'administrador'),
(78, 'admin3', '81dc9bdb52d04dc20036dbd8313ed055', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `idventa` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(30) NOT NULL,
  `monto` double NOT NULL,
  `tipo_venta` varchar(40) NOT NULL,
  `tipo_pago` varchar(40) NOT NULL,
  PRIMARY KEY (`idventa`)
) ENGINE=MyISAM AUTO_INCREMENT=164 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idventa`, `fecha`, `monto`, `tipo_venta`, `tipo_pago`) VALUES
(1, '2019-01-14', 210, 'Efectivo', 'Factura'),
(2, '2019-01-14', 420, 'Efectivo', 'Factura'),
(3, '2019-01-23', 280, 'Efectivo', 'Factura'),
(4, '2019-01-23', 280, 'Efectivo', 'Factura'),
(5, '2019-02-08', 300, 'Efectivo', 'Factura'),
(6, '2019-02-25', 2815.5, 'Efectivo', 'Factura'),
(7, '2019-02-25', 781, 'Efectivo', 'Factura'),
(8, '2019-02-25', 1481, 'Efectivo', 'Factura'),
(9, '2019-02-25', 840.5, 'Efectivo', 'Factura'),
(10, '2019-02-25', 870.5, 'Al Crédito', 'Factura'),
(11, '2019-03-02', 700, 'al contado', 'con cheque'),
(12, '2019-03-02', 700, 'al contado', 'con cheque'),
(13, '2019-03-02', 20390, 'al contado', 'con cheque'),
(14, '2019-03-02', 60, 'al contado', 'con cheque'),
(15, '2019-03-02', 1400, 'al contado', 'con cheque'),
(16, '2019-03-02', 342.5, 'al contado', 'con cheque'),
(17, '2019-03-02', 81, 'al contado', 'con cheque'),
(18, '2019-03-02', 140, 'al contado', 'con cheque'),
(19, '2019-03-02', 140, 'credito', 'targeta'),
(20, '2019-03-02', 2060, 'al contado', 'targeta'),
(21, '2019-03-02', 2025, 'anbulante', 'de una ves'),
(22, '2019-03-02', 1700, 'credito', 'targeta'),
(23, '2019-03-02', 1460, 'preguntele al cajero', 'nose'),
(24, '2019-03-02', 1400, 'credito', 'targeta'),
(25, '2019-03-02', 1860.5, 'al contado', 'con cheque'),
(26, '2019-03-02', 0, '', ''),
(27, '2019-03-02', 1740, '', ''),
(28, '2019-03-02', 1221, 'Crédito', 'Cheque'),
(29, '2019-03-03', 2051, 'Crédito', 'Cheque'),
(30, '2019-03-03', 2500, 'Factura', 'Contado'),
(31, '2019-03-03', 1530, 'Al Crédito', 'Factura'),
(32, '2019-03-03', 100, 'Efectivo', 'Factura'),
(33, '2019-03-03', 1121.5, 'Crédito', 'Contado'),
(34, '2019-03-03', 1470, 'Factura', 'Cheque'),
(35, '2019-03-03', 3960, 'Factura', 'Cheque'),
(36, '2019-03-03', 60, 'Factura', 'Cheque'),
(37, '2019-03-03', 60, 'Factura', 'Cheque'),
(38, '2019-03-03', 3002.5, 'Factura', 'Cheque'),
(39, '2019-03-03', 3650, 'Factura', 'Cheque'),
(40, '2019-03-04', 360, 'Factura', 'Cheque'),
(41, '2019-03-04', 5200, 'Factura', 'Cheque'),
(42, '2019-03-04', 4362, 'Factura', 'Cheque'),
(43, '2019-03-04', 120, 'Factura', 'Cheque'),
(44, '2019-03-04', 1340.5, 'Factura', 'Cheque'),
(45, '2019-03-04', 100, 'Factura', 'Cheque'),
(46, '2019-03-04', 1380.5, 'Factura', 'Cheque'),
(47, '2019-03-04', 970, 'Factura', 'Cheque'),
(48, '2019-03-04', 2580, 'Factura', 'Cheque'),
(49, '2019-03-04', 1340.5, 'Factura', 'Cheque'),
(50, '2019-03-04', 150, 'Factura', 'Cheque'),
(51, '2019-03-04', 150, 'Factura', 'Cheque'),
(52, '2019-03-05', 820, 'Factura', 'Cheque'),
(53, '2019-03-05', 2050, 'Factura', 'Cheque'),
(54, '2019-03-05', 540, 'Factura', 'Cheque'),
(55, '2019-03-05', 1300, 'Factura', 'Cheque'),
(56, '2019-03-07', 1550, 'Factura', 'Cheque'),
(57, '2019-03-07', 1700, 'CrÃ©dito', 'Contado'),
(58, '2019-03-07', 3100, 'Factura', 'Cheque'),
(59, '2019-03-07', 9701.5, 'Factura', 'Cheque'),
(60, '2019-03-07', 26792.5, 'Factura', 'Cheque'),
(61, '2019-03-07', 150, 'Factura', 'Cheque'),
(62, '2019-03-07', 90, 'Factura', 'Cheque'),
(63, '2019-03-07', 60, 'Factura', 'Cheque'),
(64, '2019-03-07', 150, 'Factura', 'Cheque'),
(65, '2019-03-07', 3560, 'Factura', 'Cheque'),
(66, '2019-03-07', 3560, 'Factura', 'Cheque'),
(67, '2019-03-07', 222, 'Factura', 'Cheque'),
(68, '2019-03-07', 300, 'Factura', 'Cheque'),
(69, '2019-03-07', 40.5, 'Factura', 'Cheque'),
(70, '2019-03-07', 210, 'Factura', 'Cheque'),
(71, '2019-03-07', 1200, 'Factura', 'Cheque'),
(72, '2019-03-07', 1850, 'Factura', 'Cheque'),
(73, '2019-03-07', 730, 'Factura', 'Cheque'),
(74, '2019-03-07', 700, 'Factura', 'Cheque'),
(75, '2019-03-07', 30, 'Factura', 'Cheque'),
(76, '2019-03-07', 202.5, 'Factura', 'Cheque'),
(77, '2019-03-07', 530, 'Factura', 'Cheque'),
(78, '2019-03-07', 1200, 'Factura', 'Cheque'),
(79, '2019-03-07', 30, 'Factura', 'Cheque'),
(80, '2019-03-07', 500, 'Factura', 'Cheque'),
(81, '2019-03-07', 30, 'Factura', 'Cheque'),
(82, '2019-03-07', 30, 'Factura', 'Cheque'),
(83, '2019-03-07', 100, 'Factura', 'Cheque'),
(84, '2019-03-07', 60, 'Factura', 'Cheque'),
(85, '2019-03-07', 530, 'Factura', 'Cheque'),
(86, '2019-03-07', 530, 'Factura', 'Cheque'),
(87, '2019-03-07', 120, 'Factura', 'Cheque'),
(88, '2019-03-07', 900.5, 'Factura', 'Cheque'),
(89, '2019-03-07', 30, 'Factura', 'Cheque'),
(90, '2019-03-07', 30, 'Factura', 'Cheque'),
(91, '2019-03-07', 30, 'Factura', 'Cheque'),
(92, '2019-03-07', 1340.5, 'Factura', 'Cheque'),
(93, '2019-03-07', 780, 'Factura', 'Cheque'),
(94, '2019-03-07', 1340.5, 'Factura', 'Cheque'),
(95, '2019-03-07', 30, 'Factura', 'Cheque'),
(96, '2019-03-07', 30, 'Factura', 'Cheque'),
(97, '2019-03-07', 100, 'Factura', 'Cheque'),
(98, '2019-03-07', 1340.5, 'Factura', 'Cheque'),
(99, '2019-03-07', 1340.5, 'Factura', 'Cheque'),
(100, '2019-03-07', 40.5, 'Factura', 'Cheque'),
(101, '2019-03-07', 1750, 'Factura', 'Cheque'),
(102, '2019-03-07', 1400, 'Factura', 'Cheque'),
(103, '2019-03-07', 35, 'Factura', 'Cheque'),
(104, '2019-03-07', 840, 'Factura', 'Cheque'),
(105, '2019-03-07', 35, 'Factura', 'Cheque'),
(106, '2019-03-07', 735, 'Factura', 'Cheque'),
(107, '2019-03-08', 35, 'Factura', 'Cheque'),
(108, '2019-03-08', 40.5, 'Factura', 'Cheque'),
(109, '2019-03-08', 700, 'Factura', 'Cheque'),
(110, '2019-03-08', 350, 'Factura', 'Cheque'),
(111, '2019-03-08', 385, 'Factura', 'Cheque'),
(112, '2019-03-11', 280, 'Factura', 'Cheque'),
(113, '2019-03-11', 700, 'Factura', 'Cheque'),
(114, '2019-03-13', 350, 'Factura', 'Cheque'),
(115, '2019-03-14', 1728, 'Factura', 'Cheque'),
(116, '2019-03-14', 35, 'Factura', 'Cheque'),
(117, '2019-03-14', 19575, 'Factura', 'Cheque'),
(118, '2019-03-14', 1000, 'CrÃ©dito', 'Cheque'),
(119, '2019-03-14', 5600, 'Factura', 'Cheque'),
(120, '2019-03-14', 700, 'Factura', 'Cheque'),
(121, '2019-03-14', 700, 'Factura', 'Cheque'),
(122, '2019-03-14', 600, 'Factura', 'Cheque'),
(123, '2019-03-14', 530, 'Factura', 'Cheque'),
(124, '2019-03-15', 58250, 'Factura', 'Cheque'),
(125, '2019-03-16', 290, 'Factura', 'Cheque'),
(126, '2019-03-17', 755, 'Factura', 'Contado'),
(130, '2019-03-22', 70, 'Factura', 'Cheque'),
(129, '2019-03-22', 116, 'Factura', 'Cheque'),
(131, '2019-03-22', 420, 'Factura', 'Cheque'),
(132, '2019-03-22', 150, 'Factura', 'Cheque'),
(133, '2019-03-22', 3640, 'Factura', 'Cheque'),
(134, '2019-03-23', 175, 'Factura', 'Cheque'),
(135, '2019-03-23', 525, 'Factura', 'Cheque'),
(136, '2019-03-24', 3100, 'Factura', 'Cheque'),
(137, '2019-03-24', 175, 'Factura', 'Cheque'),
(138, '2019-03-24', 1602.5, 'Factura', 'Cheque'),
(139, '2019-03-25', 80, 'Factura', 'Cheque'),
(140, '2019-03-25', 700, 'Factura', 'Cheque'),
(141, '2019-03-26', 1750, 'Factura', 'Cheque'),
(142, '2019-03-26', 455, 'Factura', 'Cheque'),
(143, '2019-03-26', 140, 'Factura', 'Cheque'),
(144, '2019-04-01', 1070, 'Factura', 'Cheque'),
(145, '2019-04-01', 175, 'Factura', 'Cheque'),
(146, '2019-04-17', 140, 'Factura', 'Cheque'),
(147, '2019-09-11', 1400, 'Factura', 'Cheque'),
(148, '2019-09-11', 70, 'CrÃ©dito', 'Cheque'),
(149, '2019-09-17', 70, 'Factura', 'Cheque'),
(150, '2019-09-17', 105, 'Factura', 'Cheque'),
(151, '2019-09-18', 67.5, 'Factura', 'Cheque'),
(152, '2019-10-05', 175, 'Factura', 'Cheque'),
(153, '2019-10-05', 411, 'Factura', 'Cheque'),
(154, '2019-10-05', 70, 'Factura', 'Cheque'),
(155, '2019-10-05', 105, 'Factura', 'Cheque'),
(156, '2019-10-12', 35, 'Factura', 'Cheque'),
(157, '2019-10-12', 35, 'Factura', 'Cheque'),
(158, '2019-10-12', 35, 'Factura', 'Cheque'),
(159, '2019-10-12', 140, 'Factura', 'Cheque'),
(160, '2019-10-12', 1470, 'Factura', 'Cheque'),
(161, '2019-10-12', 145.5, 'Factura', 'Cheque'),
(162, '2019-10-12', 700, 'Factura', 'Cheque'),
(163, '2019-10-12', 70, 'Factura', 'Cheque');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `creditos`
--
ALTER TABLE `creditos`
  ADD CONSTRAINT `creditos_ibfk_1` FOREIGN KEY (`idcredito`) REFERENCES `clientes` (`idcliente`),
  ADD CONSTRAINT `creditos_ibfk_2` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`idcliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
