package com.github.knightliao.canalx.plugin.processor.empty;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-processor-empty")
public class CanalxEmptyProcessor implements ICanalxProcessor, ICanalxDataRouter, ICanalxContextAware {

    private ICanalxContext iCanalxContext = null;

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.iCanalxContext = iCanalxContext;
    }

    @Override
    public String get(String tableId, String key) {
        return null;
    }

    @Override
    public void processUpdate(MysqlEntryWrap entry) throws CanalxProcessorException {

    }

    @Override
    public void processInsert(MysqlEntryWrap entry) throws CanalxProcessorException {

    }

    @Override
    public void processDelete(MysqlEntryWrap entry) throws CanalxProcessorException {

    }

    @Override
    public void init() {

    }

    @Override
    public void shutdown() {

    }
}
