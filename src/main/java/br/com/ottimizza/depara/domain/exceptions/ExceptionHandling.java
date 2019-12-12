package br.com.ottimizza.depara.domain.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ottimizza.depara.domain.responses.ErrorResponse;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;

import java.util.Locale;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({ RuntimeException.class })
    public HttpEntity<?> handleRunTimeException(RuntimeException e, Locale locale) {
        return error(INTERNAL_SERVER_ERROR, "internal_server_error", e.getMessage(), e);
    }

    private HttpEntity<?> error(HttpStatus status, String error, String errorDescription, Exception e) {
        e.printStackTrace();
        ErrorResponse response = new ErrorResponse(error, errorDescription);
        return ResponseEntity.status(status).body(response);
    }

}