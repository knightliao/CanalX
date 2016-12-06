package com.knightliao.canalx.core.plugin.router;

import com.knightliao.canalx.core.exception.CanalxRouterException;

/**
 * @author knightliao
 * @date 2016/12/2 18:42
 */
public interface ICanalxRouter {

    /**
     * @throws
     */
    void start() throws CanalxRouterException;

    void init();

    void shutdown() throws CanalxRouterException;
}
