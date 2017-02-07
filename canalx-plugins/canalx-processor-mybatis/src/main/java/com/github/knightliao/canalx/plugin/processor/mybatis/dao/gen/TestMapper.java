package com.github.knightliao.canalx.plugin.processor.mybatis.dao.gen;

import com.github.knightliao.canalx.plugin.processor.mybatis.entity.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}