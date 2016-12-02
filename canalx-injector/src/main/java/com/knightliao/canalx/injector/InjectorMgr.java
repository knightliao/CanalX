package com.knightliao.canalx.injector;

import java.util.List;

import com.knightliao.canalx.core.plugin.injector.ICanalInjector;
import com.knightliao.canalx.core.plugin.injector.template.IInjectEntryProcessCallback;

/**
 * @author knightliao
 * @date 2016/12/1 11:34
 */
public interface InjectorMgr {

    /**
     * @return
     */
    List<ICanalInjector> getInjectorPlugin();

    /**
     * 执行
     *
     * @param injectEntryProcessCallback
     */
    void runInjector(IInjectEntryProcessCallback injectEntryProcessCallback);
}
