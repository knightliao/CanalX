package com.github.knightliao.canalx.router.empty.test;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.plugin.router.empty.CanalxRouterEmpty;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
public class CanalxRouterEmptyTestCase {

    @Test
    public void test() {

        ICanalxRouter canalxRouter = new CanalxRouterEmpty();

        try {

            canalxRouter.init();

            canalxRouter.start();

            canalxRouter.shutdown();

        } catch (CanalxRouterException e) {

            Assert.assertTrue(false);
        }
    }
}
