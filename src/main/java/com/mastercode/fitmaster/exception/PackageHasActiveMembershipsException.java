package com.mastercode.fitmaster.exception;

import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import org.springframework.http.HttpStatus;

public class PackageHasActiveMembershipsException extends RuntimeException {
	private final String title;
	private final HttpStatus httpStatus;

	public PackageHasActiveMembershipsException(String message, HttpStatus httpStatus) {
		super(message);
		this.title = DescriptionUtils.getErrorDescription(ErrorConstants.UPDATE_PACKAGE_ERROR);
		this.httpStatus = httpStatus;
	}
}
