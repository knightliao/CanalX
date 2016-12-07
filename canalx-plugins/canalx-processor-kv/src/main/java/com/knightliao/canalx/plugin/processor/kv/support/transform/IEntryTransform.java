package com.knightliao.canalx.plugin.processor.kv.support.transform;

import com.knightliao.canalx.core.dto.MysqlEntry;

/**
 * @author knightliao
 * @date 2016/12/7 16:32
 */
public interface IEntryTransform {

    TransformResult entry2Json(MysqlEntry entry, String tableKey);
}
