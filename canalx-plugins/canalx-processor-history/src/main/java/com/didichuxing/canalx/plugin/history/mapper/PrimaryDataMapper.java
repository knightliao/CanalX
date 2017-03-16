package com.didichuxing.canalx.plugin.history.mapper;

import com.didichuxing.canalx.plugin.history.model.PrimaryData;

public interface PrimaryDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrimaryData record);

    int insertSelective(PrimaryData record);

    PrimaryData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrimaryData record);

    int updateByPrimaryKeyWithBLOBs(PrimaryData record);

    int updateByPrimaryKey(PrimaryData record);
}