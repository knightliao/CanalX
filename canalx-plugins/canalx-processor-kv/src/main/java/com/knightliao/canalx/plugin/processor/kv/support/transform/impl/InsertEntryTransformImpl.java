package com.knightliao.canalx.plugin.processor.kv.support.transform.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.knightliao.canalx.core.dto.MysqlColumn;
import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.plugin.processor.kv.support.transform.IEntryTransform;
import com.knightliao.canalx.plugin.processor.kv.support.transform.TransformResult;

/**
 * @author knightliao
 * @date 2016/12/7 16:33
 */
public class InsertEntryTransformImpl implements IEntryTransform {

    protected static final Logger LOGGER = LoggerFactory.getLogger(InsertEntryTransformImpl.class);

    protected static Gson gson = new Gson();

    @Override
    public TransformResult entry2Json(MysqlEntry entry, String tableKey) {

        Map<String, String> map = new HashMap<>();

        String currentKeyValue = "";

        List<MysqlColumn> columns = entry.getColumns();
        for (MysqlColumn mysqlColumn : columns) {

            boolean isNull = mysqlColumn.isNull();
            String key = mysqlColumn.getName();
            String value = mysqlColumn.getValue();

            if (isNull) {
                map.put(key, "");
            } else {
                map.put(key, value);
            }

            if (key.equals(tableKey)) {
                currentKeyValue = value;
            }
        }

        if (currentKeyValue.equals("")) {
            LOGGER.error("cannot find key {} for table {} with entry {}", tableKey, entry.getTable(), entry);
            return null;

        } else {

            String value = gson.toJson(map);

            return new TransformResult(currentKeyValue, value);
        }
    }
}
