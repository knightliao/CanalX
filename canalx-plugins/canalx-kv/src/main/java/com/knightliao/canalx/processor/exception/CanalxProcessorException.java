package com.knightliao.canalx.processor.exception;

import com.knightliao.canalx.core.exception.CanalxException;

/**
 *
 */
public class CanalxProcessorException extends CanalxException {
    public CanalxProcessorException() {
    }

    public CanalxProcessorException(String message) {
        super(message);
    }

    public CanalxProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalxProcessorException(Throwable cause) {
        super(cause);
    }
}
