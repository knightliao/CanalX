package com.github.knightliao.canalx.core.test.support.reflection;

import org.junit.Test;

import com.github.knightliao.canalx.core.support.reflection.ReflectionUtil;

/**
 * @author knightliao
 * @date 2016/12/7 13:32
 */
public class ReflectionUtilTestCase {

    /**
     * 通过扫描，获取反射对象
     */
    @Test
    public void test() {

        ReflectionUtil.getReflection("com.github.knightliao.canalx");
    }
}
