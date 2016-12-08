package com.github.knightliao.canalx.core.plugin.processor;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;

/**
 * @author knightliao
 * @date 2016/12/7 19:34
 */
public interface IEntryFilter {

    void doFilter(MysqlEntryWrap entry, IEntryFilterChain iEntryFilterChain) throws CanalxProcessorException;

}
