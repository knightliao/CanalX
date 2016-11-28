package com.knightliao.canalx.core.plugin.processor;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.exception.CanalxProcessorException;

/**
 * 消息处理接口
 *
 * @author knightliao
 * @date 2016/11/23 17:08
 */
public interface ICanalProcessor {

    /**
     * update
     *
     * @param entry
     * @param
     */
    void processUpdate(MysqlEntry entry) throws CanalxProcessorException;

    /**
     * insert
     *
     * @param entry
     * @param
     */
    void processInsert(MysqlEntry entry) throws CanalxProcessorException;

    /**
     * delete
     *
     * @param entry
     * @param
     */
    void processDelete(MysqlEntry entry) throws CanalxProcessorException;
}
