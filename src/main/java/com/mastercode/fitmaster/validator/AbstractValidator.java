package com.mastercode.fitmaster.validator;

import jakarta.validation.ConstraintValidator;
import java.lang.annotation.Annotation;

public abstract class AbstractValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {
	protected boolean required;

	@Override
	public void initialize(A constraintAnnotation) {
		try {
			this.required = (boolean) constraintAnnotation.getClass().getMethod("required").invoke(constraintAnnotation);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize validator", e);
		}
	}
}
