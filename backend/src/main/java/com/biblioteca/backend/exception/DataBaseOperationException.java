package com.biblioteca.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DataBaseOperationException extends RuntimeException  {
    public DataBaseOperationException(String message) {
        super(message);
    }

    public DataBaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
