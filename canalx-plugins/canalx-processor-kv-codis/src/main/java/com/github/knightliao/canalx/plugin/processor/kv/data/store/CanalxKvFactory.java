package com.github.knightliao.canalx.plugin.processor.kv.data.store;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.impl.CanalxKvStoreCodisKvImpl;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.impl.CanalxKvStoreMemImpl;

/**
 * @author knightliao
 * @date 2016/11/28 17:37
 */
public class CanalxKvFactory {

    /**
     * @param storeType
     * @param iCanalxContext
     *
     * @return
     *
     * @throws CanalxProcessorException
     */
    public static ICanalxKv getStoreInstance(StoreType storeType, ICanalxContext iCanalxContext) throws
            CanalxProcessorException {

        if (storeType.equals(StoreType.KV)) {

            return CanalxKvStoreMemImpl.create().build();

        } else if (storeType.equals(StoreType.CODIS)) {

            return CanalxKvStoreCodisKvImpl.getInstance(iCanalxContext);
        }

        throw new CanalxProcessorException("cannot find proper store type " + storeType);
    }

}
