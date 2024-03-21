/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MariaDB
 Source Server Version : 100339
 Source Host           : 127.0.0.1:3366
 Source Schema         : db_springboot

 Target Server Type    : MariaDB
 Target Server Version : 100339
 File Encoding         : 65001

 Date: 21/03/2024 12:30:11
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
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `tokenPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_3m5n1w5trapxlbo2s42ugwdmd` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
BEGIN;
INSERT INTO `usuarios` VALUES (1, 'fdxdesarrollos@gmail.com', 'FDX', '$2a$10$ek1w7p.8g/NPjQK5/tCkQe28D3t0JS1gN8quhMhzsLdpZ4LeOAW02', 'admin', NULL);
INSERT INTO `usuarios` VALUES (2, 'usuario@gmail.com', 'USER', '$2y$10$DFh2G5qHI3i302dZg4RCFeCMIarBprfxWczvGSDalYkqwWz6xz0Hm', 'user', NULL);
INSERT INTO `usuarios` VALUES (3, 'gabo@gmail.com', 'GABO', '$2a$10$vWmx5UM6exPH3Eso9H/bmuyBDyYW5BAJPQRpCyfcrMbNADL.tlrgW', 'gabo', NULL);
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
