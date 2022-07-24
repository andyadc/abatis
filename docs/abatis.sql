DROP
DATABASE
IF
	EXISTS `abatis`;
CREATE
DATABASE `abatis` DEFAULT CHARACTER
SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE
`abatis`;

SET
FOREIGN_KEY_CHECKS = 0;
DROP TABLE
    IF
    EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          BIGINT ( 20 ) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        VARCHAR(128) NOT NULL COMMENT 'name',
    `age`         TINYINT ( 4 ) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'age',
    `status`      TINYINT ( 4 ) NOT NULL DEFAULT '0' COMMENT 'status',
    `birthday`    DATE                  DEFAULT NULL COMMENT 'birthday',
    `version`     INT ( 11 ) NOT NULL DEFAULT '1' COMMENT 'version',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_name` ( `name` ) USING BTREE,
    KEY           `idx_create_time` ( `create_time` )
) ENGINE = INNODB
    AUTO_INCREMENT = 1000
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'user';

DELETE
FROM t_user;
INSERT INTO t_user (`name`, `age`, `status`, `birthday`, `version`)
VALUES ('adc', 99, 1, SYSDATE(), 1);
