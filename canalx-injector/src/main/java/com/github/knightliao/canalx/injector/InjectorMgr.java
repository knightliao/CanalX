package com.github.knightliao.canalx.injector;

import java.util.List;

import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorInitException;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/12/1 11:34
 */
public interface InjectorMgr extends ICanalxContextAware {

    /**
     * @return
     */
    List<ICanalxInjector> getInjectorPlugin();

    /**
     * 执行
     */
    void runInjector() throws CanalxInjectorException;

    /**
     *
     */
    void init() throws CanalxInjectorInitException;

    /**
     * @param injectEntryProcessCallback
     */
    void setupEventProcessCallback(IInjectEventProcessCallback injectEntryProcessCallback);

    void shutdown();

}
