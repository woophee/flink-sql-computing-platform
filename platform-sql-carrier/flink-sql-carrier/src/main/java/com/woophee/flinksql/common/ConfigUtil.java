package com.woophee.flinksql.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
    private static Properties pro;
    private static final String CODING = "utf-8";
    private static final String CONFIG_FILE_PATH = "flink-sql-carrier.properties";

    private ConfigUtil() {
        throw new RuntimeException("Cannot Initialize the ConfigUtil");
    }

    /**
     * 使用静态代码块的好处是只加载一次 但是每次修改配置文件都要重新部署
     */
    static {
        pro = new Properties();
        ClassLoader loader = ConfigUtil.class.getClassLoader();
        InputStream in = loader.getResourceAsStream(CONFIG_FILE_PATH);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(in, CODING);
            pro.load(isr);
        } catch (Exception e) {
            LOG.error("Load " + CONFIG_FILE_PATH +" error!", e);
        } finally {
            try {
                in.close();
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                LOG.error("Close " + CONFIG_FILE_PATH +" error!", e);
            }
        }
    }

    public static String getStringValue(String key) {
        return pro.getProperty(key);
    }

}
