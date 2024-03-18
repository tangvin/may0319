/*
Navicat MySQL Data Transfer

Source Server         : localhost-192.168.1.104
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-03-18 14:10:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_integral_log
-- ----------------------------
DROP TABLE IF EXISTS `t_integral_log`;
CREATE TABLE `t_integral_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '客户id',
  `pid_no` varchar(20) DEFAULT NULL COMMENT '客户号',
  `changeType` varchar(1) DEFAULT NULL COMMENT '变化类型（1-增加 2-减少）',
  `num` decimal(20,0) DEFAULT NULL COMMENT '剩余可用积分',
  `bank_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '行号',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '银行名称',
  `expiration_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
