package com.github.knightliao.canalx.starter.core;

import com.github.knightliao.canalx.starter.core.impl.CanalxControllerImpl;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class CanalxControllerFactory {

    public static ICanalxController getDefaultController() {
        return new CanalxControllerImpl();
    }
}
