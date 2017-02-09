package com.github.knightliao.canalx.starter.core.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxPluginException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.injector.InjectorMgr;
import com.github.knightliao.canalx.injector.InjectorMgrFactory;
import com.github.knightliao.canalx.injector.support.InjectConstants;
import com.github.knightliao.canalx.processor.IProcessorMgr;
import com.github.knightliao.canalx.processor.ProcessorMgrFactory;
import com.github.knightliao.canalx.processor.support.ProcessorConstants;
import com.github.knightliao.canalx.router.IRouterMgr;
import com.github.knightliao.canalx.router.RouterMgrFactory;
import com.github.knightliao.canalx.router.support.RouterConstants;
import com.github.knightliao.canalx.starter.core.IPluginMgr;

/**
 * @author knightliao
 * @date 2016/11/24 14:32
 */
public class PluginMgrIml implements IPluginMgr {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PluginMgrIml.class);

    // injector mgr
    private InjectorMgr injectorMgr = InjectorMgrFactory.getDefaultInjectorMgr();

    // processors`
    private IProcessorMgr processorMgr = ProcessorMgrFactory.getDefaultProcessorMgr();

    // router`
    private IRouterMgr routerMgr = RouterMgrFactory.getDefaultRouterMgr();

    @Override
    public void loadPlugins(ICanalxContext iCanalxContext) throws CanalxPluginException {

        //
        // load injector
        //
        Set<String> userInjectors = iCanalxContext.getInjectorPluginName();
        if (injectorMgr instanceof IPlugin) {
            ((IPlugin) injectorMgr).loadPlugin(InjectConstants.SCAN_PACK_PLUGIN_INJECT, userInjectors);
        }
        injectorMgr.setCanalxContext(iCanalxContext);

        //
        //  load processor
        //
        Set<String> userProcessorNames = iCanalxContext.getProcessorPluginName();
        if (processorMgr instanceof IPlugin) {
            ((IPlugin) processorMgr).loadPlugin(ProcessorConstants.SCAN_PACK_PLUGIN_PROCESSOR, userProcessorNames);
        }
        processorMgr.setCanalxContext(iCanalxContext);

        //
        //  load router
        //
        Set<String> userRouterNames = iCanalxContext.getRouterPluginName();
        if (routerMgr instanceof IPlugin) {
            ((IPlugin) routerMgr).loadPlugin(RouterConstants.SCAN_PACK_PLUGIN_ROUTER, userRouterNames);
        }
        routerMgr.setCanalxContext(iCanalxContext);
    }

    /**
     *
     */
    public void init(IInjectEventProcessCallback injectEntryProcessCallback) throws CanalxException {

        // init injector
        injectorMgr.setupEventProcessCallback(injectEntryProcessCallback);
        injectorMgr.init();

        // init processor
        processorMgr.init();

        // init router
        routerMgr.init();
    }

    @Override
    public void runInjector() throws CanalxInjectorException {
        injectorMgr.runInjector();
    }

    @Override
    public void runProcessor(MysqlEntryWrap entry) throws CanalxProcessorException {
        processorMgr.runProcessor(entry);
    }

    @Override
    public void runRouter() throws CanalxRouterException {
        routerMgr.runRouter();
    }

    @Override
    public void stopInjector() {
        injectorMgr.shutdown();
    }

    @Override
    public void stopProcessor() {
        processorMgr.shutdown();
    }

    @Override
    public void stopRouter() {
        routerMgr.shutdown();
    }

}
