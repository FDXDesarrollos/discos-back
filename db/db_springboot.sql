/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MariaDB
 Source Server Version : 100338
 Source Host           : 127.0.0.1:3366
 Source Schema         : db_springboot

 Target Server Type    : MariaDB
 Target Server Version : 100338
 File Encoding         : 65001

 Date: 04/01/2024 11:42:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for albums
-- ----------------------------
DROP TABLE IF EXISTS `albums`;
CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `interprete` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of albums
-- ----------------------------
BEGIN;
INSERT INTO `albums` VALUES (1, '2023-04-14 00:00:00', 'HEAVY METAL', 'BEAST IN BLACK', 180.5, 'FROM HELL WITH LOVE');
INSERT INTO `albums` VALUES (2, '2023-06-28 00:00:00', 'POWER METAL', 'ANGRA', 200, 'ANGELS CRY');
INSERT INTO `albums` VALUES (3, '2023-01-23 00:00:00', 'HEAVY METAL', 'BATTLE BEAST', 190, 'BRINGER OF PAIN');
INSERT INTO `albums` VALUES (4, '2023-03-14 00:00:00', 'THRASH METAL', 'SEPULTURA', 210, 'BENEATH THE REMAINS');
INSERT INTO `albums` VALUES (5, '2023-06-05 00:00:00', 'THRASH METAL', 'TESTAMENT', 210, 'SOULS OF BLACK');
INSERT INTO `albums` VALUES (6, '2023-06-15 00:00:00', 'SYMPHONIC METAL ', 'NIGHTWISH', 250, 'DARK PASSION PLAY');
INSERT INTO `albums` VALUES (7, '2023-06-13 00:00:00', 'POWER METAL', 'HELLOWEEN', 280, 'THE TIME OF THE OATH');
INSERT INTO `albums` VALUES (8, '2023-06-13 00:00:00', 'POWER METAL', 'HAMMER FALL', 230, 'NO SACRIFICE, NO VICTORY');
INSERT INTO `albums` VALUES (9, '2023-12-27 00:00:00', 'AAAAAAAAAA', 'AAAAAAAAAA', 500, 'AAAAAAAAAA');
INSERT INTO `albums` VALUES (10, '2023-12-27 00:00:00', 'ZZZZZZZZZ', 'ZZZZZZZZZ', 500, 'ZZZZZZZZZ');
COMMIT;

-- ----------------------------
-- Table structure for producto
-- ----------------------------
DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of producto
-- ----------------------------
BEGIN;
INSERT INTO `producto` VALUES (1, 'QUESO RANCIO', 10);
INSERT INTO `producto` VALUES (2, 'QUESO DOBLE CREMA', 20);
INSERT INTO `producto` VALUES (3, 'QUESO RAYADO', 15);
INSERT INTO `producto` VALUES (4, 'QUESO BLANCO', 15);
INSERT INTO `producto` VALUES (5, 'QUESO AMARILLO', 20);
COMMIT;

-- ----------------------------
-- Table structure for rol
-- ----------------------------
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of rol
-- ----------------------------
BEGIN;
INSERT INTO `rol` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `rol` VALUES (2, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `roles` VALUES (2, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_puhr3k3l7bj71hb7hk7ktpxn0` (`nombre_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of usuario
-- ----------------------------
BEGIN;
INSERT INTO `usuario` VALUES (1, 'fdxdesarrollos@gmail.com', 'admin', 'admin', '$2a$10$kDBWpHAGK0qL.TnyLrz2sOlQtgtDbK53JsmXDnib9eR3l45lM6cnG');
INSERT INTO `usuario` VALUES (2, 'usuario@gmail.com', 'user', 'user', '$2a$10$jk4F49/klfc27aY5koRAVOY2GUVUpFeyMYfZ.p8omvv9TOFnxWN.y');
COMMIT;

-- ----------------------------
-- Table structure for usuario_rol
-- ----------------------------
DROP TABLE IF EXISTS `usuario_rol`;
CREATE TABLE `usuario_rol` (
  `usuario_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FKe3kd49gu3mhj2ty5kl44qsytp` (`rol_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of usuario_rol
-- ----------------------------
BEGIN;
INSERT INTO `usuario_rol` VALUES (1, 1);
INSERT INTO `usuario_rol` VALUES (1, 2);
INSERT INTO `usuario_rol` VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_3m5n1w5trapxlbo2s42ugwdmd` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
BEGIN;
INSERT INTO `usuarios` VALUES (1, 'webmaster@gmail.com', 'webmaster', '$2a$10$qZ1v5HDkPW/p.mMZCJ3OsO.TFFVi6CR5lNmewjpvvlkVQWN3M7bn2', 'admin');
INSERT INTO `usuarios` VALUES (2, 'gabo@gmail.com', 'gabo', '$2a$10$eLlFCPFWmQBh3uEw0oxla.2qs8o5cE0BF2cTnjPEmnoHnbq.Ilu6q', 'user');
INSERT INTO `usuarios` VALUES (3, 'asdasdasd@gmail.com', 'asdasdasd adasdsad tertetet', '$2a$10$oLpy0mvSYIOHh2JWJZA79uXlRf15gBoQY49mIHlFbNKfEoPG3Wphq', 'asno');
COMMIT;

-- ----------------------------
-- Table structure for usuarios_roles
-- ----------------------------
DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE `usuarios_roles` (
  `usuario_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FK5338ehgluufgc8bpj08nrq970` (`rol_id`),
  CONSTRAINT `FK5338ehgluufgc8bpj08nrq970` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of usuarios_roles
-- ----------------------------
BEGIN;
INSERT INTO `usuarios_roles` VALUES (1, 1);
INSERT INTO `usuarios_roles` VALUES (1, 2);
INSERT INTO `usuarios_roles` VALUES (2, 2);
INSERT INTO `usuarios_roles` VALUES (3, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
