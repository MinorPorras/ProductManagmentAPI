package com.minor.productmanagement.dtos;

import com.minor.productmanagement.validation.ForbiddenWords;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 100, message = "Name cannot exceed 100 or have less than 3 characters")
        @ForbiddenWords(value = {"badword1", "badword2"}, message = "Name cannot contain forbidden words")
        String name,
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,
        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
        Double price
) {
}
