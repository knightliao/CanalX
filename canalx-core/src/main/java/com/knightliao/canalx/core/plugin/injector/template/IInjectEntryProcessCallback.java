package com.knightliao.canalx.core.plugin.injector.template;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * @author knightliao
 * @date 2016/11/24 12:01
 */
public interface IInjectEntryProcessCallback {

    void processEntry(MysqlEntry mysqlEntry) throws CanalxInjectorException;
}
