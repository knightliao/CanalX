package com.github.knightliao.canalx.plugin.processor.kv.data.store.impl;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.config.IConfig;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;

import lombok.Data;
import redis.clients.jedis.JedisPoolConfig;

/**

 */
@Data
public class CanalxCodisConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxCodisConfig.class);

    private int redisMaxActive = 8;
    private int redisMaxIdle = 8;
    private int redisMaxWait = 1000;
    private boolean redisTestOnBorrow = true;
    private String zkServer = "";
    private String zkPath = "";
    private int zkTimeout = 3000;

    private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private final static String CONFIG_FILE_NAME = "codis.properties";

    @Override
    public void init(ICanalxContext iCanalxContext) throws Exception {

        String codisFilePath = iCanalxContext.getProperty("canalx.plugin.processor.codis.filepath");
        loadConfigAndInit(codisFilePath);
    }

    @Override
    public void init() throws Exception {
    }

    private void loadConfigAndInit(String filePath) throws Exception {

        if (filePath == null) {
            filePath = CONFIG_FILE_NAME;
        }

        Properties properties = new Properties();
        URL url = CanalxCodisConfig.class.getClassLoader().getResource(filePath);
        if (url != null) {

            LOGGER.info("loading processor codis config file {}", url.toString());

            properties.load(new InputStreamReader(new FileInputStream(url.getPath()),
                    "utf-8"));

            redisMaxActive = Integer.parseInt(properties.getProperty("redis.maxActive", "3000"));
            redisMaxIdle = Integer.parseInt(properties.getProperty("redis.maxIdle", "50"));
            redisMaxWait = Integer.parseInt(properties.getProperty("redis.maxWait", "1000"));
            redisTestOnBorrow = Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow", "true"));

            zkServer = properties.getProperty("codis.zk.server");
            zkPath = properties.getProperty("codis.zk.path");
            zkTimeout = Integer.parseInt(properties.getProperty("codis.zk.timeout", "3000"));

            //
            jedisPoolConfig.setMaxIdle(redisMaxIdle);
            jedisPoolConfig.setMaxWaitMillis(redisMaxWait);
            jedisPoolConfig.setMaxTotal(redisMaxActive);
            jedisPoolConfig.setTestOnBorrow(redisTestOnBorrow);

        } else {

            LOGGER.warn("cannot find config file {}", CONFIG_FILE_NAME);
        }

    }
}
