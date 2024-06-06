package com.example.urlshortener.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class UrlDoesNotExistException extends RuntimeException {

    private String errorCode;
    private HttpStatus statusCode;

    public UrlDoesNotExistException(String errorCode, String msg, HttpStatus statusCode) {
        super(msg);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public static UrlDoesNotExistException buildException(String errorCode, String msg, HttpStatus statusCode) {
        return new UrlDoesNotExistException(errorCode, msg, statusCode);
    }
}
