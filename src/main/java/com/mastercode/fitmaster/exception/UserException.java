package com.mastercode.fitmaster.exception;

import com.mastercode.fitmaster.util.DescriptionUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException extends RuntimeException {
    private final String title;
    private final HttpStatus httpStatus;

    public UserException(String message, HttpStatus httpStatus) {
        super(message);
        this.title = DescriptionUtils.getErrorDescription("USER_ERROR");
        this.httpStatus = httpStatus;
    }

}
