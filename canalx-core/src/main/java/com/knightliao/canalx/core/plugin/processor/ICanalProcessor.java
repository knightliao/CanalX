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
     * @param table
     */
    void processUpdate(MysqlEntry entry, String table) throws CanalxProcessorException;

    /**
     * insert
     *
     * @param entry
     * @param table
     */
    void processInsert(MysqlEntry entry, String table) throws CanalxProcessorException;

    /**
     * delete
     *
     * @param entry
     * @param table
     */
    void processDelete(MysqlEntry entry, String table) throws CanalxProcessorException;
}
