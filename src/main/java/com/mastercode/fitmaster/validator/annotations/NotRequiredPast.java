package com.mastercode.fitmaster.validator.annotations;

import com.mastercode.fitmaster.validator.NotRequiredPastValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotRequiredPastValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NotRequiredPast {
    String message() default "Value must be in the past.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}