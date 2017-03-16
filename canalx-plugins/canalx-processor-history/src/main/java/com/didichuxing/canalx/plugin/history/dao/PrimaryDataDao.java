package com.didichuxing.canalx.plugin.history.dao;

import com.didichuxing.canalx.plugin.history.mapper.PrimaryDataMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by longkeyu on 2017/3/12.
 */
public interface PrimaryDataDao extends PrimaryDataMapper {

    int updateTraceId(@Param("traceId") String traceId, @Param("xid") String xid);

}
