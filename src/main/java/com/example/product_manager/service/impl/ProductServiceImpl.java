package com.example.product_manager.service.impl;

import com.example.product_manager.domain.model.Product;
import com.example.product_manager.domain.repository.ProductRepository;
import com.example.product_manager.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product create(Product productToCreate) {
        if (productToCreate.getId() != null && productRepository.existsById(productToCreate.getId())){
            throw new IllegalArgumentException("This product ID already exists");
        }
        if(productToCreate.getQuantity() < productToCreate.getMinOnStock()){
            throw new IllegalArgumentException("Quantity on creation, can't be less than min on stock");
        }
        if (productToCreate.getMinOnStock() <= 0 ){
            throw new IllegalArgumentException("Min stock must be higher than 0");
        }
        return productRepository.save(productToCreate);
    }

}
