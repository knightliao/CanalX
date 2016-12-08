package com.github.knightliao.canalx.core.support.config;

import com.github.knightliao.canalx.core.support.context.ICanalxContext;

/**
 * @author knightliao
 * @date 2016/12/8 17:51
 */
public interface IConfig {

    void init(ICanalxContext iCanalxContext) throws Exception;

    void init() throws Exception;
}
