package com.arsgsg.URLShortener.presentation.common;

import com.arsgsg.URLShortener.presentation.UrlShortenerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {UrlShortenerController.class})
public class UrlShortenerControllerAdvice {
    @ExceptionHandler(UrlShortenerException.class)
    public ResponseEntity<ErrorResponse> UrlShortenerAdvice(UrlShortenerException e){
        final ErrorResponse response = new ErrorResponse(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidAdvice(MethodArgumentNotValidException e){
        final ErrorResponse response = new ErrorResponse(e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
