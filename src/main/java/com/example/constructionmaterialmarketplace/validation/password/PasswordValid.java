package com.example.constructionmaterialmarketplace.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = PasswordValidation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
