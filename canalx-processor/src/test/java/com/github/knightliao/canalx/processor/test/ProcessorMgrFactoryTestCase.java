package com.github.knightliao.canalx.processor.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.support.context.CanalxContextFactory;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.processor.IProcessorMgr;
import com.github.knightliao.canalx.processor.ProcessorMgrFactory;
import com.github.knightliao.canalx.processor.support.ProcessorConstants;

/**
 * @author knightliao
 * @date 2016/12/13 23:07
 */
public class ProcessorMgrFactoryTestCase {

    @Test
    public void test() {

        IProcessorMgr processorMgr = ProcessorMgrFactory.getDefaultProcessorMgr();

        Set<String> specifyPluginNames = new HashSet<>();
        specifyPluginNames.add("canalx-processor-empty");

        try {

            // set up context
            ICanalxContext canalxContext = CanalxContextFactory.getDefaultCanalxContext();

            // load
            ((IPlugin) processorMgr).loadPlugin(ProcessorConstants.SCAN_PACK_PLUGIN_PROCESSOR, specifyPluginNames);

            // get
            List<ICanalxProcessor> canalxProcessors = processorMgr.getProcessorPlugin();
            Assert.assertEquals(canalxProcessors.size(), 1);

            // set up context
            processorMgr.setCanalxContext(canalxContext);

            // init
            processorMgr.init();

            // run
            MysqlEntryWrap mysqlEntryWrap = new MysqlEntryWrap("topic", new MysqlEntry());
            processorMgr.runProcessor(mysqlEntryWrap);

            //
            processorMgr.shutdown();

        } catch (Exception e) {

            Assert.assertTrue(false);
        }
    }
}
