package com.minor.productmanagement;

import com.minor.productmanagement.dtos.ProductRequest;
import com.minor.productmanagement.dtos.ProductResponse;
import com.minor.productmanagement.mappers.ProductMapper;
import com.minor.productmanagement.mappers.ProductMapperMS;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    //private final ProductMapper productMapper;
    private final ProductMapperMS productMapperMS;

    public ProductController(ProductService productService, ProductMapper productMapper, ProductMapperMS productMapperMS) {
        this.productService = productService;
        this.productMapperMS = productMapperMS;
        //this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        var products = productService.getProducts();
        var result = new ArrayList<ProductResponse>();
        for (Product product : products) {
            result.add(productMapperMS.toResponse(product));
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        var product = productMapperMS.toProduct(productRequest);
        productService.addProduct(product);
        URI location = URI.create("/products/" + product.getId());
        return ResponseEntity.created(location).body(productMapperMS.toResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductRequest productRequest) {
        var product = productMapperMS.toProduct(productRequest);
        productService.updateProduct(id, product);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        var product = productService.getProductById(id);
        return product
                .map(productMapperMS::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
