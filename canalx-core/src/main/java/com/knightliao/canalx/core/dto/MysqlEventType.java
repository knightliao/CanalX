package com.knightliao.canalx.core.dto;

/**
 * 消息类似
 */
public enum MysqlEventType {

    UPDATE('u'), INSERT('i'), DELETE('d');
    private char type;

    MysqlEventType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}
