package com.github.knightliao.canalx.plugin.processor.kv.data.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.config.IConfig;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.StoreType;

import lombok.Data;

/**

 */
@Data
public class CanalxKvConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvConfig.class);

    private final static String CONFIG_FILE_NAME = "canalx-db-kv.xml";

    private StoreType storeType;

    private String dbLoaderFilePath;

    private boolean isInitWithDb = true;

    private boolean getDbWhenNotHit = false;

    @Override
    public void init(ICanalxContext iCanalxContext) throws Exception {

        // 默认是 CONFIG_FILE_NAME
        this.dbLoaderFilePath =
                iCanalxContext.getProperty("canalx.plugin.processor.db.loader.filepath", CONFIG_FILE_NAME);

        // 默认是KV
        String storeTypeStr = iCanalxContext.getProperty("canalx.plugin.processor.kv.type", StoreType.KV.getName());

        // 默认是true
        isInitWithDb = Boolean.parseBoolean(iCanalxContext.getProperty("canalx.plugin.processor.db.loader.init",
                "true"));

        // 默认是false
        getDbWhenNotHit =
                Boolean.parseBoolean(iCanalxContext.getProperty("canalx.plugin.processor.db.get.row.when.not.hit",
                        "false"));

        this.storeType = StoreType.get(storeTypeStr);
    }

    @Override
    public void init() throws Exception {
    }

}
