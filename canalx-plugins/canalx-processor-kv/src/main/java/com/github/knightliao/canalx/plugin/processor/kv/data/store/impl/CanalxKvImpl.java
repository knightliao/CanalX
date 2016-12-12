package com.github.knightliao.canalx.plugin.processor.kv.data.store.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;

/**
 * @author knightliao
 * @date 2016/11/28 17:39
 */
public class CanalxKvImpl implements ICanalxKv {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvImpl.class);

    ConcurrentMap<String, ConcurrentMap<String, String>> dataMap = new ConcurrentHashMap<>(100);

    public static Builder create() {
        return new Builder();
    }

    private CanalxKvImpl() {
    }

    /**
     * Builder
     */
    public static final class Builder {

        private Builder() {
        }

        private void validate() {
        }

        /**
         */
        public ICanalxKv build() {
            return new CanalxKvImpl();
        }
    }

    @Override
    public String get(String tableId, String key) throws CanalxProcessorException {

        if (dataMap.keySet().contains(tableId)) {
            return dataMap.get(tableId).get(key);
        } else {

            return null;
        }
    }

    @Override
    public void put(String tableId, String key, String value) throws CanalxProcessorException {

        if (dataMap.keySet().contains(tableId)) {

            dataMap.get(tableId).put(key, value);

        } else {

            ConcurrentMap<String, String> currentMap = new ConcurrentHashMap<String, String>();
            currentMap.put(key, value);
            dataMap.put(tableId, currentMap);
        }
    }

    @Override
    public void shutdown() {

        dataMap.clear();
    }

}
