package com.mastercode.fitmaster.exception;


import com.mastercode.fitmaster.util.DescriptionUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ValidatorException extends RuntimeException {
    private final String title;
    private final HttpStatus httpStatus;

    public ValidatorException(String message) {
        super(message);
        this.title = DescriptionUtils.getErrorDescription("VALIDATION_ERROR");
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    }

}
