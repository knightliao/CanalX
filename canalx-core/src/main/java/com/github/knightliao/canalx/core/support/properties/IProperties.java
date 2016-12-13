package com.github.knightliao.canalx.core.support.properties;

/**
 * @author knightliao
 * @date 2016/12/6 19:26
 */
public interface IProperties {

    String getProperty(String item, String defaultValue);

    String getProperty(String item);
}
