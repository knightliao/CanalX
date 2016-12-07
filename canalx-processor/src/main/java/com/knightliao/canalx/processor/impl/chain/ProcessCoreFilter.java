package com.knightliao.canalx.processor.impl.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.knightliao.canalx.core.exception.CanalxProcessorException;
import com.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.knightliao.canalx.core.plugin.processor.IEntryFilter;
import com.knightliao.canalx.core.plugin.processor.IEntryFilterChain;

/**
 * @author knightliao
 * @date 2016/12/7 20:38
 */
public class ProcessCoreFilter implements IEntryFilter, ICanalxProcessorAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessCoreFilter.class);

    private ICanalxProcessor icanalxProcessor;

    @Override
    public void doFilter(MysqlEntryWrap entry, IEntryFilterChain iEntryFilterChain) throws CanalxProcessorException {

        // real do
        MysqlEntry mysqlEntry = entry.getMysqlEntry();
        if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_INSERT) {

            LOGGER.debug("run processor... {}  insert", icanalxProcessor.getClass());

            icanalxProcessor.processInsert(entry);

        } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_UPDATE) {

            LOGGER.debug("run processor... {}  update", icanalxProcessor.getClass());

            icanalxProcessor.processUpdate(entry);

        } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_DELETE) {

            LOGGER.debug("run processor... {} delete", icanalxProcessor.getClass());

            icanalxProcessor.processDelete(entry);
        }
    }

    @Override
    public void setupICanalxProcessor(ICanalxProcessor iCanalxProcessor) {
        this.icanalxProcessor = iCanalxProcessor;
    }
}
