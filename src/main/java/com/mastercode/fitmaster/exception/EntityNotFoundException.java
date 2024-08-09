package com.mastercode.fitmaster.exception;

import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {
	private String title = DescriptionUtils.getErrorDescription(ErrorConstants.UNABLE_TO_ACCESS_ENTITY);
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
