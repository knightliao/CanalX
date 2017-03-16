package com.didichuxing.canalx.plugin.history.service;

import com.didichuxing.canalx.plugin.history.model.PrimaryData;
import com.github.knightliao.canalx.core.dto.MysqlEntry;

/**
 * Created by longkeyu on 2017/3/10.
 */
public interface PrimaryDataService {

    int insert(PrimaryData data);

    void updateTraceId(String traceId, String xid);

    PrimaryData selectByPrimaryKey(Long id);

}
