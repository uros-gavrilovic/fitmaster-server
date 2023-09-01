package com.mastercode.fitmaster.validator.annotations;

import com.mastercode.fitmaster.validator.NotRequiredPhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotRequiredPhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NotRequiredPhoneNumber {
    String message() default "Value must follow phone number pattern.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
