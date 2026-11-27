package com.minor.productmanagement.mappers;

import com.minor.productmanagement.Product;
import com.minor.productmanagement.dtos.ProductRequest;
import com.minor.productmanagement.dtos.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapperMS {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest request);

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    void updateProduct(ProductRequest request, @MappingTarget Product product);
}
