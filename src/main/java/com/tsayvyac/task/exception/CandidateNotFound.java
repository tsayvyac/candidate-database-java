package com.tsayvyac.task.exception;

public class CandidateNotFound extends RuntimeException {
    public CandidateNotFound(String message) {
        super(message);
    }

    public CandidateNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
