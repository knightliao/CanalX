package com.knightliao.canalx.plugin.processor.kv.data;

import java.io.IOException;

import com.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * 单实例
 *
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public class CanalxKvInstance {

    private static ICanalxKv iCanalxKv = new CanalxKvDefaultImpl();

    private static volatile boolean isInit = false;

    private static CanalxKvConfig canalxKvConfig = new CanalxKvConfig();

    public static void init() {

        try {

            loadConfigAndInit();

            isInit = true;

        } catch (Exception e) {
            throw new CanalxInjectorException(e);
        }
    }

    /**
     * @throws IOException
     */
    private static void loadConfigAndInit() throws IOException {

        // setup config
        canalxKvConfig.setupConfig();

        // load sql data
        //  DbFetcherFactory.getDefaultDbFetcher(canalxKvConfig.getDriverClass(), canalxKvConfig.getDbUrl(),
        //        canalxKvConfig.getUserName(), canalxKvConfig.getPassword());
    }

    /**
     * @param key
     *
     * @return
     */
    public static String get(String key) {

        if (isInit) {

            return iCanalxKv.get(key);
        } else {

            return null;
        }
    }
}
