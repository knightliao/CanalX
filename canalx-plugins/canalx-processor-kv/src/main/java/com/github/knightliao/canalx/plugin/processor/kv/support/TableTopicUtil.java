package com.github.knightliao.canalx.plugin.processor.kv.support;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;

/**
 * @author knightliao
 * @date 2016/12/7 16:27
 */
public class TableTopicUtil {

    public static String getTableId(String db, String table) {

        return String.format(db + "." + table);
    }

    public static String getTableId(MysqlEntryWrap entry) {

        MysqlEntry mysqlEntry = entry.getMysqlEntry();
        String table = mysqlEntry.getTable();
        String db = mysqlEntry.getDb();

        return getTableId(db, table);
    }
}
