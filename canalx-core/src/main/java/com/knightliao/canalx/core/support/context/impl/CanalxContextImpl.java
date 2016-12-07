package com.knightliao.canalx.core.support.context.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import com.knightliao.canalx.core.support.context.ICanalxContext;

/**
 * @author knightliao
 * @date 2016/12/6 19:32
 */
public class CanalxContextImpl implements ICanalxContext {
    private final Properties properties;

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

    @Override
    public String getProperty(String item) {
        return properties.getProperty(item);
    }
}
