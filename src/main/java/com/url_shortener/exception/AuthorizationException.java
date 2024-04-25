package com.url_shortener.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class AuthorizationException extends Exception {

}
