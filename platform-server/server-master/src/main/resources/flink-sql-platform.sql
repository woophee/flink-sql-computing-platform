-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.27 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  10.2.0.5698
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 flink_sql_platform 的数据库结构
CREATE DATABASE IF NOT EXISTS `flink_sql_platform` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `flink_sql_platform`;

-- 导出  表 flink_sql_platform.define_group 结构
CREATE TABLE IF NOT EXISTS `define_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` enum('REALTIME','SCHEDULED') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- 正在导出表  flink_sql_platform.define_group 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `define_group` DISABLE KEYS */;
INSERT INTO `define_group` (`id`, `name`, `type`) VALUES
	(3, 'group1', 'REALTIME');
/*!40000 ALTER TABLE `define_group` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.define_job 结构
CREATE TABLE IF NOT EXISTS `define_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('REALTIME','SCHEDULED') NOT NULL DEFAULT 'REALTIME',
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- 正在导出表  flink_sql_platform.define_job 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `define_job` DISABLE KEYS */;
INSERT INTO `define_job` (`id`, `type`, `name`) VALUES
	(1, 'REALTIME', 'kafka_mysql'),
	(2, 'REALTIME', 'run_mysql'),
	(3, 'REALTIME', 'rum_groupby');
/*!40000 ALTER TABLE `define_job` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.define_realtime_job 结构
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

-- 正在导出表  flink_sql_platform.define_realtime_job 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `define_realtime_job` DISABLE KEYS */;
INSERT INTO `define_realtime_job` (`id`, `name`, `group_id`, `sql_text`, `parallelism`) VALUES
	(1, 'kafka_mysql', 3, 'CREATE TABLE trade (\r\nuserId VARCHAR,\r\nitemId VARCHAR,\r\ncategoryId VARCHAR,\r\nbehavior VARCHAR,\r\n`timestamp` BIGINT\r\n) WITH (\r\n\'connector.type\' = \'kafka\',\r\n\'connector.version\' = \'universal\',\r\n\'connector.topic\' = \'trade\',\r\n\'connector.startup-mode\' = \'earliest-offset\',\r\n\'connector.properties.0.key\' = \'zookeeper.connect\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:2181\',\r\n\'connector.properties.0.key\' = \'bootstrap.servers\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:9092\',\r\n\'update-mode\' = \'append\',\r\n\'format.type\' = \'json\',\r\n\'format.derive-schema\' = \'true\'\r\n);\r\n\r\nINSERT INTO pvuv_sink\r\nSELECT\r\nuserId as user_id,\r\nitemId as item_id,\r\ncategoryId as category_id,\r\nbehavior,\r\n`timestamp` as ts\r\nFROM trade where itemId = \'9\';\r\n\r\nCREATE TABLE pvuv_sink (\r\nuser_id VARCHAR,\r\nitem_id VARCHAR,\r\ncategory_id VARCHAR,\r\nbehavior VARCHAR,\r\n`ts` BIGINT\r\n) WITH (\r\n\'connector.type\' = \'jdbc\',\r\n\'connector.url\' = \'jdbc:mysql://127.0.0.1:3306/flink_sql_platform\',\r\n\'connector.table\' = \'pvuv_sink\',\r\n\'connector.username\' = \'root\',\r\n\'connector.password\' = \'mysql\',\r\n\'connector.write.flush.max-rows\' = \'1\'\r\n);', 1),
	(2, 'run_mysql', 3, 'CREATE TABLE rum_source (\r\nappId VARCHAR,\r\ndata VARCHAR,\r\ndeviceId VARCHAR,\r\nmessageType VARCHAR,\r\nsampleRate VARCHAR,\r\nsecretId VARCHAR,\r\nsessionId VARCHAR,\r\nts TIMESTAMP\r\n) WITH (\r\n\'connector.type\' = \'kafka\',\r\n\'connector.version\' = \'universal\',\r\n\'connector.topic\' = \'rum\',\r\n\'connector.startup-mode\' = \'latest-offset\',\r\n\'connector.properties.0.key\' = \'zookeeper.connect\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:2181\',\r\n\'connector.properties.0.key\' = \'bootstrap.servers\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:9092\',\r\n\'update-mode\' = \'append\',\r\n\'format.type\' = \'json\',\r\n\'format.derive-schema\' = \'true\'\r\n);\r\n\r\nINSERT INTO rum_sink\r\nSELECT\r\nappId as app_id,\r\ndata,\r\ndeviceId as device_id,\r\nmessageType as message_type,\r\nsampleRate as sample_rate,\r\nsecretId as secret_id,\r\nsessionId as session_id,\r\nts\r\nFROM rum_source where messageType = \'LB_SPA_INFO\';\r\n\r\nCREATE TABLE rum_sink (\r\napp_id VARCHAR,\r\ndata VARCHAR,\r\ndevice_id VARCHAR,\r\nmessage_type VARCHAR,\r\nsample_rate VARCHAR,\r\nsecret_id VARCHAR,\r\nsession_id VARCHAR,\r\nts TIMESTAMP\r\n) WITH (\r\n\'connector.type\' = \'jdbc\',\r\n\'connector.url\' = \'jdbc:mysql://127.0.0.1:3306/flink_sql_platform\',\r\n\'connector.table\' = \'rum_sink\',\r\n\'connector.username\' = \'root\',\r\n\'connector.password\' = \'mysql\',\r\n\'connector.write.flush.max-rows\' = \'1\'\r\n);', 1),
	(3, 'rum_groupby', 3, 'CREATE TABLE rum_source (\r\nappId VARCHAR,\r\ndata VARCHAR,\r\ndeviceId VARCHAR,\r\nmessageType VARCHAR,\r\nsampleRate VARCHAR,\r\nsecretId VARCHAR,\r\nsessionId VARCHAR,\r\nts TIMESTAMP\r\n) WITH (\r\n\'connector.type\' = \'kafka\',\r\n\'connector.version\' = \'universal\',\r\n\'connector.topic\' = \'rum\',\r\n\'connector.startup-mode\' = \'latest-offset\',\r\n\'connector.properties.0.key\' = \'zookeeper.connect\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:2181\',\r\n\'connector.properties.0.key\' = \'bootstrap.servers\',\r\n\'connector.properties.0.value\' = \'127.0.0.1:9092\',\r\n\'update-mode\' = \'append\',\r\n\'format.type\' = \'json\',\r\n\'format.derive-schema\' = \'true\'\r\n);\r\n\r\nINSERT INTO pvuv_sink\r\nSELECT\r\nappId as app_id,\r\nDATE_FORMAT(ts, \'yyyy-MM-dd HH:00\') ts,\r\nCOUNT(*) AS av\r\nFROM rum_source where messageType = \'LB_SPA_INFO\' GROUP BY DATE_FORMAT(ts, \'yyyy-MM-dd HH:00\'),appId;\r\n\r\nCREATE TABLE pvuv_sink (\r\napp_id VARCHAR,\r\nts VARCHAR,\r\nav 6+BIGINT\r\n) WITH (\r\n\'connector.type\' = \'jdbc\',\r\n\'connector.url\' = \'jdbc:mysql://127.0.0.1:3306/flink_sql_platform\',\r\n\'connector.table\' = pvuv_sink \r\n\'connector.username\' = \'root\',\r\n\'connector.password\' = \'mysql\',\r\n\'connector.write.flush.max-rows\' = \'1\'\r\n);', 1);
/*!40000 ALTER TABLE `define_realtime_job` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.define_scheduled_job 结构
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

-- 正在导出表  flink_sql_platform.define_scheduled_job 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `define_scheduled_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `define_scheduled_job` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.flink_cluster 结构
CREATE TABLE IF NOT EXISTS `flink_cluster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- 正在导出表  flink_sql_platform.flink_cluster 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `flink_cluster` DISABLE KEYS */;
INSERT INTO `flink_cluster` (`id`, `name`, `address`) VALUES
	(1, 'hz-flink-1.9.0', 'http://127.0.0.1:8081');
/*!40000 ALTER TABLE `flink_cluster` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.pvuv_sink 结构
CREATE TABLE IF NOT EXISTS `pvuv_sink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL,
  `ts` varchar(50) DEFAULT NULL,
  `av` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- 正在导出表  flink_sql_platform.pvuv_sink 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `pvuv_sink` DISABLE KEYS */;
INSERT INTO `pvuv_sink` (`id`, `app_id`, `ts`, `av`) VALUES
	(1, '1', '1', 1);
/*!40000 ALTER TABLE `pvuv_sink` ENABLE KEYS */;

-- 导出  表 flink_sql_platform.rum_sink 结构
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

-- 正在导出表  flink_sql_platform.rum_sink 的数据：~22 rows (大约)
/*!40000 ALTER TABLE `rum_sink` DISABLE KEYS */;
INSERT INTO `rum_sink` (`id`, `app_id`, `data`, `device_id`, `message_type`, `sample_rate`, `secret_id`, `session_id`, `ts`) VALUES
	(36, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV7', 'uuid', '2019-11-17 16:21:27'),
	(37, 'PV-8', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV6', 'uuid', '2019-11-17 16:21:27'),
	(38, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV7', 'uuid', '2019-11-17 16:21:27'),
	(39, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV10', 'uuid', '2019-11-17 16:21:28'),
	(40, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV8', 'uuid', '2019-11-17 16:21:28'),
	(41, 'PV-5', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV1', 'uuid', '2019-11-17 16:21:28'),
	(42, 'PV-6', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV1', 'uuid', '2019-11-17 16:21:28'),
	(43, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 16:21:28'),
	(44, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV10', 'uuid', '2019-11-17 16:21:28'),
	(45, 'PV-10', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:21'),
	(46, 'PV-7', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:21'),
	(47, 'PV-6', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV5', 'uuid', '2019-11-17 17:13:21'),
	(48, 'PV-2', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV8', 'uuid', '2019-11-17 17:13:21'),
	(49, 'PV-6', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV1', 'uuid', '2019-11-17 17:13:21'),
	(50, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV2', 'uuid', '2019-11-17 17:13:21'),
	(51, 'PV-10', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV2', 'uuid', '2019-11-17 17:13:22'),
	(52, 'PV-8', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:22'),
	(53, 'PV-10', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV6', 'uuid', '2019-11-17 17:13:24'),
	(54, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV5', 'uuid', '2019-11-17 17:13:25'),
	(55, 'PV-10', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV3', 'uuid', '2019-11-17 17:13:25'),
	(56, 'PV-1', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV7', 'uuid', '2019-11-17 17:13:25'),
	(57, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:25'),
	(58, 'PV-9', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV5', 'uuid', '2019-11-17 17:13:25'),
	(59, 'PV-8', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV5', 'uuid', '2019-11-17 17:13:25'),
	(60, 'PV-7', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV8', 'uuid', '2019-11-17 17:13:29'),
	(61, 'PV-6', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV3', 'uuid', '2019-11-17 17:13:29'),
	(62, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV7', 'uuid', '2019-11-17 17:13:30'),
	(63, 'PV-7', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:30'),
	(64, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:13:30'),
	(65, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV2', 'uuid', '2019-11-17 17:13:55'),
	(66, 'PV-2', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV10', 'uuid', '2019-11-17 17:13:55'),
	(67, 'PV-8', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV8', 'uuid', '2019-11-17 17:13:55'),
	(68, 'PV-1', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV10', 'uuid', '2019-11-17 17:13:55'),
	(69, 'PV-4', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV6', 'uuid', '2019-11-17 17:13:55'),
	(70, 'PV-8', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV7', 'uuid', '2019-11-17 17:19:18'),
	(71, 'PV-3', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV8', 'uuid', '2019-11-17 17:19:19'),
	(72, 'PV-7', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV4', 'uuid', '2019-11-17 17:19:19'),
	(73, 'PV-2', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV6', 'uuid', '2019-11-17 17:19:19'),
	(74, 'PV-6', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV9', 'uuid', '2019-11-17 17:19:20'),
	(75, 'PV-10', '', 'uuid', 'LB_SPA_INFO', '3000', 'PV9', 'uuid', '2019-11-17 17:19:33');
/*!40000 ALTER TABLE `rum_sink` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
