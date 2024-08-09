package com.mastercode.fitmaster.util;

import com.mastercode.fitmaster.dto.response.ErrorResponse;
import com.mastercode.fitmaster.exception.EntityNotFoundException;
import com.mastercode.fitmaster.exception.PackageHasActiveMembershipsException;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import com.mastercode.fitmaster.util.constants.SqlErrorConstants;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

public class ExceptionUtils {
	public static ResponseEntity<ErrorResponse> generateResponse(Exception ex, HttpStatus status, String title, String message) {
		CustomLogger.error(ex.getMessage());

		return new ResponseEntity<>(
				new ErrorResponse(title, message),
				status
		);
	}

	public static void handleSqlException(Exception e, Long entityId) {
		if (e instanceof JpaSystemException) {
			System.out.println("USLO JE U JPA SYSTEM EXCEPTION");
		} else if (e instanceof DataAccessException) {
			System.out.println("USLO JE U DATA ACCESS EXCEPTION");
		} else {
			System.out.println("USLO JE U ELSE");
		}

		String sqlState = ((GenericJDBCException) e.getCause()).getSQLState();
		System.out.println("USLO JE OVDE");
		System.out.println(e.getMessage());

		switch (sqlState) {
			case SqlErrorConstants.NO_DATA_FOUND:
				throw new EntityNotFoundException(
						DescriptionUtils.getInterpolatedErrorDescription(entityId.toString(), ErrorConstants.ENTITY_NOT_FOUND)
				);
			case SqlErrorConstants.UNIQUE_CONSTRAINT:
				throw new PackageHasActiveMembershipsException(
						DescriptionUtils.getErrorDescription(ErrorConstants.PACKAGE_HAS_ACTIVE_MEMBERSHIPS)
				);
			default:
				return;
		}
	}
}
