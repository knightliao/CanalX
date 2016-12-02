package com.knightliao.canalx.core.exception;

/**
 *
 */
public class CanalxPluginException extends CanalxException {
    public CanalxPluginException() {
    }

    public CanalxPluginException(String message) {
        super(message);
    }

    public CanalxPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalxPluginException(Throwable cause) {
        super(cause);
    }
}
