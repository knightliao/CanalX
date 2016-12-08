package com.github.knightliao.canalx.plugin.router.rest.support.server;

import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;

import lombok.Data;

/**
 * @author knightliao
 * @date 2016/12/7 15:18
 */
@Data
public class CanalxDataGetter {

    private static ICanalxDataRouter iCanalxDataRouter = null;

    public static String get(String tableId, String key) {
        if (iCanalxDataRouter != null) {

            if (tableId == null || key == null) {
                return "";
            }

            return iCanalxDataRouter.get(tableId, key);
        }

        return "";
    }

    public static void setupICanalxDataRouter(ICanalxDataRouter dataRouter) {
        iCanalxDataRouter = dataRouter;
    }
}
