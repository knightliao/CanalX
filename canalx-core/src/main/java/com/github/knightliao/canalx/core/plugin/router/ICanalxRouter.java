package com.github.knightliao.canalx.core.plugin.router;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;

/**
 * @author knightliao
 * @date 2016/12/2 18:42
 */
public interface ICanalxRouter {

    /**
     * @throws
     */
    void start() throws CanalxRouterException;

    void init() throws CanalxRouterException;

    void shutdown() throws CanalxRouterException;
}
