package com.github.knightliao.canalx.core.plugin;

import java.util.Set;

import com.github.knightliao.canalx.core.exception.CanalxPluginException;

/**
 * @author knightliao
 * @date 2016/12/1 11:36
 */
public interface IPlugin {

    // load
    void loadPlugin(String scanPack, Set<String> specifyPluginNames) throws CanalxPluginException;
}
