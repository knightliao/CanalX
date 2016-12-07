package com.knightliao.canalx.core.support.context;

import java.io.IOException;
import java.util.Set;

import com.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.knightliao.canalx.core.support.properties.IProperties;

/**
 * @author knightliao
 * @date 2016/12/6 19:30
 */
public interface ICanalxContext extends IProperties {

    Set<String> getInjectorPluginName();

    Set<String> getProcessorPluginName();

    Set<String> getRouterPluginName();

    void load() throws IOException;

    ICanalxDataRouter getProcessorAsDataSource(String processorName);
}
