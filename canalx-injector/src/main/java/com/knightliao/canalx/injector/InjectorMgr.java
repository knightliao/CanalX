package com.knightliao.canalx.injector;

import java.util.List;

import com.knightliao.canalx.core.exception.CanalxInjectorException;
import com.knightliao.canalx.core.exception.CanalxInjectorInitException;
import com.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.knightliao.canalx.core.plugin.injector.template.IInjectEntryProcessCallback;
import com.knightliao.canalx.core.support.context.ICanalxContextAware;

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
    void init(IInjectEntryProcessCallback injectEntryProcessCallback) throws
            CanalxInjectorInitException;
}
