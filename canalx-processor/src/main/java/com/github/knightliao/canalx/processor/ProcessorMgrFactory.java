package com.github.knightliao.canalx.processor;

import com.github.knightliao.canalx.processor.impl.ProcessorMgrImpl;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class ProcessorMgrFactory {

    public static IProcessorMgr getDefaultProcessorMgr() {
        return new ProcessorMgrImpl();
    }
}
