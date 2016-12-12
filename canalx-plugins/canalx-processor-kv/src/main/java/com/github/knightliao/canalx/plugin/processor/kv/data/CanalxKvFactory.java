package com.github.knightliao.canalx.plugin.processor.kv.data;

import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.ICanalxKv;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.impl.CanalxCodisKvImpl;
import com.github.knightliao.canalx.plugin.processor.kv.data.store.impl.CanalxKvImpl;

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

            return CanalxKvImpl.create().build();

        } else if (storeType.equals(StoreType.CODIS)) {

            return CanalxCodisKvImpl.getInstance(iCanalxContext);
        }

        throw new CanalxProcessorException("cannot find proper store type " + storeType);
    }

}
