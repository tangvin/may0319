/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : mpbct

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 22/03/2024 22:08:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_import_records
-- ----------------------------
DROP TABLE IF EXISTS `t_import_records`;
CREATE TABLE `t_import_records`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '批次号',
  `total_integral` bigint NULL DEFAULT NULL COMMENT '每批次的总积分和',
  `total_records` bigint NULL DEFAULT NULL COMMENT '每批次的总条数',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态（0-待提交 1-待审批 2-审批通过 3-拒绝）',
  `operator_id` bigint NULL DEFAULT NULL COMMENT '操作人id',
  `approval_id` bigint NULL DEFAULT NULL COMMENT '审批人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
