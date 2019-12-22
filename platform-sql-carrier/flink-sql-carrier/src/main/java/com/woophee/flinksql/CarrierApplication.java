package com.woophee.flinksql;

import com.woophee.flinksql.service.cli.CliOptions;
import com.woophee.flinksql.service.cli.CliOptionsParser;
import com.woophee.flinksql.service.config.HttpRecipient;
import com.woophee.flinksql.service.sql.SqlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarrierApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CarrierApplication.class);
    private static final String SOURCE_SQL = "CREATE TABLE trade (\n" +
            "    userId VARCHAR,\n" +
            "    itemId VARCHAR,\n" +
            "    categoryId VARCHAR,\n" +
            "    behavior VARCHAR,\n" +
            "    `timestamp` BIGINT\n" +
            ") WITH (\n" +
            "    'connector.type' = 'kafka',\n" +
            "    'connector.version' = 'universal',\n" +
            "    'connector.topic' = 'trade',\n" +
            "    'connector.startup-mode' = 'earliest-offset',\n" +
            "    'connector.properties.0.key' = 'zookeeper.connect',\n" +
            "    'connector.properties.0.value' = '127.0.0.1:2181',\n" +
            "    'connector.properties.0.key' = 'bootstrap.servers',\n" +
            "    'connector.properties.0.value' = '127.0.0.1:9092',\n" +
            "    'update-mode' = 'append',\n" +
            "    'format.type' = 'json',\n" +
            "    'format.derive-schema' = 'true'\n" +
            ");\n";
    private static final String LOGIC_SQL ="INSERT INTO pvuv_sink\n" +
            "SELECT\n" +
            "  userId as user_id,\n" +
            "  itemId as item_id,\n" +
            "  categoryId as category_id,\n" +
            "  behavior,\n" +
            "  `timestamp` as ts\n" +
            "FROM trade;\n";
    private static final String SINK_SQL = "CREATE TABLE pvuv_sink (\n" +
            "  user_id VARCHAR,\n" +
            "  item_id VARCHAR,\n" +
            "  category_id VARCHAR,\n" +
            "  behavior VARCHAR,\n" +
            "  `ts` BIGINT\n" +
            ") WITH (\n" +
            "    'connector.type' = 'jdbc',\n" +
            "    'connector.url' = 'jdbc:mysql://127.0.0.1:3306/flink_sql_platform',\n" +
            "    'connector.table' = 'pvuv_sink',\n" +
            "    'connector.username' = 'root',\n" +
            "    'connector.password' = 'mysql',\n" +
            "    'connector.write.flush.max-rows' = '1'\n" +
            ");\n";

    public static void main(String[] args) throws Exception {
//        args = new String[]{"-j", "1"};
        final CliOptions options = CliOptionsParser.parseClient(args);
        HttpRecipient httpRecipient = new HttpRecipient(options.getJobId());
        String sql = httpRecipient.getSQl();
        String name = httpRecipient.getName();
        SqlExecutor sqlExecutor = new SqlExecutor(name, sql);
        sqlExecutor.execute();
    }
}
