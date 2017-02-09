package com.github.knightliao.canalx.starter.core;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxPluginException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;

/**
 * @author knightliao
 * @date 2016/11/24 14:34
 */
public interface IPluginMgr {

    // 载入插件
    void loadPlugins(ICanalxContext canalxProfile) throws CanalxPluginException;

    /**
     *
     */
    void init(IInjectEventProcessCallback injectEntryProcessCallback) throws CanalxException;

    /**
     * runner
     *
     * @throws CanalxInjectorException
     */
    void runInjector() throws CanalxInjectorException;

    void runProcessor(MysqlEntryWrap entry) throws CanalxProcessorException;

    void runRouter() throws CanalxRouterException;

    /**
     * stop
     */
    void stopInjector();

    void stopProcessor();

    void stopRouter();
}
