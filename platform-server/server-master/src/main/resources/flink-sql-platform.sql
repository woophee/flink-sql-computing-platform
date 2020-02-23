CREATE DATABASE IF NOT EXISTS `flink_sql_platform` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `flink_sql_platform`;

CREATE TABLE IF NOT EXISTS `define_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` enum('REALTIME','SCHEDULED') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `define_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('REALTIME','SCHEDULED') NOT NULL DEFAULT 'REALTIME',
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `define_realtime_job` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `name` varchar(50) NOT NULL,
  `group_id` int(11) NOT NULL,
  `sql_text` varchar(60000) NOT NULL,
  `parallelism` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `FK_define_realtime_job_define_job` FOREIGN KEY (`id`) REFERENCES `define_job` (`id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `define_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `define_scheduled_job` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT 'job的名字',
  `group_id` int(11) NOT NULL,
  `sql_text` varchar(60000) NOT NULL,
  `parallelism` int(11) NOT NULL DEFAULT '1',
  `scheduled_rule` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_define_scheduled_job_define_group` (`group_id`),
  CONSTRAINT `FK_define_scheduled_job_define_group` FOREIGN KEY (`group_id`) REFERENCES `define_group` (`id`),
  CONSTRAINT `FK_define_scheduled_job_define_job` FOREIGN KEY (`id`) REFERENCES `define_job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `flink_cluster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `pvuv_sink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL,
  `ts` varchar(50) DEFAULT NULL,
  `av` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `rum_sink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL,
  `data` varchar(50) DEFAULT NULL,
  `device_id` varchar(50) DEFAULT NULL,
  `message_type` varchar(50) DEFAULT NULL,
  `sample_rate` varchar(50) DEFAULT NULL,
  `secret_id` varchar(50) DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;


