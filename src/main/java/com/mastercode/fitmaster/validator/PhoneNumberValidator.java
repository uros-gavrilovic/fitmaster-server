package com.mastercode.fitmaster.validator;

import com.mastercode.fitmaster.util.PatternUtils;
import com.mastercode.fitmaster.validator.annotations.PhoneNumber;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator extends AbstractValidator<PhoneNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return !required;
        return value.toString().matches(PatternUtils.PHONE_NUMBER_PATTERN);
    }
}
