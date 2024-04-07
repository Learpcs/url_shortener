package com.url_shortener.exception;

public class URLValidationException extends Exception {
    public URLValidationException() {

    }
    public URLValidationException(String message) {
        super(message);
    }
}
