package com.mindurownbusiness.springboottutorial.springboottutorial.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    private final Set<String> validRoles = Set.of(
            "USER",
            "ADMIN",
            "SPAM"
    );

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {

        if (role == null) {
            return false;
        }

        return validRoles.contains(role.toUpperCase());
    }
}