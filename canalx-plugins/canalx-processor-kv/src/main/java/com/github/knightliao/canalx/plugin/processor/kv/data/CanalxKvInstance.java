package com.github.knightliao.canalx.plugin.processor.kv.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.db.IDbStoreLoader;
import com.github.knightliao.canalx.plugin.processor.kv.data.db.impl.DbStoreLoaderImpl;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.CanalxKvFactory;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;
import com.github.knightliao.canalx.plugin.processor.kv.data.support.CanalxKvConfig;

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

    //
    private static IDbStoreLoader iDbStoreLoader = new DbStoreLoaderImpl();

    private static volatile boolean isInit = false;

    public static void init(ICanalxContext iCanalxContext) {

        try {

            // init config
            canalxKvConfig.init(iCanalxContext);

            // new canalx instance
            iCanalxKv = CanalxKvFactory.getStoreInstance(canalxKvConfig.getStoreType(), iCanalxContext);

            // load db data and table key
            iDbStoreLoader.init(canalxKvConfig);
            if (canalxKvConfig.isInitWithDb()) {
                iDbStoreLoader.loadInitData(iCanalxKv);
            }

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

                String value = iCanalxKv.get(tableId, key);
                if (value == null && canalxKvConfig.isGetDbWhenNotHit()) {

                    // select from db
                    return iDbStoreLoader.executeRowSql(tableId, key);
                }

                return value;

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

    /**
     * 获取 table 要设定的 KEY
     *
     * @param tableId
     *
     * @return
     */
    public static String getTableKey(String tableId) {
        return iDbStoreLoader.getTableKey(tableId);
    }

    /**
     * 关闭
     */
    public static void shutdown() {

        iCanalxKv.shutdown();
    }

}
