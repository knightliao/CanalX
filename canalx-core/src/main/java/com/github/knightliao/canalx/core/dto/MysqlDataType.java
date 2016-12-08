package com.github.knightliao.canalx.core.dto;

/**
 * 常用的MYSQL数据类型枚举
 *
 * @author knightliao
 * @date 2016/11/23 16:47
 */
public enum MysqlDataType {
    TINYINT("tinyint"), BOOL("bool"), SMALLINT("smallint"), MEDIUMINT(
            "mediumint"), INT("int"), INTEGER("integer"), BIGINT("bigint"), DECIMAL(
            "decimal"), FLOAT("float"), DOUBLE("double"), VARCHAR("varchar"), TIMESTAMP(
            "timestamp"), DATE("date");

    private String typeName;

    MysqlDataType(String typename) {
        this.typeName = typename;
    }

    public String getTypeName() {
        return typeName;
    }
}
