package com.mastercode.fitmaster.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mastercode.fitmaster.dto.response.ErrorResponse;
import com.mastercode.fitmaster.util.CustomLogger;
import com.mastercode.fitmaster.util.DescriptionUtils;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> generateResponse(Exception ex, HttpStatus status, String title, String message) {
        CustomLogger.error(ex.getMessage());

        return new ResponseEntity<>(
            new ErrorResponse(title, message),
            status
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return generateResponse(
            ex,
            HttpStatus.INTERNAL_SERVER_ERROR,
            DescriptionUtils.getErrorDescription("ERROR"),
            ex.getMessage()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
        return generateResponse(
            ex,
            HttpStatus.UNAUTHORIZED,
            DescriptionUtils.getErrorDescription("TOKEN_ERROR"),
            DescriptionUtils.getErrorDescription("TOKEN_ERROR")
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleLoginException(LoginException ex) {
        return generateResponse(
            ex,
            ex.getHttpStatus(),
            ex.getTitle(),
            ex.getMessage()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRegisterException(RegisterException ex) {
        return generateResponse(
            ex,
            ex.getHttpStatus(),
            ex.getTitle(),
            ex.getMessage()
        );
    }

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleValidatorException(Exception ex) {
        return generateResponse(
            ex,
            HttpStatus.UNPROCESSABLE_ENTITY,
            DescriptionUtils.getErrorDescription("VALIDATION_ERROR"),
            ex.getMessage()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePackageHasActiveMembershipsException(PackageHasActiveMembershipsException ex) {
        return generateResponse(
            ex,
            HttpStatus.FORBIDDEN,
            ex.getTitle(),
            ex.getMessage()
        );
    }
}
