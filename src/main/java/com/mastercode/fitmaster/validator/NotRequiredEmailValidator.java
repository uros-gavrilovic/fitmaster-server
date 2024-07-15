package com.mastercode.fitmaster.validator;

import com.mastercode.fitmaster.util.PatternUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotRequiredEmailValidator implements ConstraintValidator<com.mastercode.fitmaster.validator.annotations.NotRequiredEmail, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.toString().matches(PatternUtils.EMAIL_PATTERN);
    }
}
