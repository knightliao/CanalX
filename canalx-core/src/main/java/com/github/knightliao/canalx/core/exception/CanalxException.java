package com.github.knightliao.canalx.core.exception;

/**
 *
 */
public class CanalxException extends Exception {
    public CanalxException() {
    }

    public CanalxException(String message) {
        super(message);
    }

    public CanalxException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalxException(Throwable cause) {
        super(cause);
    }
}
