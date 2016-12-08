package com.github.knightliao.canalx.plugin.processor.kv.support.filter;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.IEntryFilter;
import com.github.knightliao.canalx.core.plugin.processor.IEntryFilterChain;

/**
 * @author knightliao
 * @date 2016/12/7 19:43
 */
public class EntryTimeFilter implements IEntryFilter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EntryTimeFilter.class);

    /**
     * 过滤掉LOG日志比当前时间小较多的数据, 避免长时间处理旧数据
     *
     * @param entry
     * @param iEntryFilterChain
     *
     * @throws CanalxProcessorException
     */
    @Override
    public void doFilter(MysqlEntryWrap entry, IEntryFilterChain iEntryFilterChain) throws CanalxProcessorException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long currentTime = timestamp.getTime();

        long logTime = entry.getMysqlEntry().getTime();

        // 5s
        if (logTime + 1000 * 5 < currentTime) {

            LOGGER.warn("ignore entry because of smaller time logTime:{}, currentTime:{}, entry{} ", logTime,
                    currentTime, entry);

        } else {
            iEntryFilterChain.doFilter(entry);
        }
    }
}
