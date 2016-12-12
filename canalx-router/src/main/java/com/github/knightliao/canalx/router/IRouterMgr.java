package com.github.knightliao.canalx.router;

import java.util.List;

import com.github.knightliao.canalx.core.exception.CanalxRouterException;
import com.github.knightliao.canalx.core.plugin.router.ICanalxRouter;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;

/**
 * @author knightliao
 * @date 2016/12/1 11:34
 */
public interface IRouterMgr extends ICanalxContextAware {

    /**
     * @return
     */
    List<ICanalxRouter> getRouterPlugin();

    /**
     * 执行
     */
    void runRouter() throws CanalxRouterException;

    /**
     *
     */
    void init() throws CanalxRouterException;

    void shutdown();
}
