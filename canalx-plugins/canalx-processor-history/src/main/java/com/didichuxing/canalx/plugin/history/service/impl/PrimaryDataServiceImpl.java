package com.didichuxing.canalx.plugin.history.service.impl;

import com.didichuxing.canalx.plugin.history.dao.PrimaryDataDao;
import com.didichuxing.canalx.plugin.history.model.PrimaryData;
import com.didichuxing.canalx.plugin.history.service.PrimaryDataService;
import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by longkeyu on 2017/3/10.
 */
@Service("primaryDataService")
@Transactional
public class PrimaryDataServiceImpl implements PrimaryDataService{

    @Autowired
    private PrimaryDataDao primaryDataDao;

    @Override
    public int insert(PrimaryData data) {
        return primaryDataDao.insertSelective(data);
    }

    @Override
    public void updateTraceId(String traceId, String xid) {
        primaryDataDao.updateTraceId(traceId, xid);
    }

    @Override
    public PrimaryData selectByPrimaryKey(Long id) {
        return primaryDataDao.selectByPrimaryKey(id);
    }
}
