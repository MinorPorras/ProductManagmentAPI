package com.minor.productmanagement.mappers;

import com.minor.productmanagement.Product;
import com.minor.productmanagement.dtos.ProductRequest;
import com.minor.productmanagement.dtos.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
