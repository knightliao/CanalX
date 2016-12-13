package com.github.knightliao.canalx.plugin.processor.kv.data.db;

import com.github.knightliao.canalx.db.exception.CanalxSelectDbJsonInitException;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;
import com.github.knightliao.canalx.plugin.processor.kv.data.support.CanalxKvConfig;

/**
 * @author knightliao
 * @date 2016/12/13 15:05
 */
public interface IDbStoreLoader {

    void init(CanalxKvConfig canalxKvConfig) throws CanalxSelectDbJsonInitException;

    void loadInitData(ICanalxKv iCanalxKv) throws CanalxSelectDbJsonInitException;

    String getTableKey(String tableId);
}
