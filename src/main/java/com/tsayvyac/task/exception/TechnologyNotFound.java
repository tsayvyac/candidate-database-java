package com.tsayvyac.task.exception;

public class TechnologyNotFound extends RuntimeException {
    public TechnologyNotFound(String message) {
        super(message);
    }

    public TechnologyNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
