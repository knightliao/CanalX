package com.knightliao.canalx.plugin.processor.kv.data;

/**
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public interface ICanalxKv {

    String get(String tableId, String key);

    void put(String tableId, String key, String value);
}
