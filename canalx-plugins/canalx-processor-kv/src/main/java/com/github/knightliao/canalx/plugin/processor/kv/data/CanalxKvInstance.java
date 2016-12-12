package com.github.knightliao.canalx.plugin.processor.kv.data;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;
import com.github.knightliao.canalx.plugin.processor.kv.data.support.CanalxKvConfig;
import com.github.knightliao.canalx.plugin.processor.kv.data.support.DbStoreLoaderUtils;

/**
 * 单实例
 *
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public class CanalxKvInstance {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvInstance.class);

    // kv impl
    private static ICanalxKv iCanalxKv = null;

    // config
    private static CanalxKvConfig canalxKvConfig = new CanalxKvConfig();

    // 记录 tableid -> table key
    private static Map<String, String> tableKeyMap = new HashMap<>();

    private static volatile boolean isInit = false;

    public static void init(ICanalxContext iCanalxContext) {

        try {

            // init config
            canalxKvConfig.init(iCanalxContext);

            // new canalx instance
            iCanalxKv = CanalxKvFactory.getStoreInstance(canalxKvConfig.getStoreType(), iCanalxContext);

            // load db data
            DbStoreLoaderUtils.load(canalxKvConfig, iCanalxKv, tableKeyMap);

            isInit = true;

        } catch (Exception e) {

            LOGGER.error(e.toString());
        }
    }

    /**
     * @param key
     *
     * @return
     */
    public static String get(String tableId, String key) {

        if (isInit) {

            try {

                return iCanalxKv.get(tableId, key);

            } catch (CanalxProcessorException e) {

                LOGGER.error(e.toString());
                return null;
            }
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

            try {

                iCanalxKv.put(tableId, key, value);
                return true;

            } catch (CanalxProcessorException e) {

                LOGGER.error("cannot put {} {} {} ", tableId, key, value, e.toString());
                return false;
            }

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

    public static void shutdown() {

        iCanalxKv.shutdown();
    }
}
