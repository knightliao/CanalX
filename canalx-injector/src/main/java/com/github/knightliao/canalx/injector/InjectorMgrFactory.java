package com.github.knightliao.canalx.injector;

import com.github.knightliao.canalx.injector.impl.InjectorMgrImpl;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class InjectorMgrFactory {

    public static InjectorMgr getDefaultInjectorMgr() {
        return new InjectorMgrImpl();
    }
}
