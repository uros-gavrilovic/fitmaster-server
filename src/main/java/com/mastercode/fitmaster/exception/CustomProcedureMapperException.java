package com.mastercode.fitmaster.exception;

import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomProcedureMapperException extends RuntimeException {
	private String title = DescriptionUtils.getErrorDescription(ErrorConstants.MAPPER_ERROR);
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public CustomProcedureMapperException(String message) {
		super(message);
	}

	public CustomProcedureMapperException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
