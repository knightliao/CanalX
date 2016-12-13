package com.github.knightliao.canalx.plugin.processor.kv.data.db.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.db.DbFetchControllerFactory;
import com.github.knightliao.canalx.db.IDbFetchController;
import com.github.knightliao.canalx.db.exception.CanalxSelectDbJsonInitException;
import com.github.knightliao.canalx.plugin.processor.kv.data.db.IDbStoreLoader;
import com.github.knightliao.canalx.plugin.processor.kv.data.db.TableKey;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;
import com.github.knightliao.canalx.plugin.processor.kv.data.support.CanalxKvConfig;

/**
 * @author knightliao
 * @date 2016/12/13 15:05
 */
public class DbStoreLoaderImpl implements IDbStoreLoader {

    protected final Logger LOGGER = LoggerFactory.getLogger(DbStoreLoaderImpl.class);

    protected IDbFetchController iDbFetchController = DbFetchControllerFactory.getDefaultDbController();

    protected TableKey tableKey = new TableKey();

    @Override
    public void init(CanalxKvConfig canalxKvConfig) throws CanalxSelectDbJsonInitException {

        iDbFetchController.init(canalxKvConfig.getDbLoaderFilePath());
    }

    @Override
    public void loadInitData(ICanalxKv iCanalxKv) throws CanalxSelectDbJsonInitException {

        Map<String, Map<String, String>> dataInitMap = iDbFetchController.getInitDbKv();

        for (String tableId : dataInitMap.keySet()) {

            Map<String, String> tableKvMap = dataInitMap.get(tableId);

            for (String key : tableKvMap.keySet()) {

                try {

                    iCanalxKv.put(tableId, key, tableKvMap.get(key));

                } catch (CanalxProcessorException e) {

                    LOGGER.error("cannot put {} {} {}", tableId, key, tableKvMap.get(key), e);
                }
            }

            String key = iDbFetchController.getTableKey(tableId);
            tableKey.setTableKey(tableId, key);

            LOGGER.info("tableId: {} , TableKey {} loads ok", tableId, tableKey);
        }
    }

    @Override
    public String getTableKey(String tableId) {

        return tableKey.getTableKey(tableId);
    }

    @Override
    public String executeRowSql(String tableId, String keyValue) {

        return iDbFetchController.getRowByExecuteSql(tableId, keyValue);
    }
}
