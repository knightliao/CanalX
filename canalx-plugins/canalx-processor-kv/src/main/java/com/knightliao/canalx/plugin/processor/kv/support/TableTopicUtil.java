package com.knightliao.canalx.plugin.processor.kv.support;

/**
 * @author knightliao
 * @date 2016/12/7 16:27
 */
public class TableTopicUtil {

    public static String getTableId(String topic, String table) {

        return String.format("{}.{}", topic, table);
    }
}
