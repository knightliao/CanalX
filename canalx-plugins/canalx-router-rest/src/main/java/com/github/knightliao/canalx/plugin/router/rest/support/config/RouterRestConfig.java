package com.github.knightliao.canalx.plugin.router.rest.support.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.config.IConfig;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;

import lombok.Data;

/**
 * @author knightliao
 * @date 2016/12/2 18:53
 */
@Data
public class RouterRestConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RouterRestConfig.class);

    private int serverPort = 8888;

    private String dataSource = "";

    @Override
    public void init(ICanalxContext iCanalxContext) throws Exception {
        // port
        String port = iCanalxContext.getProperty("canalx.plugin.router.port");

        //
        String dataSource = iCanalxContext.getProperty("canalx.plugin.router.datasource");

        serverPort = Integer.parseInt(port);
        this.dataSource = dataSource;

        LOGGER.debug("router config: port:{}, dataSource:{}", port, dataSource);
    }

    @Override
    public void init(Properties properties) throws Exception {

    }
}
