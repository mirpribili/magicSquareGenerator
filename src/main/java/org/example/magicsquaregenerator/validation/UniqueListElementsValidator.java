package org.example.magicsquaregenerator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;

public class UniqueListElementsValidator implements ConstraintValidator<UniqueListElements, List<?>> {

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return new HashSet<>(value).size() == value.size();
    }
}
