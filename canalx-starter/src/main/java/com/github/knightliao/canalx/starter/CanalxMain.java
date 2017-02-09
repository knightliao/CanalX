package com.github.knightliao.canalx.starter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.context.CanalxContextFactory;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.starter.core.CanalxControllerFactory;
import com.github.knightliao.canalx.starter.core.ICanalxController;

/**
 * @author knightliao
 * @date 2016/11/24 14:05
 */
public class CanalxMain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxMain.class);

    /**
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) {

        // load profile
        ICanalxContext canalxProfile = CanalxContextFactory.getDefaultCanalxContext();
        try {
            canalxProfile.load();
        } catch (IOException e) {
            LOGGER.error(
                    "## something goes wrong when load profile:", e);
            System.exit(-1);
        }

        // run
        final ICanalxController canalxController = CanalxControllerFactory.getDefaultController();
        canalxController.run(canalxProfile);

        // shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    LOGGER.info("## stop CanalX");
                    canalxController.shutdown();
                } catch (Throwable e) {
                    LOGGER.warn(
                            "## something goes wrong when stopping the service:", e);
                } finally {

                    LOGGER.info("## CanalX is exit.");
                }
            }
        });
    }
}
