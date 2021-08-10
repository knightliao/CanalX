package com.github.knightliao.canalx.plugin.processor.kv.data.store.impl;

import java.io.InputStream;
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
public class CanalxKvStoreCodisConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvStoreCodisConfig.class);

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

        // 默认是 codis.properties
        String codisFilePath = iCanalxContext.getProperty("canalx.plugin.processor.codis.filepath", CONFIG_FILE_NAME);
        loadConfigAndInit(codisFilePath);
    }

    @Override
    public void init() throws Exception {
    }

    private void loadConfigAndInit(String filePath) throws Exception {

        Properties properties = new Properties();

        InputStream inputStream = CanalxKvStoreCodisConfig.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream != null) {

            properties.load(inputStream);

            LOGGER.info("loading processor codis config file {}", filePath);

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

            throw new Exception("cannot find config file " + filePath);
        }

    }
}
