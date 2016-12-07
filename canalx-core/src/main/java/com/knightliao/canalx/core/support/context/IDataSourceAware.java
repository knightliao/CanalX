package com.knightliao.canalx.core.support.context;

import java.util.Map;

import com.knightliao.canalx.core.plugin.processor.ICanalxProcessor;

/**
 * @author knightliao
 * @date 2016/12/7 14:07
 */
public interface IDataSourceAware {

    void setDataSource(Map<String, ICanalxProcessor> iCanalxProcessorMap);
}
