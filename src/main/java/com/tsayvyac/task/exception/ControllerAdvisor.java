package com.tsayvyac.task.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CandidateNotFound.class)
    public ResponseEntity<Object> handleCandidateNotFoundException(
            CandidateNotFound e,
            WebRequest request
            ) {
        return buildBody(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LevelBoundsException.class)
    public ResponseEntity<Object> handleLevelBoundsException(
            LevelBoundsException e,
            WebRequest request
    ) {
        return buildBody(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssociationNotFound.class)
    public ResponseEntity<Object> handleAssociationNotFoundException(
            AssociationNotFound e,
            WebRequest request
    ) {
        return buildBody(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TechnologyException.class)
    public ResponseEntity<Object> handleTechnologyException(
            TechnologyException e,
            WebRequest request
    ) {
        return buildBody(e, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildBody(RuntimeException e, WebRequest request, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        body.put("path", request.getDescription(false));
        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }
}
