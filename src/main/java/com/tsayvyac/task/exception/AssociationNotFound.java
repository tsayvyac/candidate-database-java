package com.tsayvyac.task.exception;

public class AssociationNotFound extends RuntimeException {
    public AssociationNotFound(String message) {
        super(message);
    }

    public AssociationNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
