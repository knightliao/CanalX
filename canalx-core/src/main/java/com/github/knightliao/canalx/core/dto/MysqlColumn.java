package com.github.knightliao.canalx.core.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Mysql 列名
 *
 * @author knightliao
 * @date 2016/11/23 16:47
 */
@Data
public class MysqlColumn {

    @SerializedName("n")
    private String name;

    @SerializedName("t")
    private String mysqlType;

    @SerializedName("v")
    private String value;

    @SerializedName("origin_val")
    private String originValue;

    @SerializedName("null")
    private boolean isNull;

    @SerializedName("updated")
    private boolean isUpdated;
}
