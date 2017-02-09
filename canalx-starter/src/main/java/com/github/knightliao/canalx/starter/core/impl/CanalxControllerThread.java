package com.github.knightliao.canalx.starter.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.injector.template.IInjectEventProcessCallback;

/**
 * @author knightliao
 * @date 2016/11/24 16:59
 */
public class CanalxControllerThread implements Runnable {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxControllerThread.class);

    protected CanalxControllerImpl canalxController;

    protected boolean isInitOk = false;

    public CanalxControllerThread(CanalxControllerImpl canalxController) {

        this.canalxController = canalxController;
    }

    @Override
    public void run() {

        // init injector and processor
        init();

        // run injector
        runInjector();

        // run router
        runRouter();
    }

    private void init() {

        try {

            // injector call back
            IInjectEventProcessCallback injectEntryProcessCallback = new IInjectEventProcessCallback() {

                @Override
                public void processMysqlEntry(MysqlEntryWrap mysqlEntry) throws CanalxInjectorException {

                    runProcessor(mysqlEntry);
                }

                @Override
                public void shutdown() {

                }
            };

            this.canalxController.pluginMgr.init(injectEntryProcessCallback);

            //
            isInitOk = true;

            LOGGER.info("init injector and processor ok");

        } catch (CanalxException e) {

            LOGGER.error(e.toString(), e);
        }
    }

    /**
     * run injector
     */
    private void runInjector() {

        if (isInitOk) {

            try {
                this.canalxController.pluginMgr.runInjector();
            } catch (CanalxInjectorException e) {
                LOGGER.error(e.toString());
            }
        }
    }

    /**
     * run router
     */
    private void runRouter() {

        if (isInitOk) {

            try {
                this.canalxController.pluginMgr.runRouter();
            } catch (CanalxRouterException e) {
                LOGGER.error(e.toString());
            }
        }
    }

    /**
     * run processor
     */
    private void runProcessor(MysqlEntryWrap entry) {

        try {
            this.canalxController.pluginMgr.runProcessor(entry);
        } catch (CanalxProcessorException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * stop processor
     */
    private void shutdownProcessor() {

        this.canalxController.pluginMgr.stopInjector();
    }
}
