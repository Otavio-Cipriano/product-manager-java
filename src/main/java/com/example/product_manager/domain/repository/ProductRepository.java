package com.example.product_manager.domain.repository;

import com.example.product_manager.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
