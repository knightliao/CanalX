package com.knightliao.canalx.plugin.processor.kv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.knightliao.canalx.core.exception.CanalxProcessorException;
import com.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.plugin.processor.kv.data.CanalxKvInstance;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-processor-kv")
public class CanalxKvProcessor implements ICanalxProcessor, ICanalxRouter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvProcessor.class);

    @Override
    public void processUpdate(MysqlEntryWrap entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());
    }

    @Override
    public void processInsert(MysqlEntryWrap entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());
    }

    @Override
    public void processDelete(MysqlEntryWrap entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());
    }

    /**
     * 初始化
     */
    @Override
    public void init() {

        LOGGER.info("start to init {}", CanalxKvProcessor.class.toString());
        CanalxKvInstance.init();
    }

    /**
     * @param tableId
     * @param key
     *
     * @return
     */
    @Override
    public String get(String tableId, String key) {

        return CanalxKvInstance.get(tableId, key);
    }
}
