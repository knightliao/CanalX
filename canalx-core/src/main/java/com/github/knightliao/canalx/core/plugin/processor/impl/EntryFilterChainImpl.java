package com.github.knightliao.canalx.core.plugin.processor.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.support.IEntryFilter;
import com.github.knightliao.canalx.core.plugin.processor.support.IEntryFilterChain;

/**
 * @author knightliao
 * @date 2016/12/7 20:19
 */
public class EntryFilterChainImpl implements IEntryFilterChain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EntryFilterChainImpl.class);

    final List<IEntryFilter> chain;
    int curFilter = 0;

    public EntryFilterChainImpl(List<IEntryFilter> filters) {
        chain = filters;
    }

    @Override
    public void doFilter(MysqlEntryWrap entry) throws CanalxProcessorException {

        // pass to next filter
        if (curFilter < chain.size()) {

            IEntryFilter entryFilter = chain.get(curFilter);

            curFilter += 1;

            LOGGER.debug("call filter " + entryFilter.getClass().toString());

            entryFilter.doFilter(entry, this);
        }

        curFilter = 0;
    }
}
