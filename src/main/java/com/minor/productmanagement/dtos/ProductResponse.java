package com.minor.productmanagement.dtos;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Double price
) {
}
