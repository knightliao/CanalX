package com.github.knightliao.canalx.core.support.context;

import java.io.IOException;
import java.util.Set;

import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.support.properties.IProperties;

/**
 * @author knightliao
 * @date 2016/12/6 19:30
 */
public interface ICanalxContext extends IProperties {

    /**
     * 获取 injector plugin name
     *
     * @return
     */
    Set<String> getInjectorPluginName();

    /**
     * 获取 processor plugin name
     *
     * @return
     */
    Set<String> getProcessorPluginName();

    /**
     * 获取router plugin name
     *
     * @return
     */
    Set<String> getRouterPluginName();

    /**
     * 载入
     *
     * @throws IOException
     */
    void load() throws IOException;

    /**
     * processor 作为数据源
     *
     * @param processorName
     *
     * @return
     */
    ICanalxDataRouter getProcessorAsDataSource(String processorName);
}
