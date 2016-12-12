package com.github.knightliao.canalx.injector.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.exception.CanalxPluginException;
import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
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
        specifyPluginNames.add("canalx-injector-kafka");

        try {

            ((IPlugin) injectorMgr).loadPlugin("com.github.knightliao.canalx.plugin.injector", specifyPluginNames);

            List<ICanalxInjector> canalxInjectors = injectorMgr.getInjectorPlugin();
            Assert.assertEquals(canalxInjectors.size(), 1);

        } catch (CanalxPluginException e) {

            Assert.assertTrue(false);
        }
    }
}
