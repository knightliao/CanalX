CanalX 
=======

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/knightliao/CanalX.svg?branch=master)](https://travis-ci.org/knightliao/CanalX) 
[![Coverage Status](https://coveralls.io/repos/github/knightliao/CanalX/badge.svg?branch=master)](https://coveralls.io/github/knightliao/CanalX?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx/canalx/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.github.knightliao.canalx/canalx)


docs: http://canalx.readthedocs.io/en/latest/

基于 `Canal` 的数据感知服务框架. 可用于围绕数据库`Mysql`进行数据相关的各式业务开发, 并建立各式各样的服务平台。例如:

- 热点数据缓存KV服务
- 用户操作日志服务
- 数据变更通知服务
- 搜索服务

`CanalX`是一个框架, 采用插件式开发, 可扩展性极强. 目前(以及短期)的插件有:

- 数据导入功能(injector):
	- 支持 `Canal`->`Kafka`->`CanalX` 的数据导入方案。(已发布, 插件 `canalx-injector-kafka`)
	- 支持 `Canal`->`CanalX` 的数据导入方案。(开发中, 插件 `canalx-injector-canal`)
- 数据处理功能(processor):
	- `Data`->`kv`。将变更Data数据转化成全内存的`kv`存储。(已发布, 插件 `canalx-processor-kv`)
	- `Data`->`codis`。将变更Data数据转化成`Codis(redis的某种实现)`中间件存储。(开发中, 插件 `canalx-processor-codis`)
	- `Data`->`History`。将变更Data数据转化成`History`存储。(开发中, 插件 `canalx-processor-history`)
	- `Data`->`Notification`。将变更Data数据转化成`Notification`存储。(开发中, 插件 `canalx-processor-notification`)
	- `Data`->`ElasticSearch`。将变更Data数据转化成`ElasticSearch`搜索服务。(开发中, 插件 `canalx-processor-es`)
- 数据发布功能(router):
	- `web-rest`发布。将processor中的数据以 `web-rest` 接口的方式。(已发布, 插件 `canalx-router-rest`)
	- `dubbo`发布。将processor中的数据以 `dubbo-rpc` 接口的方式(开发中, 插件 `canalx-router-dubbo`)
	- `kepler`发布。将processor中的数据以 `kepler-rpc` 接口的方式(开发中, 插件 `canalx-router-kepler`)

### main dependency

- canalx-select-db-json - Fetch MYSQL's data to K-V style data.  http://github.com/knightliao/canalx-select-db-json

## 推荐

- 有态度无广告的搜索引擎: https://www.sov5.com
- 微读 - 高品质阅读: http://www.100weidu.com
- Python中国社区: http://www.python88.com
- Disconf - 分布式配置管理平台: http://github.com/knightliao/disconf
- CanalX - 基于 `Canal` 的数据感知服务框架: http://github.com/knightliao/canalX
- jutf - Java Unit Test Framework: https://github.com/knightliao/jutf

