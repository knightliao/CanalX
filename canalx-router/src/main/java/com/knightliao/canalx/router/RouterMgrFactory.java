package com.knightliao.canalx.router;

import com.knightliao.canalx.router.impl.RouterMgrImpl;

/**
 * @author knightliao
 * @date 2016/11/24 14:43
 */
public class RouterMgrFactory {

    public static IRouterMgr getDefaultRouterMgr() {
        return new RouterMgrImpl();
    }
}
