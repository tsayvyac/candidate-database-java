package com.tsayvyac.task.exception;

public class LevelBoundsException extends RuntimeException {
    public LevelBoundsException(String message) {
        super(message);
    }

    public LevelBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
