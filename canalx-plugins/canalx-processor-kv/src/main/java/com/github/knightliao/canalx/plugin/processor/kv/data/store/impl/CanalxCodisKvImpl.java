package com.github.knightliao.canalx.plugin.processor.kv.data.store.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author knightliao
 * @date 2016/12/12 17:17
 */
public class CanalxCodisKvImpl implements ICanalxKv {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxCodisKvImpl.class);

    private String redisPrefix = "";

    //
    private static CanalxCodisConfig processorCodisConfig = new CanalxCodisConfig();

    private JedisResourcePool jedisPool;

    private CanalxCodisKvImpl(JedisPoolConfig poolConfig, String zkAddr, String zkProxyDir, int zkTimeout,
                              String redisPrefix) {
        this.redisPrefix = redisPrefix;
        jedisPool = RoundRobinJedisPool.create()
                .curatorClient(zkAddr, zkTimeout).zkProxyDir(zkProxyDir).poolConfig(poolConfig).build();
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder
     */
    public static final class Builder {

        private String zkProxyDir;
        private String zkAddr;
        private int zkTimeout;
        private String redisPrefix = "__canalx__";
        ;
        private JedisPoolConfig jedisPoolConfig;

        private Builder() {
        }

        /**
         * Set jedis pool config.
         */
        public Builder poolConfig(JedisPoolConfig poolConfig) {
            this.jedisPoolConfig = poolConfig;
            return this;
        }

        public Builder zkSet(String zkAddr, String zkProxyDir, int zkTimeout) {
            this.zkAddr = zkAddr;
            this.zkProxyDir = zkProxyDir;
            this.zkTimeout = zkTimeout;
            return this;
        }

        public Builder redisPrefix(String redisPrefix) {
            this.redisPrefix = redisPrefix;
            return this;
        }

        private void validate() {
        }

        /**
         */
        public ICanalxKv build() {
            validate();
            return new CanalxCodisKvImpl(jedisPoolConfig, zkAddr, zkProxyDir, zkTimeout, redisPrefix);
        }
    }

    /**
     * @param tableId
     * @param key
     *
     * @return
     *
     * @throws CanalxProcessorException
     */
    @Override
    public String get(String tableId, String key) throws CanalxProcessorException {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();

            String hKey = redisPrefix + tableId;
            return jedis.hget(hKey, key);

        } catch (Exception e) {

            throw new CanalxProcessorException(e);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param tableId
     * @param key
     * @param value
     *
     * @throws CanalxProcessorException
     */
    @Override
    public void put(String tableId, String key, String value) throws CanalxProcessorException {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();

            String hKey = redisPrefix + tableId;
            jedis.hset(hKey, key, value);

        } catch (Exception e) {

            throw new CanalxProcessorException(e);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }

    }

    @Override
    public void shutdown() {

        if (jedisPool != null) {
            try {
                jedisPool.close();
            } catch (IOException e) {

                LOGGER.error(e.toString());
            }
        }
    }

    public static ICanalxKv getInstance(ICanalxContext iCanalxContext) throws CanalxProcessorException {

        try {

            processorCodisConfig.init(iCanalxContext);
            return CanalxCodisKvImpl.create().poolConfig(processorCodisConfig.getJedisPoolConfig())
                    .zkSet(processorCodisConfig.getZkServer(), processorCodisConfig.getZkPath(), processorCodisConfig
                            .getZkTimeout()).build();
        } catch (Exception e) {
            throw new CanalxProcessorException(e);
        }

    }
}
