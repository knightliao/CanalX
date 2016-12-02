package com.knightliao.canalx.plugin.processor.kv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.exception.CanalxProcessorException;
import com.knightliao.canalx.core.plugin.processor.ICanalProcessor;
import com.knightliao.canalx.core.support.annotation.PluginName;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-processor-kv")
public class CanalxKvProcessor implements ICanalProcessor, IProcessorInitAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvProcessor.class);

    @Override
    public void processUpdate(MysqlEntry entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());
    }

    @Override
    public void processInsert(MysqlEntry entry) throws CanalxProcessorException {

    }

    @Override
    public void processDelete(MysqlEntry entry) throws CanalxProcessorException {

    }

    @Override
    public void init() {

    }
}
