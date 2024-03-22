/*
Navicat MySQL Data Transfer

Source Server         : localhost-192.168.1.104
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-03-18 14:09:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app_user
-- ----------------------------
DROP TABLE IF EXISTS `t_app_user`;
CREATE TABLE `t_app_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid_no` varchar(20) DEFAULT NULL COMMENT '客户号',
  `remain_point` decimal(20,0) DEFAULT NULL COMMENT '剩余可用积分',
  `bank_no` varchar(20) DEFAULT NULL COMMENT '银行号',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '银行名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT = '客户信息表';
