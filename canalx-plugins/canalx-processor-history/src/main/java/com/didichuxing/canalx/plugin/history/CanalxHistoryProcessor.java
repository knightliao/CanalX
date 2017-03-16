package com.didichuxing.canalx.plugin.history;

import com.didichuxing.canalx.plugin.history.model.PrimaryData;
import com.didichuxing.canalx.plugin.history.service.PrimaryDataService;
import com.didichuxing.canalx.plugin.history.utils.HistoryPluginUtils;
import com.github.knightliao.canalx.core.dto.MysqlColumn;
import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.google.gson.Gson;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by longkeyu on 2017/3/9.
 */
@PluginName(name = "canalx-processor-history")
public class CanalxHistoryProcessor implements ICanalxProcessor, ICanalxContextAware {

    private PrimaryDataService primaryDataService;

    private Gson gson;

    private ICanalxContext context;

    private String traceTableName;

    @Override
    public void processUpdate(MysqlEntryWrap entry) throws CanalxProcessorException {
        reduce(entry);
    }

    @Override
    public void processInsert(MysqlEntryWrap entry) throws CanalxProcessorException {
        reduce(entry);
    }

    @Override
    public void processDelete(MysqlEntryWrap entry) throws CanalxProcessorException {
        reduce(entry);
    }

    @Override
    public void init() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        primaryDataService = ctx.getBean(PrimaryDataService.class);
        gson = new Gson();
        traceTableName = context.getProperty("canalx.trace.table.name");
    }

    @Override
    public void shutdown() {

    }

    private void reduce(MysqlEntryWrap wrap){
        MysqlEntry mysqlEntry = wrap.getMysqlEntry();
        String table = mysqlEntry.getTable();
        if (table.equals(traceTableName)){
            String traceId = null;
            String xid = mysqlEntry.getXid();
            List<MysqlColumn> columns = mysqlEntry.getColumns();
            for (MysqlColumn c : columns){
                if (c.getName().equals("trace_id")){
                    traceId = c.getValue();
                }
            }
            primaryDataService.updateTraceId(traceId, xid);
        }else {
            primaryDataService.insert(HistoryPluginUtils.convert(mysqlEntry));
        }

    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        context = iCanalxContext;
    }
}
