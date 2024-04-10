package com.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends Exception {
    public DatabaseException() {

    }
    public DatabaseException(String message) {
        super(message);
    }
}
