
## CanalX

基于 `Canal` 的数据感知服务框架. 可用于围绕数据库`Mysql`进行数据相关的各式业务开发, 并建立各式各样的服务平台。例如:

- 热点数据缓存KV服务
- 用户操作日志服务
- 数据变更通知服务

`CanalX`是一个框架, 采用插件式开发, 可扩展性极强. 目前(以及短期)的插件有:

- 数据导入功能(injector):
	- 支持 `Canal`->`Kafka`->`CanalX` 的数据导入方案。(已发布, 插件 `canalx-injector-kafka`)
	- 支持 `Canal`->`CanalX` 的数据导入方案。(开发中, 插件 `canalx-injector-canal`)
- 数据处理功能(processor):
	- `Data`->`KV`。将变更Data数据转化成全内存的`KV`存储。(已发布, 插件 `canalx-processor-kv`)
	- `Data`->`Redis`。将变更Data数据转化成`Redis`中间件存储。(开发中, 插件 `canalx-processor-redis`)
	- `Data`->`History`。将变更Data数据转化成`History`存储。(开发中, 插件 `canalx-processor-history`)
	- `Data`->`Notification`。将变更Data数据转化成`Notification`存储。(开发中, 插件 `canalx-processor-notification`)
- 数据发布功能(router):
	- `web-rest`发布。将processor中的数据以 `web-rest` 接口的方式。(已发布, 插件 `canalx-router-rest`)
	- `dubbo`发布。将processor中的数据以 `dubbo-rpc` 接口的方式(开发中, )
	- `kepler`发布。将processor中的数据以 `kepler-rpc` 接口的方式(开发中, )
