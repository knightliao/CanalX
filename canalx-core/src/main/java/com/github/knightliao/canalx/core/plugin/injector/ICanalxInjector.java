package com.github.knightliao.canalx.core.plugin.injector;

import com.github.knightliao.canalx.core.exception.CanalxInjectorException;
import com.github.knightliao.canalx.core.exception.CanalxInjectorInitException;

/**
 * 数据输入 injector
 *
 * @author knightliao
 * @date 2016/11/23 18:21
 */
public interface ICanalxInjector {

    void init() throws CanalxInjectorInitException;

    void run() throws CanalxInjectorException;

    void shutdown() throws CanalxInjectorException;
}
