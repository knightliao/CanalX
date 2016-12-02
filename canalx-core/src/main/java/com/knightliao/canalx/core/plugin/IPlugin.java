package com.knightliao.canalx.core.plugin;

import java.util.Set;

/**
 * @author knightliao
 * @date 2016/12/1 11:36
 */
public interface IPlugin {

    // load
    void loadPlugin(String scanPack, Set<String> specifyPluginNames);
}
