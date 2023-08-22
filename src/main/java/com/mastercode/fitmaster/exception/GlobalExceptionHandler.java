package com.mastercode.fitmaster.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mastercode.fitmaster.util.DescriptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // TODO: Eliminate code redundancy.

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        System.out.println(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(DescriptionUtils.getErrorDescription("ERROR"), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
        ErrorResponse errorResponse = new ErrorResponse(DescriptionUtils.getErrorDescription("TOKEN_ERROR"), DescriptionUtils.getErrorDescription("TOKEN_ERROR"));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleLoginException(LoginException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getTitle(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRegisterException(RegisterException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getTitle(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
