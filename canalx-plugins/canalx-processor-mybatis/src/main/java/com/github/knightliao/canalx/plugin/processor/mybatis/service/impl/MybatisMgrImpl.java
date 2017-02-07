package com.github.knightliao.canalx.plugin.processor.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.knightliao.canalx.plugin.processor.mybatis.dao.ext.TestDao;
import com.github.knightliao.canalx.plugin.processor.mybatis.entity.Test;
import com.github.knightliao.canalx.plugin.processor.mybatis.service.MybatisMgr;

/**
 * @author knightliao
 * @date 2017/2/7 15:29
 */
@Service
public class MybatisMgrImpl implements MybatisMgr {

    @Autowired
    private TestDao testDao;

    @Override
    public void doWork() {
        Test test = new Test();
        test.setName("kkk");
        testDao.insert(test);
    }
}
