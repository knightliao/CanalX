package com.github.knightliao.canalx.plugin.processor.kv.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.db.DbFetchControllerFactory;
import com.github.knightliao.canalx.db.IDbFetchController;
import com.github.knightliao.canalx.db.exception.CanalxSelectDbJsonInitException;
import com.github.knightliao.canalx.plugin.processor.kv.data.impl.CanalxKvDefaultImpl;

/**
 * 单实例
 *
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public class CanalxKvInstance {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvInstance.class);

    private static ICanalxKv iCanalxKv = new CanalxKvDefaultImpl();

    // id fetcher
    private static IDbFetchController iDbFetchController = DbFetchControllerFactory.getDefaultDbController();

    // 记录 tableid -> table key
    private static Map<String, String> tableKeyMap = new HashMap<>();

    private static volatile boolean isInit = false;

    public static void init(String configFilePath) {

        try {

            loadConfigAndInit(configFilePath);

            isInit = true;

        } catch (Exception e) {

            LOGGER.error(e.toString());
        }
    }

    /**
     * @throws IOException
     */
    private static void loadConfigAndInit(String configFilePath)
            throws IOException, ClassNotFoundException, CanalxSelectDbJsonInitException {

        iDbFetchController.init(configFilePath);
        Map<String, Map<String, String>> dataInitMap = iDbFetchController.getInitDbKv();

        for (String tableId : dataInitMap.keySet()) {

            Map<String, String> tableKvMap = dataInitMap.get(tableId);

            for (String key : tableKvMap.keySet()) {

                iCanalxKv.put(tableId, key, tableKvMap.get(key));
            }

            String tableKey = iDbFetchController.getTableKey(tableId);
            tableKeyMap.put(tableId, tableKey);
            LOGGER.info("tableId: {} , Tablekey {} loads ok", tableId, tableKey);
        }
    }

    /**
     * @param key
     *
     * @return
     */
    public static String get(String tableId, String key) {

        if (isInit) {

            return iCanalxKv.get(tableId, key);
        } else {

            return null;
        }
    }

    /**
     * @param key
     *
     * @return
     */
    public static boolean put(String tableId, String key, String value) {

        if (isInit) {

            iCanalxKv.put(tableId, key, value);

            return true;

        } else {

            return false;
        }
    }

    public static String getTableKey(String tableId) {
        if (tableKeyMap.containsKey(tableId)) {
            return tableKeyMap.get(tableId);
        } else {

            return "";
        }
    }
}
