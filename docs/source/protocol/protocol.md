 Kafka消息 Specification
=======

Kafka消息 specification, encoded using GSON library.

| 名字 | 类型 | 对应Java类型 | 意义 |
| ------| ------ | ------ | ---- |
| binlog | 字符串 | String | MYSQL的binlog文件和offset，格式为offset@binlog-file |
| time	| 64位整型 |	long |	以毫秒为单位的Unix时间戳，代表此binlog条目产生的时间 |
| canalTime	| 64位整型	| long	| 以毫秒为单位的Unix时间戳，代表binlog经过到达KafkaRiverCanal程序的时间，也可视为消息进入kafka的时间 | 
| db	| 字符串	| String | 	MYSQL一个数据库的schema名（一般情况下与数据库名一样，某些情况也可能不一样，请咨询相关数据库的DBA）| 
| table	| 字符串	| String | 	此条binlog影响的MYSQL的一个表的表名
| event| 	字符型| 	char| 	代表binlog事件类型，目前只解析出了insert, update & delete（对应的值为'i', 'u'和'd'）
| columns	| JSON数组	| `ArrayList<Object>`	| MYSQL表一个row的各个column，包含这次事件影响的数据，详见表二| 
| keys| 	JSON数组	| `ArrayList<String>`	| MYSQL keys| 

其中，columns数组的每个元素specification见表二。

| 名字 | 类型 | 对应Java类型 | 意义 |
| ------| ------ | ------ | ---- |
| v	| 字符串	| String	| value的缩写，即该列的值。对于insert，即插入的新值；对于update，即update之后的值；而对于delete，则为删除前的值
| updated	| 布尔	| boolean	| 本次事件该字段是否被更新了。仅对update事件有意义，对insert和delete一概为true，因此略去
| t	| 字符串	| String	| type的缩写，即这个列的MYSQL数据类型。例如decimal(10,4)
| origin_val	| 字符串	| String	| origin_value的缩写，该列更新之前的旧值。仅对update事件，且updated为true的字段有效。
| null	| 布尔	| boolean	| 该字段的值是否为null
| n	| 字符串	| String	| name的缩写，即MYSQL表的一个列的列名

INSERT消息样例：

	{ 
	"binlog": "6816@mysql-bin.000070", 
	"time": 1450235092000,
	"canalTime": 1450235093370,
	"db": "TestCanal", 
	"table": "g_order_010",
	"event": "i",
	"columns": [ 
	{ "n": "order_id", "t": "bigint(20)", "v": "126", "null": false }, 
	{ "n": "x_id", "t": "bigint(20)", "v": "123456", "null": false }, 
	{ "n": "phone", "t": "varchar(15)", "v": "13264494028", "null": false }, 
	{ "n": "time", "t": "timestamp", "v": "2015-08-10 13:08:13", "null": false }
	],
	"keys": [ "order_id" ] }

UPDATE消息样例：

	{
	"binlog": "25521@mysql-bin.000070",
	"time": 1450236307000,
	"canalTime": 1450236308279,
	"db": "TestCanal",
	"table": "g_order_010",
	"event": "u",
	"columns": [
	{"n": "order_id", "t": "bigint(20)", "v": "126", "null": false, "updated": false},
	{"n": "x_id", "t": "bigint(20)", "v": "123456", "null": false, "updated": false},
	{ "n": "name", "t": "varchar(100)", "v": "小春", "origin_val": "小明", "null": false, "updated": true}
	],
	"keys": ["order_id"]
	}

DELETE消息样例：

	{
	"binlog": "58851@mysql-bin.000070",
	"time": 1450237034000,
	"canalTime": 1450237034492,
	"db": "TestCanal",
	"table": "g_order_010",
	"event": "d",
	"columns": [
	{"n": "order_id", "t": "bigint(20)", "v": "126", "null": false},
	{"n": "x_id", "t": "bigint(20)", "v": "123456", "null": false},
	{"n": "phone", "t": "varchar(15)", "v": "13264494028", "null": false}
	],
	"keys": ["order_id"]
	}
	


