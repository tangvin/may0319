/*
Navicat MySQL Data Transfer

Source Server         : localhost-192.168.1.104
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-03-18 14:09:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_import_temp
-- ----------------------------
DROP TABLE IF EXISTS `t_import_temp`;
CREATE TABLE `t_import_temp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `import_date` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pid_no` varchar(20) DEFAULT NULL,
  `bank_no` varchar(20) DEFAULT NULL,
  `bank_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reward_point` decimal(20,0) DEFAULT NULL,
  `expiration_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109999 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT = '导入数据临时表';
