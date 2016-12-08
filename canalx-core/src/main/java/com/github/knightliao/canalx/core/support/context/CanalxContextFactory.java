package com.github.knightliao.canalx.core.support.context;

import com.github.knightliao.canalx.core.support.context.impl.CanalxContextImpl;

/**
 * @author knightliao
 * @date 2016/11/24 16:05
 */
public class CanalxContextFactory {

    public static ICanalxContext getDefaultCanalxContext() {
        return new CanalxContextImpl();
    }
}
