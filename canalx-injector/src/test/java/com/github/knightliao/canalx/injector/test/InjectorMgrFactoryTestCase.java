package com.github.knightliao.canalx.injector.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;
import com.github.knightliao.canalx.core.support.context.CanalxContextFactory;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.injector.InjectorMgr;
import com.github.knightliao.canalx.injector.InjectorMgrFactory;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class InjectorMgrFactoryTestCase {

    @Test
    public void test() {

        InjectorMgr injectorMgr = InjectorMgrFactory.getDefaultInjectorMgr();

        Set<String> specifyPluginNames = new HashSet<>();
        specifyPluginNames.add("canalx-injector-empty");

        try {

            // set up context
            ICanalxContext canalxContext = CanalxContextFactory.getDefaultCanalxContext();

            // load
            ((IPlugin) injectorMgr).loadPlugin("com.github.knightliao.canalx.plugin.injector", specifyPluginNames);

            // get
            List<ICanalxInjector> canalxInjectors = injectorMgr.getInjectorPlugin();
            Assert.assertEquals(canalxInjectors.size(), 1);

            // set up context
            injectorMgr.setCanalxContext(canalxContext);

            // set up callback
            injectorMgr.setupEventProcessCallback(new IInjectEventProcessCallback() {
                @Override
                public void processMysqlEntry(MysqlEntryWrap mysqlEntry) throws CanalxInjectorException {

                }

                @Override
                public void shutdown() {

                }
            });

            // init
            injectorMgr.init();

            // run
            injectorMgr.runInjector();

            //
            injectorMgr.shutdown();

        } catch (Exception e) {

            Assert.assertTrue(false);
        }
    }
}
