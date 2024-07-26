package com.mastercode.fitmaster.validator;

import com.mastercode.fitmaster.validator.annotations.Past;
import jakarta.validation.ConstraintValidatorContext;

public class PastValidator extends AbstractValidator<Past, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return !required;
        return !((java.time.LocalDate) value).isAfter(java.time.LocalDate.now());
    }
}
