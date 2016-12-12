package com.github.knightliao.canalx.core.test.plugin.processor.support.filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.EntryFilterChainFactory;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.IEntryFilter;
import com.github.knightliao.canalx.core.plugin.processor.support.filter.IEntryFilterChain;

/**
 * @author knightliao
 * @date 2016/12/10 17:16
 */
public class EntryFilterChainFactoryTestCase {

    @Test
    public void test() {

        List<IEntryFilter> filters = new ArrayList<>();

        IEntryFilter iEntryFilter = new IEntryFilter() {
            @Override
            public void doFilter(MysqlEntryWrap entry, IEntryFilterChain iEntryFilterChain)
                    throws CanalxProcessorException {

                iEntryFilterChain.doFilter(entry);
            }
        };
        filters.add(iEntryFilter);

        IEntryFilterChain iEntryFilterChain = EntryFilterChainFactory.getEntryFilterChain(filters);
        try {

            iEntryFilterChain.doFilter(new MysqlEntryWrap("topic", new MysqlEntry()));

        } catch (CanalxProcessorException e) {
            Assert.assertTrue(false);
        }
    }
}
