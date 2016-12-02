package com.knightliao.canalx.plugin.processor.kv.config;

import lombok.Data;

@Data
public class TableConfig {

    String dbName;

    String dbUrl;

    String userName;

    String password;

    String tableName;

    String initSql;

    String identify;

    public void genIdentify() {
        this.identify = this.dbName + "." + this.tableName;
    }
}
