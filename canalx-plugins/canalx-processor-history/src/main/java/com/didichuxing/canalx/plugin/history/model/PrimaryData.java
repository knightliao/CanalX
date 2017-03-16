package com.didichuxing.canalx.plugin.history.model;

import java.util.Date;

public class PrimaryData {
    private Long id;

    private Long userId;

    private String userName;

    private String userIp;

    private String traceId;

    private String xid;

    private Integer status;

    private Date logTime;

    private Date canalTime;

    private String binlog;

    private String dbName;

    private String tableName;

    private String tableEvent;

    private String tableKeys;

    private Date createTime;

    private Date updateTime;

    private String tableColumns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId == null ? null : traceId.trim();
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid == null ? null : xid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getCanalTime() {
        return canalTime;
    }

    public void setCanalTime(Date canalTime) {
        this.canalTime = canalTime;
    }

    public String getBinlog() {
        return binlog;
    }

    public void setBinlog(String binlog) {
        this.binlog = binlog == null ? null : binlog.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getTableEvent() {
        return tableEvent;
    }

    public void setTableEvent(String tableEvent) {
        this.tableEvent = tableEvent == null ? null : tableEvent.trim();
    }

    public String getTableKeys() {
        return tableKeys;
    }

    public void setTableKeys(String tableKeys) {
        this.tableKeys = tableKeys == null ? null : tableKeys.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(String tableColumns) {
        this.tableColumns = tableColumns == null ? null : tableColumns.trim();
    }
}