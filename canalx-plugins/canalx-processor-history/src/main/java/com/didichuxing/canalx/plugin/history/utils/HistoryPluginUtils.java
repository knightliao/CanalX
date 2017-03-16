package com.didichuxing.canalx.plugin.history.utils;

import com.didichuxing.canalx.plugin.history.model.PrimaryData;
import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by longkeyu on 2017/3/15.
 */
public class HistoryPluginUtils {

    private static Gson gson = new Gson();

    public static PrimaryData convert(MysqlEntry entry){
        PrimaryData data = new PrimaryData();
        data.setBinlog(entry.getBinlog());
        data.setLogTime(new Date(entry.getTime()));
        data.setCanalTime(new Date(entry.getCanalTime()));
        data.setDbName(entry.getDb());
        data.setTableName(entry.getTable());
        data.setTableEvent(String.valueOf(entry.getEvent()));
        data.setTableColumns(gson.toJson(entry.getColumns()));
        data.setTableKeys(gson.toJson(entry.getKeys()));
        data.setXid(entry.getXid());
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        return data;
    }
}
