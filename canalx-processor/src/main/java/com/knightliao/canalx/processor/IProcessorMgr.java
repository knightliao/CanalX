package com.knightliao.canalx.processor;

import java.util.List;

import com.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.knightliao.canalx.core.exception.CanalxProcessorException;
import com.knightliao.canalx.core.exception.CanalxProcessorInitException;
import com.knightliao.canalx.core.plugin.processor.ICanalxProcessor;

/**
 * @author knightliao
 * @date 2016/12/1 11:52
 */
public interface IProcessorMgr {

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

