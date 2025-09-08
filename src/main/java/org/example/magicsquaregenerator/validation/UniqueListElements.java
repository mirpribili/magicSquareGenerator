package org.example.magicsquaregenerator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = org.example.magicsquaregenerator.validation.UniqueListElementsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueListElements {
    String message() default "Элементы списка должны быть уникальными";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
