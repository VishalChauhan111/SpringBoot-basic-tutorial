package com.mindurownbusiness.springboottutorial.springboottutorial.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidRole {
    String message() default "Role must be USER, ADMIN or SPAM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
