package com.mastercode.fitmaster.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotRequiredPhoneNumberValidator implements ConstraintValidator<com.mastercode.fitmaster.validator.annotations.NotRequiredPhoneNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.toString().matches("^\\+[0-9,.\\\\\\-/]+$"); // Might be a subject to change.
    }
}
