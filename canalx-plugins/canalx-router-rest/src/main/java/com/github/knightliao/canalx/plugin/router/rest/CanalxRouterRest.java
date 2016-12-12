package com.github.knightliao.canalx.plugin.router.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.github.knightliao.canalx.plugin.router.rest.support.config.RouterRestConfig;
import com.github.knightliao.canalx.plugin.router.rest.support.server.CanalxDataGetter;
import com.github.knightliao.canalx.plugin.router.rest.support.server.CanalxRouterServerFactory;
import com.github.knightliao.canalx.plugin.router.rest.support.server.ICanalxRouterServer;

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
                routerRestConfig.init(canalxContext);

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
        LOGGER.info(CanalxRouterRest.class.toString() + " stops gracefully");
    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.canalxContext = iCanalxContext;
    }
}
