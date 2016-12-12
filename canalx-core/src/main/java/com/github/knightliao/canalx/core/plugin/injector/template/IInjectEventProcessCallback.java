package com.github.knightliao.canalx.core.plugin.injector.template;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * @author knightliao
 * @date 2016/11/24 12:01
 */
public interface IInjectEventProcessCallback {

    void processMysqlEntry(MysqlEntryWrap mysqlEntry) throws CanalxInjectorException;

    void shutdown();
}
