package com.github.knightliao.canalx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Mysql entry
 *
 * @author knightliao
 * @date 2016/11/23 16:47
 */
@Data
@AllArgsConstructor
public class MysqlEntryWrap {

    /**
     * 来自哪一个topic
     */
    private String topic;

    private MysqlEntry mysqlEntry;

}
