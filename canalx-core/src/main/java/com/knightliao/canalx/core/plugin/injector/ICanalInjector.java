package com.knightliao.canalx.core.plugin.injector;

import java.io.IOException;

import com.knightliao.canalx.core.exception.CanalxInjectorException;

/**
 * 数据输入 injector
 *
 * @author knightliao
 * @date 2016/11/23 18:21
 */
public interface ICanalInjector {

    void init() throws CanalxInjectorException;

    void run() throws CanalxInjectorException;

    void shutdown() throws CanalxInjectorException;
}
