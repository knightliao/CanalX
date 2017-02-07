package com.github.knightliao.canalx.plugin.processor.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.github.knightliao.canalx.plugin.processor.mybatis.service.MybatisMgr;
import com.github.knightliao.canalx.plugin.processor.mybatis.service.impl.MybatisMgrImpl;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-processor-mybatis")
public class CanalxMybatisProcessor implements ICanalxProcessor, ICanalxDataRouter, ICanalxContextAware {

    private ICanalxContext iCanalxContext = null;

    private MybatisMgr mybatisMgr = null;

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.iCanalxContext = iCanalxContext;
    }

    @Override
    public String get(String tableId, String key) {
        return null;
    }

    @Override
    public void processUpdate(MysqlEntryWrap entry) throws CanalxProcessorException {
        if (mybatisMgr != null) {
            mybatisMgr.doWork();
        }
    }

    @Override
    public void processInsert(MysqlEntryWrap entry) throws CanalxProcessorException {

    }

    @Override
    public void processDelete(MysqlEntryWrap entry) throws CanalxProcessorException {

    }

    @Override
    public void init() {

        // open/read the application context file
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        mybatisMgr = (MybatisMgr) ctx.getBean(MybatisMgrImpl.class);

    }

    @Override
    public void shutdown() {

    }
}
