package com.knightliao.canalx.plugin.processor.kv.data.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.plugin.processor.kv.data.ICanalxKv;

/**
 * @author knightliao
 * @date 2016/11/28 17:39
 */
public class CanalxKvDefaultImpl implements ICanalxKv {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvDefaultImpl.class);

    ConcurrentMap<String, ConcurrentMap<String, String>> dataMap = new ConcurrentHashMap<>(100);

    @Override
    public String get(String tableId, String key) {

        if (dataMap.keySet().contains(tableId)) {
            return dataMap.get(tableId).get(key);
        } else {

            return null;
        }
    }

    @Override
    public void put(String tableId, String key, String value) {

        if (dataMap.keySet().contains(tableId)) {

            dataMap.get(tableId).put(key, value);

        } else {

            ConcurrentMap<String, String> currentMap = new ConcurrentHashMap<String, String>();
            currentMap.put(key, value);
            dataMap.put(tableId, currentMap);
        }
    }
}
