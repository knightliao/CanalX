package com.github.knightliao.canalx.core.test.plugin.injector.template;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessOperator;
import com.github.knightliao.canalx.core.plugin.injector.template.InjectorEventProcessTemplate;

/**
 * @author knightliao
 * @date 2016/12/12 23:25
 */
public class InjectorEventProcessTemplateTestCase {

    @Test
    public void test() {

        IInjectEventProcessOperator injectEventProcessOperator = new InjectorEventProcessTemplate(
                new IInjectEventProcessCallback() {

                    @Override
                    public void
                    processMysqlEntry(
                            MysqlEntryWrap mysqlEntry)
                            throws
                            CanalxInjectorException {

                    }

                    @Override
                    public void shutdown() {

                    }
                });

        MysqlEntryWrap mysqlEntryWrap = new MysqlEntryWrap("topic", new MysqlEntry());

        try {

            injectEventProcessOperator.processEntry(mysqlEntryWrap);

            injectEventProcessOperator.shutdown();

        } catch (CanalxInjectorException e) {

            Assert.assertTrue(false);
        }
    }
}
