package com.github.knightliao.canalx.plugin.processor.kv.data.store;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;

/**
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public interface ICanalxKv {

    String get(String tableId, String key) throws CanalxProcessorException;

    void put(String tableId, String key, String value) throws CanalxProcessorException;

    void delete(String tableId, String key) throws CanalxProcessorException;

    void shutdown();

}
