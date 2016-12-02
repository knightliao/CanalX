package com.knightliao.canalx.plugin.processor.kv.data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author knightliao
 * @date 2016/11/28 17:39
 */
public class CanalxKvDefaultImpl implements ICanalxKv {

    ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>(100);

    @Override
    public String get(String key) {
        return dataMap.get(key);
    }
}
