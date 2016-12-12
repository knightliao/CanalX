package com.github.knightliao.canalx.core.support.context.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.IDataSourceAware;

/**
 * @author knightliao
 * @date 2016/12/6 19:32
 */
public class CanalxContextImpl implements ICanalxContext, IDataSourceAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxContextImpl.class);

    private final Properties properties;

    // data source provider
    private Map<String, ICanalxProcessor> iCanalxProcessorMap;

    public CanalxContextImpl() {
        properties = new Properties();
    }

    @Override
    public Set<String> getInjectorPluginName() {

        String injectorStr = properties.getProperty("canalx.plugin.injector", "");
        Set<String> ret = new LinkedHashSet<>(10);
        String[] data = injectorStr.split(",");
        for (String item : data) {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public Set<String> getProcessorPluginName() {
        String processorStr = properties.getProperty("canalx.plugin.processor", "");
        Set<String> ret = new LinkedHashSet<>(10);
        String[] data = processorStr.split(",");
        for (String item : data) {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public Set<String> getRouterPluginName() {
        String processorStr = properties.getProperty("canalx.plugin.router", "");
        Set<String> ret = new LinkedHashSet<>(10);
        String[] data = processorStr.split(",");
        for (String item : data) {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public void load() throws IOException {
        URL url = CanalxContextImpl.class.getClassLoader().getResource("canalx.properties");
        properties.load(new InputStreamReader(new FileInputStream(url.getPath()), "utf-8"));
    }

    /**
     * 获取某一个 processor 作为 router 的数据源
     *
     * @param processorName
     *
     * @return
     */
    @Override
    public ICanalxDataRouter getProcessorAsDataSource(String processorName) {

        if (processorName != null) {
            for (String iCanalxProcessorName : iCanalxProcessorMap.keySet()) {
                if (iCanalxProcessorName.equals(processorName)) {

                    ICanalxProcessor iCanalxProcessor = iCanalxProcessorMap.get(processorName);
                    if (iCanalxProcessor instanceof ICanalxDataRouter) {
                        return (ICanalxDataRouter) iCanalxProcessor;
                    }
                }
            }

            LOGGER.warn("cannot get any data source as rest input: {} ", processorName);
        }

        return null;
    }

    @Override
    public String getProperty(String item) {
        return properties.getProperty(item);
    }

    /**
     * 设置数据源
     *
     * @param iCanalxProcessorMap
     */
    @Override
    public void setDataSource(Map<String, ICanalxProcessor> iCanalxProcessorMap) {
        this.iCanalxProcessorMap = iCanalxProcessorMap;
    }
}
