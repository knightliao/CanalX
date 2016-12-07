package com.knightliao.canalx.plugin.router.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxRouterException;
import com.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.core.support.context.ICanalxContext;
import com.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.knightliao.canalx.plugin.router.rest.support.config.RouterRestConfig;
import com.knightliao.canalx.plugin.router.rest.support.server.CanalxDataGetter;
import com.knightliao.canalx.plugin.router.rest.support.server.CanalxRouterServerFactory;
import com.knightliao.canalx.plugin.router.rest.support.server.ICanalxRouterServer;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-router-rest")
public class CanalxRouterRest implements ICanalxRouter, ICanalxContextAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxRouterRest.class);

    // config
    protected RouterRestConfig routerRestConfig = new RouterRestConfig();

    // context
    protected ICanalxContext canalxContext;

    // data source
    protected ICanalxDataRouter iCanalxDataRouter = null;

    //
    protected ICanalxRouterServer iCanalxRouterServer = CanalxRouterServerFactory.getDefaultRouterServer();

    protected boolean isInit = false;

    @Override
    public void start() throws CanalxRouterException {

        if (isInit) {

            if (iCanalxRouterServer != null) {

                iCanalxRouterServer.start(routerRestConfig.getServerPort());
            }
        }
    }

    @Override
    public void init() throws CanalxRouterException {

        try {
            if (canalxContext != null) {

                // config
                routerRestConfig.initConfig(canalxContext);

                // get data source
                this.iCanalxDataRouter = canalxContext.getProcessorAsDataSource(routerRestConfig.getDataSource());
                CanalxDataGetter.setupICanalxDataRouter(iCanalxDataRouter);

                isInit = true;
            }
        } catch (Exception e) {
            throw new CanalxRouterException(e);
        }

    }

    @Override
    public void shutdown() throws CanalxRouterException {

        if (isInit) {
            iCanalxRouterServer.stop();
        }
    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.canalxContext = iCanalxContext;
    }
}
