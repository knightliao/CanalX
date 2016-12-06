package com.knightliao.canalx.router.rest.support.config;

import com.knightliao.canalx.core.support.context.ICanalxContext;

import lombok.Data;

/**
 * @author knightliao
 * @date 2016/12/2 18:53
 */
@Data
public class RouterRestConfig {

    private int serverPort = 8080;

    public void initConfig(ICanalxContext iCanalxContext) throws Exception {

        // port
        String port = iCanalxContext.getProperty("canalx.plugin.router.port");

        serverPort = Integer.parseInt(port);
    }
}
