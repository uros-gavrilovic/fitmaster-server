package com.mastercode.fitmaster.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mastercode.fitmaster.config.CustomResponseEntitySerializer;


/**
 * CustomResponseEntity is a record class that represents a custom response entity.
 * Used with ResponseEntity<CustomResponseEntity> to manually handle possible errors instead of letting exception handler
 * catch it.
 * With CustomResponseEntitySerializer, if the data present, it will be serialized without any nesting and title or message.
 * If the data is null, the title and message will be serialized, along with a timestamp of error.
 *
 * Usage:
 * If you want to manually handle an exception, you can return ResponseEntity<CustomResponseEntity> in your controller,
 * and pass the data or title and message to the CustomResponseEntity constructor. Otherwise, you can just throw an exception,
 * and let the exception handler catch it and in controller you'll just return the expected ResponseEntity<T>.
 *
 * @param data - the data that is being returned to the client
 * @param title - title of the error message
 * @param message - message contents of the error
 * @param <T> - the generic type of data
 */
@JsonSerialize(using = CustomResponseEntitySerializer.class)
public record CustomResponseEntity<T>(
		T data,
		String title,
		String message
) {
	public static <T> CustomResponseEntity<T> ofData(T data) {
		return new CustomResponseEntity<>(data, null, null);
	}

	public static <T> CustomResponseEntity<T> ofError(String title, String message) {
		return new CustomResponseEntity<>(null, title, message);
	}
}
