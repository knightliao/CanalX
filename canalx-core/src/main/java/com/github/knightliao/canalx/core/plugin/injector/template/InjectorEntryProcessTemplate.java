package com.github.knightliao.canalx.core.plugin.injector.template;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * entry process template
 *
 * @author knightliao
 * @date 2016/11/24 11:56
 */
public class InjectorEntryProcessTemplate implements IInjectEntryProcessOperator {

    protected IInjectEntryProcessCallback injectEntryProcessCallback;

    public InjectorEntryProcessTemplate(IInjectEntryProcessCallback injectEntryProcessCallback) {
        this.injectEntryProcessCallback = injectEntryProcessCallback;
    }

    @Override
    public void processEntry(MysqlEntryWrap mysqlEntry) throws
            CanalxInjectorException {

        if (injectEntryProcessCallback != null) {
            injectEntryProcessCallback.processMysqlEntry(mysqlEntry);
        }
    }
}
