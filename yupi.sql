/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : yupi

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 26/09/2022 13:02:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id，主键',
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_account` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `avatar_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `user_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_status` int(11) NULL DEFAULT 0 COMMENT '用户状态',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'jack', '123', 'https://www.bing.com/ck/a?!&&p=c28eadc6119bd394JmltdHM9MTY2Mzk3NzYwMCZpZ3VpZD0xNDhjYTY5Ni04NmQ1LTY4YzYtMmZlYi1hOWUzODdmYjY5N2QmaW5zaWQ9NTQ2OQ&ptn=3&hsh=3&fclid=148ca696-86d5-68c6-2feb-a9e387fb697d&u=a1L2ltYWdlcy9zZWFyY2g_cT0lRTUlOUIlQkUlRTclODklODcmRk9STT1JUUZSQkEmaWQ9MUM4MjgwRDJENzVCODY1M0ZFNEY4NzgxNzM4N0Y1NzE4OUI1QUE0MQ&ntb=1', NULL, '123', '456', '789', 0, '2022-09-24 15:53:11', '2022-09-24 15:53:11', 0);
INSERT INTO `user` VALUES (2, 'jack', '123', 'https://www.bing.com/ck/a?!&&p=c28eadc6119bd394JmltdHM9MTY2Mzk3NzYwMCZpZ3VpZD0xNDhjYTY5Ni04NmQ1LTY4YzYtMmZlYi1hOWUzODdmYjY5N2QmaW5zaWQ9NTQ2OQ&ptn=3&hsh=3&fclid=148ca696-86d5-68c6-2feb-a9e387fb697d&u=a1L2ltYWdlcy9zZWFyY2g_cT0lRTUlOUIlQkUlRTclODklODcmRk9STT1JUUZSQkEmaWQ9MUM4MjgwRDJENzVCODY1M0ZFNEY4NzgxNzM4N0Y1NzE4OUI1QUE0MQ&ntb=1', 0, '123', '456', '789', 0, '2022-09-24 15:56:14', '2022-09-24 15:56:14', 0);
INSERT INTO `user` VALUES (3, NULL, 'jack ', NULL, NULL, '37e2b5b03e48187b2544aace4f08a9cb', NULL, NULL, 0, '2022-09-24 16:46:03', '2022-09-24 16:46:03', 0);

SET FOREIGN_KEY_CHECKS = 1;
