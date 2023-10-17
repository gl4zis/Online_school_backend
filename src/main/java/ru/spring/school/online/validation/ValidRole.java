package ru.spring.school.online.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.spring.school.online.model.security.User;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RoleValidator.class)
public @interface ValidRole {
    User.Role[] anyOf();
    String message() default "Must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
