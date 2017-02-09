package com.github.knightliao.canalx.starter.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxPluginException;
import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.starter.core.IPluginMgr;
import com.github.knightliao.canalx.starter.core.ICanalxController;

/**
 * @author knightliao
 * @date 2016/11/24 14:37
 */
public class CanalxControllerImpl implements ICanalxController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxControllerImpl.class);

    protected IPluginMgr pluginMgr;

    // injector
    protected ICanalxInjector canalInjector = null;

    // router
    protected ICanalxRouter canalxRouter = null;

    //
    protected Thread thread = null;
    protected volatile boolean running = false;

    public void run(ICanalxContext iCanalxContext) {

        loadPlugin(iCanalxContext);

        runMain();
    }

    /**
     *
     */
    private void runMain() {

        final CanalxControllerThread canalxControllerThread = new CanalxControllerThread(this);
        thread = new Thread(new Runnable() {
            public void run() {

                try {
                    canalxControllerThread.run();

                } catch (Throwable e) {
                    LOGGER.error(e.toString(), e);
                    running = false;
                }
            }
        });

        thread.start();
        running = true;
    }

    private void loadPlugin(ICanalxContext canalxProfile) {

        try {

            // load plugin
            pluginMgr = new PluginMgrIml();
            pluginMgr.loadPlugins(canalxProfile);

        } catch (CanalxPluginException ex) {

            LOGGER.error(ex.toString());
        }
    }

    @Override
    public void shutdown() {

        if (!running) {
            return;
        }
        running = false;
        if (thread != null) {
            try {
                //thread.join();
                thread.join(1000);   // 等待1000ms
            } catch (InterruptedException e) {
                LOGGER.warn(e.toString());
            }
        }

        if (canalInjector != null) {
            try {
                canalInjector.shutdown();
            } catch (CanalxInjectorException e) {
                LOGGER.warn(e.toString());
            }
        }

        if (canalxRouter != null) {
            try {
                canalxRouter.shutdown();
            } catch (CanalxRouterException e) {
                LOGGER.warn(e.toString());
            }
        }
    }
}
