package com.github.knightliao.canalx.core.plugin.injector.template;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * entry process template
 *
 * @author knightliao
 * @date 2016/11/24 11:56
 */
public class InjectorEventProcessTemplate implements IInjectEventProcessOperator {

    protected IInjectEventProcessCallback injectEventProcessCallback;

    public InjectorEventProcessTemplate(IInjectEventProcessCallback injectEventProcessCallback) {
        this.injectEventProcessCallback = injectEventProcessCallback;
    }

    @Override
    public void processEntry(MysqlEntryWrap mysqlEntry) throws
            CanalxInjectorException {

        if (injectEventProcessCallback != null) {
            injectEventProcessCallback.processMysqlEntry(mysqlEntry);
        }
    }

    @Override
    public void shutdown() {

        if (injectEventProcessCallback != null) {
            injectEventProcessCallback.shutdown();
        }
    }
}
