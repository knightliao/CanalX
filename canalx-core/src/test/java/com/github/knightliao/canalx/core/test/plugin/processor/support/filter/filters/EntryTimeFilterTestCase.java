package com.github.knightliao.canalx.core.test.plugin.processor.support.filter.filters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.EntryFilterChainFactory;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.IEntryFilter;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.IEntryFilterChain;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.filters.EntryTimeFilter;
import com.github.knightliao.test.log.logback.LogbackCapturingAppender;

/**
 * @author knightliao
 * @date 2016/12/12 23:29
 */
public class EntryTimeFilterTestCase {

    @After
    public void cleanUp() {
        LogbackCapturingAppender.Factory.cleanUp();
    }

    @Test
    public void testHasIgnore() {

        // Given logger
        LogbackCapturingAppender capturing = LogbackCapturingAppender.Factory.weaveInto(EntryTimeFilter.getLOGGER());

        IEntryFilter entryFilter = new EntryTimeFilter();

        List<IEntryFilter> filters = new ArrayList<>();
        filters.add(entryFilter);
        filters.add(entryFilter);
        IEntryFilterChain entryFilterChain = EntryFilterChainFactory.getEntryFilterChain(filters);

        try {

            MysqlEntry mysqlEntry = new MysqlEntry();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long currentTime = timestamp.getTime();
            mysqlEntry.setTime(currentTime - 5000 * 4);
            entryFilterChain.doFilter(new MysqlEntryWrap("topic", mysqlEntry));

        } catch (CanalxProcessorException e) {
            Assert.assertTrue(false);
        }

        MatcherAssert.assertThat(capturing.getCapturedLogMessage(), StringContains.containsString("ignore entry"));

    }

    @Test
    public void testOk() {

        // Given logger
        LogbackCapturingAppender capturing = LogbackCapturingAppender.Factory.weaveInto(EntryTimeFilter.getLOGGER());

        IEntryFilter entryFilter = new EntryTimeFilter();

        List<IEntryFilter> filters = new ArrayList<>();
        filters.add(entryFilter);
        filters.add(entryFilter);
        IEntryFilterChain entryFilterChain = EntryFilterChainFactory.getEntryFilterChain(filters);

        try {

            MysqlEntry mysqlEntry = new MysqlEntry();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long currentTime = timestamp.getTime();
            mysqlEntry.setTime(currentTime - 1000 * 2);
            entryFilterChain.doFilter(new MysqlEntryWrap("topic", mysqlEntry));

        } catch (CanalxProcessorException e) {
            Assert.assertTrue(false);
        }

        Assert.assertEquals(capturing.getCapturedLogMessage(), null);

    }
}
