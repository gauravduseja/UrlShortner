package com.example.urlshortener.exception;

import com.example.urlshortener.model.view.ErrorDetailsResponse;
import com.example.urlshortener.model.view.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlDoesNotExistException.class)
    public ResponseEntity<GenericResponse> handleUrlDoesNotExistException(UrlDoesNotExistException exception,
                                                                              HttpServletRequest request) {
        GenericResponse response = new GenericResponse();
        response.setSuccess(false);
        response.setErrorDetailsResponse(ErrorDetailsResponse.builder()
                .code(exception.getErrorCode())
                .description(exception.getMessage())
                .build());
        return new ResponseEntity<>(response, exception.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<GenericResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        GenericResponse response = new GenericResponse();
        response.setSuccess(false);
        response.setErrorDetailsResponse(ErrorDetailsResponse.builder()
                .code(ex.getCause().getMessage())
                .description(ex.getMessage())
                .build());

        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }


}
