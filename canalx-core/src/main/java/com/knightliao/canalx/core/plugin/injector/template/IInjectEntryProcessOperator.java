package com.knightliao.canalx.core.plugin.injector.template;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * entry process template
 *
 * @author knightliao
 * @date 2016/11/24 11:58
 */
public interface IInjectEntryProcessOperator {

    void processEntry(MysqlEntry mysqlEntry) throws
            CanalxInjectorException;
}
