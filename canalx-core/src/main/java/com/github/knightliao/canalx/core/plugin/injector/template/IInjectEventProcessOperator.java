package com.github.knightliao.canalx.core.plugin.injector.template;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * entry process event template
 *
 * @author knightliao
 * @date 2016/11/24 11:58
 */
public interface IInjectEventProcessOperator {

    void processEntry(MysqlEntryWrap mysqlEntry) throws
            CanalxInjectorException;

    void shutdown();
}
