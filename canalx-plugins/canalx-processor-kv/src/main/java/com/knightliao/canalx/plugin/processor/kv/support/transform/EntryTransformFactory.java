package com.knightliao.canalx.plugin.processor.kv.support.transform;

import com.knightliao.canalx.plugin.processor.kv.support.transform.impl.InsertEntryTransformImpl;
import com.knightliao.canalx.plugin.processor.kv.support.transform.impl.UpdateEntryTransformImpl;

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
