package com.knightliao.canalx.processor.exception;

/**
 *
 */
public class CanalxProcessorException extends RuntimeException {
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
