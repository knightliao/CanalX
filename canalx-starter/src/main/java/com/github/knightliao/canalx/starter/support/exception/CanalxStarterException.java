package com.github.knightliao.canalx.starter.support.exception;

import com.github.knightliao.canalx.core.exception.CanalxException;

/**
 *
 */
public class CanalxStarterException extends CanalxException {
    public CanalxStarterException() {
    }

    public CanalxStarterException(String message) {
        super(message);
    }

    public CanalxStarterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalxStarterException(Throwable cause) {
        super(cause);
    }
}
