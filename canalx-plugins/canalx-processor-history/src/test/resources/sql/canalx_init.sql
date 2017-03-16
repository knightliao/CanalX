DROP TABLE IF EXISTS `primary_data`;
CREATE TABLE `primary_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户登录名',
  `user_ip` varchar(255) DEFAULT NULL COMMENT '用户登录ip',
  `trace_id` varchar(255) DEFAULT NULL COMMENT 'kepler trace id',
  `xid` varchar(20) DEFAULT NULL COMMENT '前一个事务ID',
  `status` int(11) DEFAULT '0' COMMENT '该记录状态',
  `log_time` datetime DEFAULT NULL COMMENT 'binlog条目产生的时间',
  `canal_time` datetime DEFAULT NULL COMMENT 'binlog经过到达KafkaRiverCanal程序的时间',
  `binlog` varchar(255) DEFAULT NULL COMMENT 'MYSQL的binlog文件和offset',
  `db_name` varchar(255) DEFAULT NULL COMMENT '数据库名',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `table_event` varchar(255) DEFAULT NULL COMMENT '变更类型',
  `table_columns` text COMMENT '变更列详情',
  `table_keys` varchar(255) DEFAULT NULL COMMENT '表的主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ;