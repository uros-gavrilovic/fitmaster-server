package com.mastercode.fitmaster.validator.annotations;

import com.mastercode.fitmaster.validator.NotRequiredEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotRequiredEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NotRequiredEmail {
    String message() default "Invalid e-mail format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
