package com.github.knightliao.canalx.processor.impl.chain;

import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;

/**
 * @author knightliao
 * @date 2016/12/7 20:39
 */
public interface ICanalxProcessorAware {

    void setupICanalxProcessor(ICanalxProcessor iCanalxProcessor);
}
