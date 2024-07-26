package com.mastercode.fitmaster.validator;

import com.mastercode.fitmaster.util.PatternUtils;
import com.mastercode.fitmaster.validator.annotations.Email;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator extends AbstractValidator<Email, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return !required;
        return value.toString().matches(PatternUtils.EMAIL_PATTERN);
    }
}
