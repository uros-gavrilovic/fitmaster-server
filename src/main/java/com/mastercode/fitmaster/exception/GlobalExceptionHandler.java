package com.mastercode.fitmaster.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mastercode.fitmaster.dto.response.ErrorResponse;
import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.mastercode.fitmaster.util.ExceptionUtils.generateResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return generateResponse(
            ex,
            HttpStatus.INTERNAL_SERVER_ERROR,
            DescriptionUtils.getErrorDescription(ErrorConstants.GENERIC_ERROR),
            ex.getMessage()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
        return generateResponse(
            ex,
            HttpStatus.UNAUTHORIZED,
            DescriptionUtils.getErrorDescription(ErrorConstants.TOKEN_ERROR),
            DescriptionUtils.getErrorDescription(ErrorConstants.TOKEN_ERROR)
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
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            FieldError fieldError = e.getBindingResult().getFieldError();

            return generateResponse(
                ex,
                HttpStatus.UNPROCESSABLE_ENTITY,
                DescriptionUtils.getErrorDescription(ErrorConstants.VALIDATION_ERROR),
                DescriptionUtils.getInterpolatedErrorDescription(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
                )
            );
        } else {
            return generateResponse(
                ex,
                HttpStatus.UNPROCESSABLE_ENTITY,
                DescriptionUtils.getErrorDescription(ErrorConstants.VALIDATION_ERROR),
                ex.getMessage()
            );
        }
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
