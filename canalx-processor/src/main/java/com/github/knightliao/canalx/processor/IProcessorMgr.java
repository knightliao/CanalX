package com.github.knightliao.canalx.processor;

import java.util.List;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorInitException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/12/1 11:52
 */
public interface IProcessorMgr extends ICanalxContextAware {

    /**
     * @return
     */
    List<ICanalxProcessor> getProcessorPlugin();

    /**
     * 执行
     *
     * @param
     */
    void runProcessor(MysqlEntryWrap entry) throws CanalxProcessorException;

    /**
     *
     */
    void init() throws CanalxProcessorInitException;
}

