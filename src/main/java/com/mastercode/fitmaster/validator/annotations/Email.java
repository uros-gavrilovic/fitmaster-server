package com.mastercode.fitmaster.validator.annotations;

import com.mastercode.fitmaster.util.constants.ErrorConstants;
import com.mastercode.fitmaster.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Email {
    String message() default ErrorConstants.INVALID_EMAIL_FORMAT;
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
