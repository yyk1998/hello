/*
 Navicat Premium Data Transfer

 Source Server         : Liao
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : jfinal_demo_user

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 26/02/2019 22:03:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person`  (
  `id` varchar(166) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('2019-02-26-18:22:44-77be0b2d-a211-4e93-a46f-bfc6c636457f', 'keke', '11', 'keke');
INSERT INTO `t_person` VALUES ('2019-02-26-19:01:27-4497f5a5-9b28-4c56-9094-0f0fc579ad22', 'test1', '1', 'test1');
INSERT INTO `t_person` VALUES ('2019-02-26-19:27:14-4441d3a9-3c4d-44fc-ba4b-62d2a5ec965c', 'haha', '1', 'hehe');
INSERT INTO `t_person` VALUES ('2019-02-26-19:27:32-289bc1c0-162f-4b1d-832c-1d06dd7624ff', 'hhh', '44', ',.,.,m');
INSERT INTO `t_person` VALUES ('2019-02-26-19:29:34-2f0c13fc-118a-4591-a4ef-237a9634fc10', 'JoJo', '122', 'MyHome');
INSERT INTO `t_person` VALUES ('2019-02-26-21:47:49-3f30e99e-82fe-4d66-93db-e6114ff8a257', 'test', '12', 'test');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'zhangsan', '1234');
INSERT INTO `t_user` VALUES (2, 'lisi', '12341');
INSERT INTO `t_user` VALUES (3, 'null', '11');

SET FOREIGN_KEY_CHECKS = 1;
