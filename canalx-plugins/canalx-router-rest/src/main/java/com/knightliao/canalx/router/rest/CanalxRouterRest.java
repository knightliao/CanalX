package com.knightliao.canalx.router.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxRouterException;
import com.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.core.support.context.ICanalxContext;
import com.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.knightliao.canalx.router.rest.support.config.RouterRestConfig;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-router-rest")
public class CanalxRouterRest implements ICanalxRouter, ICanalxContextAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxRouterRest.class);

    protected RouterRestConfig routerRestConfig = new RouterRestConfig();

    protected ICanalxContext canalxContext;

    protected boolean isInit = false;

    @Override
    public void start() throws CanalxRouterException {

    }

    @Override
    public void init() {

        if (canalxContext != null) {
            RouterRestConfig.initConfig(canalxContext);

            isInit = true;
        }

    }

    @Override
    public void shutdown() throws CanalxRouterException {

    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.canalxContext = canalxContext;
    }
}
