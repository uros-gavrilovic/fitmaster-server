package com.mastercode.fitmaster.validator.annotations;

import com.mastercode.fitmaster.util.constants.ErrorConstants;
import com.mastercode.fitmaster.validator.PastValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PastValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Past {
    String message() default ErrorConstants.INVALID_PAST_DATE;
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
