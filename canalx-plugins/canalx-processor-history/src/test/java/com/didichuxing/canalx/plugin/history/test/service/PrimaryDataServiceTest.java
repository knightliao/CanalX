package com.didichuxing.canalx.plugin.history.test.service;

import com.didichuxing.canalx.plugin.history.model.PrimaryData;
import com.didichuxing.canalx.plugin.history.service.PrimaryDataService;
import com.didichuxing.canalx.plugin.history.test.BaseTest;
import com.didichuxing.canalx.plugin.history.utils.HistoryPluginUtils;
import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by longkeyu on 2017/3/15.
 */
public class PrimaryDataServiceTest extends BaseTest{
    @Autowired
    private PrimaryDataService primaryDataService;
    @Test
    public void testInsert(){
        String content = "{\"binlog\":\"6599@mysql-bin.000042\",\"time\":1489576450000,\"canalTime\":1489576450780,\"db\":\"school\",\"table\":\"user\",\"event\":\"i\",\"columns\":[{\"n\":\"id\",\"t\":\"int(11)\",\"v\":\"61\",\"null\":false},{\"n\":\"name\",\"t\":\"varchar(222)\",\"v\":\"vvv\",\"null\":false}],\"keys\":[\"id\"],\"xid\":\"963\"}";
        Gson gson = new Gson();
        MysqlEntry entry = gson.fromJson(content, MysqlEntry.class);
        PrimaryData data = HistoryPluginUtils.convert(entry);
        primaryDataService.insert(data);
        PrimaryData primaryData = primaryDataService.selectByPrimaryKey(data.getId());
        Assert.assertNotNull(primaryData);
    }

}
