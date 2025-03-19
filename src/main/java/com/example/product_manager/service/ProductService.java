package com.example.product_manager.service;

import com.example.product_manager.domain.model.Product;

public interface ProductService {
    Product findById(Long id);

    Product create(Product productToCreate);
}
