package com.github.knightliao.canalx.plugin.router.rest.support.server;

import com.github.knightliao.canalx.plugin.router.rest.support.server.impl.ICanalxRouterSeverImpl;

/**
 * @author knightliao
 * @date 2016/12/6 20:29
 */
public class CanalxRouterServerFactory {

    public static ICanalxRouterServer getDefaultRouterServer() {
        return new ICanalxRouterSeverImpl();
    }
}
