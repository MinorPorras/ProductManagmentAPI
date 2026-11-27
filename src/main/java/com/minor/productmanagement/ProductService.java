package com.minor.productmanagement;

import com.minor.productmanagement.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        product.setId(UUID.randomUUID());
        products.add(product);
    }

    public void removeProduct(UUID id) {
        Product existing = getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        products.remove(existing);
    }

    public void updateProduct(UUID id, Product product) {
        Product existing = getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
    }

    public Optional<Product> getProductById(UUID id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
