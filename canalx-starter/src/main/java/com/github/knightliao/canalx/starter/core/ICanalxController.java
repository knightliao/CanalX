package com.github.knightliao.canalx.starter.core;

import com.github.knightliao.canalx.core.support.context.ICanalxContext;

/**
 * @author knightliao
 * @date 2016/11/24 14:07
 */
public interface ICanalxController {

    void run(ICanalxContext canalxProfile);

    void shutdown();
}
