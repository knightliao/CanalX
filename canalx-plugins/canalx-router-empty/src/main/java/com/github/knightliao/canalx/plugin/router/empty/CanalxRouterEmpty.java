package com.github.knightliao.canalx.plugin.router.empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-router-empty")
public class CanalxRouterEmpty implements ICanalxRouter, ICanalxContextAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxRouterEmpty.class);

    // context
    protected ICanalxContext canalxContext;

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.canalxContext = iCanalxContext;
    }

    @Override
    public void start() throws CanalxRouterException {

    }

    @Override
    public void init() throws CanalxRouterException {

    }

    @Override
    public void shutdown() throws CanalxRouterException {

    }
}
