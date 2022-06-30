/*
 Navicat Premium Data Transfer

 Source Server         : Chensql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : basiness_db1

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/06/2022 17:39:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rate
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate`  (
  `rate_id` int NOT NULL COMMENT '汇率id',
  `rate` double NULL DEFAULT NULL COMMENT '汇率',
  `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易的货币',
  PRIMARY KEY (`rate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES (1, 0.5, '火星');
INSERT INTO `rate` VALUES (2, 1, '美元');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `RIC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票代码',
  `Ticker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '股票价格',
  `Issuer_sector` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发行机构',
  `date` datetime NULL DEFAULT NULL COMMENT '时间',
  `size` int NULL DEFAULT NULL COMMENT '股票发行数量',
  `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '股票发行币种',
  PRIMARY KEY (`RIC`, `Ticker`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('11', '12', 100.00, '121', '2022-06-30 10:10:53', 100, '美元');
INSERT INTO `stock` VALUES ('11', '13', 100.00, '121', '2022-06-30 10:11:09', 100, '美元');
INSERT INTO `stock` VALUES ('12', '11', 100.00, '122', '2022-06-30 10:11:22', 100, '火星');

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `trade_id` int NOT NULL COMMENT '交易id',
  `date` datetime NULL DEFAULT NULL COMMENT '交易时间',
  `client_side` enum('Buy','Sell') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易方式',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `size` int NULL DEFAULT NULL COMMENT '交易数量',
  `RIC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '股票代码',
  `rate_id` int NULL DEFAULT NULL COMMENT '交易的货币id',
  `HT/PT` enum('HT','PT') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易方式',
  `Salesperson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易员',
  `Ticker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '股票名称',
  PRIMARY KEY (`trade_id`) USING BTREE,
  INDEX `rate_id`(`rate_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `RIC`(`RIC`, `Ticker`) USING BTREE,
  CONSTRAINT `rate_id` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`rate_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `RIC` FOREIGN KEY (`RIC`, `Ticker`) REFERENCES `stock` (`RIC`, `Ticker`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trade
-- ----------------------------
INSERT INTO `trade` VALUES (1, '2022-06-30 10:12:42', 'Buy', 1, 50, '11', 1, 'HT', 'saler1', '12');
INSERT INTO `trade` VALUES (2, '2022-06-30 10:14:02', 'Sell', 1, 20, '11', 1, 'PT', 'saler1', '12');
INSERT INTO `trade` VALUES (3, '2022-06-30 10:17:11', 'Buy', 1, 30, '11', 1, 'HT', 'saler1', '12');
INSERT INTO `trade` VALUES (4, '2022-06-30 10:26:34', 'Buy', 2, 50, '11', 1, 'HT', 'saler1', '12');
INSERT INTO `trade` VALUES (5, '2022-06-30 11:35:15', 'Buy', 2, 20, '11', 2, 'HT', 'saler1', '13');
INSERT INTO `trade` VALUES (6, '2022-06-29 15:17:55', 'Buy', 1, 10, '11', 1, 'HT', 'saler1', '12');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `authority` int NULL DEFAULT NULL COMMENT '角色 0 为用户，1为交易员',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user1', NULL, NULL, NULL, 0);
INSERT INTO `users` VALUES (2, 'user2', NULL, NULL, NULL, 0);
INSERT INTO `users` VALUES (3, 'saler1', NULL, NULL, NULL, 1);

-- ----------------------------
-- View structure for stock_total_1d_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_1d_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_1d_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,date_format(`trade`.`date`,'%Y-%m-%d') AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,date_format(`trade`.`date`,'%Y-%m-%d');

-- ----------------------------
-- View structure for stock_total_1m_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_1m_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_1m_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,date_format(`trade`.`date`,'%Y-%m') AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,date_format(`trade`.`date`,'%Y-%m');

-- ----------------------------
-- View structure for stock_total_1w_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_1w_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_1w_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,date_format(`trade`.`date`,'%Y-%u') AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,date_format(`trade`.`date`,'%Y-%u');

-- ----------------------------
-- View structure for stock_total_2w_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_2w_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_2w_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,concat(date_format(`trade`.`date`,'%Y-'),floor((date_format(`trade`.`date`,'%u') / 2))) AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,concat(date_format(`trade`.`date`,'%Y-'),floor((date_format(`trade`.`date`,'%u') / 2)));

-- ----------------------------
-- View structure for stock_total_3m_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_3m_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_3m_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,concat(date_format(`trade`.`date`,'%Y-'),floor(((date_format(`trade`.`date`,'%m') + 2) / 3))) AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,concat(date_format(`trade`.`date`,'%Y'),floor(((date_format(`trade`.`date`,'%m') + 2) / 3)));

-- ----------------------------
-- View structure for stock_total_6m_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_6m_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_6m_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,concat(date_format(`trade`.`date`,'%Y-'),floor(((date_format(`trade`.`date`,'%m') + 5) / 6))) AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,concat(date_format(`trade`.`date`,'%Y'),floor(((date_format(`trade`.`date`,'%m') + 5) / 6)));

-- ----------------------------
-- View structure for stock_total_y_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_y_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_y_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,date_format(`trade`.`date`,'%Y') AS `dat`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) group by `trade`.`RIC`,`trade`.`Ticker`,date_format(`trade`.`date`,'%Y');

-- ----------------------------
-- View structure for stock_total_ytd_view
-- ----------------------------
DROP VIEW IF EXISTS `stock_total_ytd_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_total_ytd_view` AS select `trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,`rate`.`rate_id` AS `rate`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else 0 end)) AS `total_buy_size`,sum((case when (`trade`.`client_side` = 'sell') then `trade`.`size` else 0 end)) AS `total_sell_size` from ((`trade` join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) join `rate` on((`trade`.`rate_id` = `rate`.`rate_id`))) where (year(`trade`.`date`) = year(curdate())) group by `trade`.`RIC`,`trade`.`Ticker`;

-- ----------------------------
-- View structure for total_date_view
-- ----------------------------
DROP VIEW IF EXISTS `total_date_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `total_date_view` AS select sum(`stock_total_view`.`total_buy_size`) AS `total_buy_size`,sum(`stock_total_view`.`total_sell_size`) AS `total_sell_size`,(sum(`stock_total_view`.`total_buy_size`) - sum(`stock_total_view`.`total_sell_size`)) AS `quantity`,sum(((`stock_total_view`.`total_buy_size` * `rate`.`rate`) * `stock`.`price`)) AS `total_buy_notional`,sum(((`stock_total_view`.`total_sell_size` * `rate`.`rate`) * `stock`.`price`)) AS `total_sell_notional`,sum((((`stock_total_view`.`total_buy_size` - `stock_total_view`.`total_sell_size`) * `rate`.`rate`) * `stock`.`price`)) AS `quantity_notional` from ((`stock_total_view` join `rate`) join `stock`) where ((`stock_total_view`.`rate` = `rate`.`rate_id`) and (`stock_total_view`.`RIC` = `stock`.`RIC`) and (`stock_total_view`.`Ticker` = `stock`.`Ticker`));

-- ----------------------------
-- View structure for user_stock_view
-- ----------------------------
DROP VIEW IF EXISTS `user_stock_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_stock_view` AS select `users`.`user_id` AS `user_id`,`trade`.`RIC` AS `RIC`,`trade`.`Ticker` AS `Ticker`,sum((case when (`trade`.`client_side` = 'buy') then `trade`.`size` else (-(1) * `trade`.`size`) end)) AS `size` from ((`users` join `trade` on((`users`.`user_id` = `trade`.`user_id`))) join `stock` on(((`trade`.`RIC` = `stock`.`RIC`) and (`trade`.`Ticker` = `stock`.`Ticker`)))) group by `trade`.`RIC`,`trade`.`Ticker`,`trade`.`user_id`;

SET FOREIGN_KEY_CHECKS = 1;
