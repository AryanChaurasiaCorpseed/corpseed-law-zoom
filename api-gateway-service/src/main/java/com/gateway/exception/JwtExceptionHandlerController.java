package com.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandlerController {

    @ExceptionHandler(value = JwtTokenMalformedException.class)
    public ResponseEntity<Object> jwtMalFormedException(JwtTokenMalformedException e){
        System.out.println("JwtTokenMalformedException Exception occurred going to throw...");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtTokenMissingException.class)
    public ResponseEntity<Object> jwtTokenMissingException(JwtTokenMissingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
