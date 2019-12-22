CREATE TABLE trade (
userId VARCHAR,
itemId VARCHAR,
categoryId VARCHAR,
behavior VARCHAR,
`timestamp` BIGINT
) WITH (
'connector.type' = 'kafka',
'connector.version' = 'universal',
'connector.topic' = 'trade',
'connector.startup-mode' = 'earliest-offset',
'connector.properties.0.key' = 'zookeeper.connect',
'connector.properties.0.value' = '127.0.0.1:2181',
'connector.properties.0.key' = 'bootstrap.servers',
'connector.properties.0.value' = '127.0.0.1:9092',
'update-mode' = 'append',
'format.type' = 'json',
'format.derive-schema' = 'true'
);

INSERT INTO pvuv_sink
SELECT
userId as user_id,
itemId as item_id,
categoryId as category_id,
behavior,
`timestamp` as ts
FROM trade;

CREATE TABLE pvuv_sink (
user_id VARCHAR,
item_id VARCHAR,
category_id VARCHAR,
behavior VARCHAR,
`ts` BIGINT
) WITH (
'connector.type' = 'jdbc',
'connector.url' = 'jdbc:mysql://127.0.0.1:3306/flink_sql_platform',
'connector.table' = 'pvuv_sink',
'connector.username' = 'root',
'connector.password' = 'mysql',
'connector.write.flush.max-rows' = '1'
);