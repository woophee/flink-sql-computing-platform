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

To be continued...

