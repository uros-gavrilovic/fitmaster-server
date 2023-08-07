package com.mastercode.fitmaster.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionHandler {
    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> exceptionHandler(LoginException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // Default status

        if (exception.getHttpStatus() != null) {
            httpStatus = exception.getHttpStatus();
        }

        return new ResponseEntity<>(errorMap, httpStatus);
    }
}