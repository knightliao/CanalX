package com.github.knightliao.canalx.plugin.injector.empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorInitException;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.github.knightliao.canalx.core.plugin.injector.IInjectorEntryProcessorAware;
import com.github.knightliao.canalx.core.plugin.injector.template.InjectorEventProcessTemplate;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-injector-empty")
public class CanalxEmptyInjector implements ICanalxInjector, IInjectorEntryProcessorAware, ICanalxContextAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxEmptyInjector.class);

    //
    private ICanalxContext iCanalxContext = null;

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.iCanalxContext = iCanalxContext;
    }

    @Override
    public void init() throws CanalxInjectorInitException {

    }

    @Override
    public void run() throws CanalxInjectorException {

    }

    @Override
    public void shutdown() throws CanalxInjectorException {

    }

    @Override
    public void setupProcessEntry(InjectorEventProcessTemplate injectorEntryProcessTemplate) {

    }
}
