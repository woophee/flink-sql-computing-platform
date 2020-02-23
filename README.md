# Flink SQL computing platform

Give the project a star to support me! Thank you!

## Overview
This is the home page for the platform. The page show some mocking data.

![](http://file.woophee.com/github/flink_sql_computing_platform/home.png)

Create your job group, which helps you manage your same kind job.

![](http://file.woophee.com/github/flink_sql_computing_platform/group.png)

Create your job in the selected group.

![](http://file.woophee.com/github/flink_sql_computing_platform/add_job.png)

List all your defined jobs in this page.

![](http://file.woophee.com/github/flink_sql_computing_platform/job.png)

## How to build
Step one:

Initial the mysql database by the script "flink-sql-platform.sql"

Step two:

Check all properties files to change the mocking address to the real address.

And then you can run the following command for the "platform-server" module and the "platform-sql-carrier" module:

```
mvn clean package -Dmaven.test.skip=true
```

And then you can run the following command for the "platform-web" module:

```
npm run serve
```

Step three:

Upload the platform-sql-carrier.jar to your flink cluster, whose version need to be greate than 1.9.0. 

Step four:

Just use it.

## Example SQL code

```sql
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
FROM trade where itemId = '9';

CREATE TABLE pvuv_sink (
user_id VARCHAR,
item_id VARCHAR,
category_id VARCHAR,
behavior VARCHAR,
`ts` BIGINT
) WITH (
'connector.type' = 'jdbc',
'connector.url' = 'jdbc:mysql://127.0.0.1:3306/flink_sql_computing_platform',
'connector.table' = 'pvuv_sink',
'connector.username' = 'root',
'connector.password' = 'mysql',
'connector.write.flush.max-rows' = '1'
);
```

