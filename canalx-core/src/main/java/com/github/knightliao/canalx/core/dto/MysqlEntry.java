package com.github.knightliao.canalx.core.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * Mysql entry
 *
 * @author knightliao
 * @date 2016/11/23 16:47
 */
@Data
public class MysqlEntry {

    private String binlog; // binlog file & offset

    private long time;

    private long canalTime;

    private String db;

    private String table;

    private char event;

    // 列
    private List<MysqlColumn> columns = new ArrayList<>();

    // keys
    private Set<String> keys = new HashSet<>();

    private String xid;

    public static final char MYSQL_INSERT = 'i';
    public static final char MYSQL_UPDATE = 'u';
    public static final char MYSQL_DELETE = 'd';
}
