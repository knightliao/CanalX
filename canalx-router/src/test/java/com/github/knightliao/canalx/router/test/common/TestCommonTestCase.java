package com.github.knightliao.canalx.router.test.common;

import org.junit.Test;

import com.github.knightliao.test.support.utils.TestUtils;

/**
 * @author knightliao
 * @date 2016/12/10 14:47
 */
public class TestCommonTestCase {

    @Test
    public void test() {

        TestUtils.testAllClassUnderPackage("com.github.knightliao.canalx.router");
    }
}
