package com.knightliao.canalx.plugin.router.rest.support.server;

import com.knightliao.canalx.core.exception.CanalxRouterException;

/**
 * @author knightliao
 * @date 2016/12/6 20:29
 */
public interface ICanalxRouterServer {

    void start(int port) throws CanalxRouterException;

    void stop() throws CanalxRouterException;

}
