package com.github.knightliao.canalx.plugin.processor.kv.data.support;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.db.DbFetchControllerFactory;
import com.github.knightliao.canalx.db.IDbFetchController;
import com.github.knightliao.canalx.db.exception.CanalxSelectDbJsonInitException;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;

/**

 */
public class DbStoreLoaderUtils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DbStoreLoaderUtils.class);

    /**
     * @param canalxKvConfig
     * @param iCanalxKv
     * @param tableKeyMap
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CanalxSelectDbJsonInitException
     */
    public static void load(CanalxKvConfig canalxKvConfig, ICanalxKv iCanalxKv, Map<String, String> tableKeyMap)
            throws IOException, ClassNotFoundException, CanalxSelectDbJsonInitException {

        IDbFetchController iDbFetchController = DbFetchControllerFactory.getDefaultDbController();
        iDbFetchController.init(canalxKvConfig.getDbLoaderFilePath());

        Map<String, Map<String, String>> dataInitMap = iDbFetchController.getInitDbKv();

        for (String tableId : dataInitMap.keySet()) {

            Map<String, String> tableKvMap = dataInitMap.get(tableId);

            for (String key : tableKvMap.keySet()) {

                try {
                    iCanalxKv.put(tableId, key, tableKvMap.get(key));

                } catch (CanalxProcessorException e) {

                    LOGGER.error("cannot put {} {} {} {} ", tableId, key, tableKvMap.get(key), e.toString());
                }
            }

            String tableKey = iDbFetchController.getTableKey(tableId);
            tableKeyMap.put(tableId, tableKey);
            LOGGER.info("tableId: {} , TableKey {} loads ok", tableId, tableKey);
        }
    }
}


