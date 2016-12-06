package com.knightliao.canalx.plugin.processor.kv.data;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.db.DbFetchControllerFactory;
import com.knightliao.canalx.db.IDbFetchController;
import com.knightliao.canalx.db.exception.CanalxSelectDbJsonInitException;

/**
 * 单实例
 *
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public class CanalxKvInstance {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvInstance.class);

    private static ICanalxKv iCanalxKv = new CanalxKvDefaultImpl();

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

        // load sql data
        IDbFetchController iDbFetchController = DbFetchControllerFactory.getDefaultDbController();

        Map<String, Map<String, String>> dataInitMap = iDbFetchController.getInitDbKv(configFilePath);

        for (String tableId : dataInitMap.keySet()) {

            Map<String, String> tableKvMap = dataInitMap.get(tableId);

            for (String key : tableKvMap.keySet()) {

                iCanalxKv.put(tableId, key, tableKvMap.get(key));
            }
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
}
