package com.github.knightliao.canalx.plugin.processor.kv.data.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.config.IConfig;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.StoreType;

import lombok.Data;

/**

 */
@Data
public class CanalxKvConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvConfig.class);

    private final static String CONFIG_FILE_NAME = "canalx-db-kv.xml";

    private StoreType storeType;

    private String dbLoaderFilePath;

    @Override
    public void init(ICanalxContext iCanalxContext) throws Exception {

        this.dbLoaderFilePath = iCanalxContext.getProperty("canalx.plugin.processor.db.loader.filepath");
        if (this.dbLoaderFilePath == null) {
            this.dbLoaderFilePath = CONFIG_FILE_NAME;
        }

        String storeTypeStr = iCanalxContext.getProperty("canalx.plugin.processor.kv.type");

        this.storeType = StoreType.get(storeTypeStr);
    }

    @Override
    public void init() throws Exception {
    }

}
