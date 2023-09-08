package com.mastercode.fitmaster.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotRequiredPhoneNumberValidator implements
                                             ConstraintValidator<com.mastercode.fitmaster.validator.annotations.NotRequiredPhoneNumber, Object> {

    private static final String PHONE_NUMBER_REGEX = "\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}";
    // Might be subject to change.

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.toString().matches(PHONE_NUMBER_REGEX);
    }
}
