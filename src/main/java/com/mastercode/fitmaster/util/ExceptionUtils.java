package com.mastercode.fitmaster.util;

import com.mastercode.fitmaster.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class ExceptionUtils {
	public static ResponseEntity<ErrorResponse> generateResponse(Exception ex, HttpStatus status, String title, String message) {
		CustomLogger.error(ex.getMessage());

		return new ResponseEntity<>(
				new ErrorResponse(title, message),
				status
		);
	}
}
