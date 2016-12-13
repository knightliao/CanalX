package com.github.knightliao.canalx.plugin.injector.empty.test;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorInitException;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.github.knightliao.canalx.plugin.injector.empty.CanalxEmptyInjector;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
public class CanalxEmptyInjectorTestCase {

    @Test
    public void test() {

        ICanalxInjector canalxInjector = new CanalxEmptyInjector();

        try {

            canalxInjector.init();

            canalxInjector.run();

            canalxInjector.shutdown();

        } catch (CanalxInjectorInitException e) {

            Assert.assertTrue(false);

        } catch (CanalxInjectorException e) {

            Assert.assertTrue(false);
        }

    }
}
