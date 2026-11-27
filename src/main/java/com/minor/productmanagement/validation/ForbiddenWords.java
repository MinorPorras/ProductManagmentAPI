package com.minor.productmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ForbiddenWordsValidator.class)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE_USE,
        ElementType.RECORD_COMPONENT

})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForbiddenWords {
    String message() default "Forbidden words are not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value() default {"admin", "test"};
}
