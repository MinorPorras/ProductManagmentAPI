package com.minor.productmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class ForbiddenWordsValidator implements ConstraintValidator<ForbiddenWords, String> {

    private Set<String> forbiddenWords;

    @Override
    public void initialize(ForbiddenWords annotation) {
        forbiddenWords = Arrays.stream(annotation.value())
                .map(word -> word.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String normalizedValue = value.toLowerCase(Locale.ROOT);
        return forbiddenWords.stream().noneMatch(normalizedValue::contains);
    }
}
