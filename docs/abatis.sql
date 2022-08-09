DROP DATABASE IF EXISTS `abatis`;

CREATE DATABASE `abatis` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `abatis`;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        VARCHAR(128)        NOT NULL COMMENT '名称',
    `age`         TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '年龄',
    `status`      TINYINT(4)          NOT NULL DEFAULT '0' COMMENT '状态',
    `birthday`    DATE                         DEFAULT NULL COMMENT '生日',
    `version`     INT(11)             NOT NULL DEFAULT '1' COMMENT '版本号',
    `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_name` (`name`) USING BTREE,
    KEY `idx_create_time` (`create_time`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = 'user';

-- ----------------------------
-- Table structure for activity
-- ----------------------------

DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `activity_id`   BIGINT(20)          NOT NULL COMMENT '活动ID',
    `activity_name` VARCHAR(128)        NOT NULL COMMENT '活动名称',
    `activity_desc` VARCHAR(512)        NULL     DEFAULT '' COMMENT '活动描述',
    `status`        TINYINT(4)          NOT NULL DEFAULT '0' COMMENT '状态',
    `version`       INT(11)             NOT NULL DEFAULT '1' COMMENT '版本号',
    `create_time`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_activity_id` (`activity_id`) USING BTREE,
    KEY `idx_create_time` (`create_time`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = 'activity';

-- ----------------------------
-- data
-- ----------------------------

TRUNCATE t_user;
TRUNCATE t_activity;

BEGIN;

INSERT INTO t_user (`name`, `age`, `status`, `birthday`, `version`)
VALUES ('adc', 99, 1, SYSDATE(), 1);

INSERT INTO `t_activity` (`activity_id`, `activity_name`, `activity_desc`, `status`)
VALUES (100001, '活动名', '测试活动', 1);

COMMIT;
