package com.knightliao.canalx.router.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxRouterException;
import com.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.knightliao.canalx.core.support.annotation.PluginName;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-router-rest")
public class CanalxRouterRest implements ICanalxRouter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxRouterRest.class);

    //
    private final static String CONFIG_FILE_NAME = "canalx-router-rest.properties";

    @Override
    public void start() throws CanalxRouterException {

    }

    @Override
    public void init() {

    }

    @Override
    public void shutdown() throws CanalxRouterException {

    }

    private void loadConfigAndInit() throws IOException {

        Properties kafkaProps = new Properties();
        URL url = CanalxRouterRest.class.getClassLoader().getResource(CONFIG_FILE_NAME);
        if (url != null) {
            LOGGER.info("loading config file {}", url.toString());

            kafkaProps.load(new InputStreamReader(new FileInputStream(url.getPath()),
                    "utf-8"));


        } else {

            LOGGER.warn("cannot find config file {}", CONFIG_FILE_NAME);
        }
    }
}
