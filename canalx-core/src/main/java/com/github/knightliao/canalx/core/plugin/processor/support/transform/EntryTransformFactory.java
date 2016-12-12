package com.github.knightliao.canalx.core.plugin.processor.support.transform;

import com.github.knightliao.canalx.core.plugin.processor.support.transform.impl.InsertEntryTransformImpl;
import com.github.knightliao.canalx.core.plugin.processor.support.transform.impl.UpdateEntryTransformImpl;

/**
 * @author knightliao
 * @date 2016/12/7 16:32
 */
public class EntryTransformFactory {

    public static IEntryTransform getInsertTransform() {
        return new InsertEntryTransformImpl();
    }

    public static IEntryTransform getUpdateTransform() {
        return new UpdateEntryTransformImpl();
    }
}
