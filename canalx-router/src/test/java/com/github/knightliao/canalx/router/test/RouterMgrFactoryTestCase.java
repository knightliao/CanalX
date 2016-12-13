package com.github.knightliao.canalx.router.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.core.support.context.CanalxContextFactory;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.router.IRouterMgr;
import com.github.knightliao.canalx.router.RouterMgrFactory;
import com.github.knightliao.canalx.router.support.RouterConstants;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class RouterMgrFactoryTestCase {

    @Test
    public void test() {

        IRouterMgr routerMgr = RouterMgrFactory.getDefaultRouterMgr();

        Set<String> specifyPluginNames = new HashSet<>();
        specifyPluginNames.add("canalx-router-empty");

        try {

            // set up context
            ICanalxContext canalxContext = CanalxContextFactory.getDefaultCanalxContext();

            // load
            ((IPlugin) routerMgr).loadPlugin(RouterConstants.SCAN_PACK_PLUGIN_ROUTER, specifyPluginNames);

            // get
            List<ICanalxRouter> canalxRouters = routerMgr.getRouterPlugin();
            Assert.assertEquals(canalxRouters.size(), 1);

            // set up context
            routerMgr.setCanalxContext(canalxContext);

            // init
            routerMgr.init();

            // run
            routerMgr.runRouter();

            //
            routerMgr.shutdown();

        } catch (Exception e) {

            Assert.assertTrue(false);
        }
    }
}
