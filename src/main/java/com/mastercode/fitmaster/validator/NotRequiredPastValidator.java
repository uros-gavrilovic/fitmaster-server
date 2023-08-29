package com.mastercode.fitmaster.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotRequiredPastValidator implements ConstraintValidator<com.mastercode.fitmaster.validator.annotations.NotRequiredPast, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !((java.time.LocalDate) value).isAfter(java.time.LocalDate.now());
    }
}
