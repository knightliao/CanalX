package com.github.knightliao.canalx.core.test.support.context;

import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.support.context.CanalxContextFactory;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;

/**
 * @author knightliao
 * @date 2016/11/24 16:05
 */
public class CanalxContextFactoryTestCase {

    @Test
    public void test() {

        ICanalxContext iCanalxContext = CanalxContextFactory.getDefaultCanalxContext();

        try {

            iCanalxContext.load();

            Set<String> injectorPluginNameSet = iCanalxContext.getInjectorPluginName();
            Assert.assertEquals(injectorPluginNameSet.toString(), "[canalx-injector-kafka]");

            Set<String> processorPluginNameSet = iCanalxContext.getProcessorPluginName();
            Assert.assertEquals(processorPluginNameSet.toString(), "[canalx-processor-kv-codis]");

            Set<String> routerPluginNameSet = iCanalxContext.getRouterPluginName();
            Assert.assertEquals(routerPluginNameSet.toString(), "[canalx-router-rest]");

            String topics = iCanalxContext.getProperty("canalx.plugin.injector.topics");
            Assert.assertEquals(topics, "test,test2");

        } catch (IOException e) {

            Assert.assertTrue(false);
        }
    }
}
