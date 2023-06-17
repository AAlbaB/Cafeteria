-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-02-2022 a las 18:49:45
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdcafeteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_producto`
--

CREATE TABLE `tb_producto` (
  `id_producto` int(11) NOT NULL,
  `Nombre` varchar(150) NOT NULL,
  `Referencia` varchar(150) NOT NULL,
  `Precio` int(11) NOT NULL,
  `Peso` int(11) NOT NULL,
  `Categoria` varchar(150) NOT NULL,
  `Inventario` int(11) NOT NULL,
  `Fecha_Creacion` varchar(20) NOT NULL,
  `Dias_Inventario` int(11) NOT NULL,
  `Cantidad_Ventas` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_producto`
--

INSERT INTO `tb_producto` (`id_producto`, `Nombre`, `Referencia`, `Precio`, `Peso`, `Categoria`, `Inventario`, `Fecha_Creacion`, `Dias_Inventario`, `Cantidad_Ventas`) VALUES
(2, 'Dulces', 'Quipitos', 1200, 2, 'Mecato', 15, '2021-02-01', 376, 0),
(3, 'Pepitas', '002', 500, 1, 'Dulces', 13, '2022-01-15', 28, 3),
(5, 'Bueñuelos', '001', 1300, 2, 'Panaderia', 6, '2022-02-10', 2, 0),
(7, 'Tinto', '004', 3000, 1, 'Bebidas', 30, '2022-02-12', 0, 25),
(8, 'Perico', '009', 6000, 2, 'Bebidas', 90, '2022-02-10', 2, 0),
(9, 'Agua con gas', '008', 3000, 1, 'Bebidas', 20, '2022-01-10', 33, 0),
(10, 'Dulcesitos', '007', 600, 1, 'Mecato', 10, '2000-10-10', 7795, 0),
(11, 'Papas', '031-98', 1300, 2, 'Mecato', 47, '2022-01-10', 33, 13);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_producto`
--
ALTER TABLE `tb_producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_producto`
--
ALTER TABLE `tb_producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
