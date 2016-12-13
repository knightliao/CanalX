package com.github.knightliao.canalx.plugin.processor.empty.test;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.plugin.processor.empty.CanalxEmptyProcessor;

/**
 * @author knightliao
 * @date 2016/12/13 23:01
 */
public class CanalxEmptyProcessorTestCase {

    @Test
    public void test() {

        ICanalxProcessor canalxEmptyProcessor = new CanalxEmptyProcessor();

        try {

            canalxEmptyProcessor.init();

            MysqlEntryWrap mysqlEntryWrap = new MysqlEntryWrap("topic", new MysqlEntry());

            canalxEmptyProcessor.processDelete(mysqlEntryWrap);

            canalxEmptyProcessor.processInsert(mysqlEntryWrap);
            canalxEmptyProcessor.processUpdate(mysqlEntryWrap);

            canalxEmptyProcessor.shutdown();

        } catch (CanalxProcessorException e) {

            Assert.assertTrue(false);
        }

    }
}
