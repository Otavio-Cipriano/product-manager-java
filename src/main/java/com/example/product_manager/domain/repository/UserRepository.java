package com.example.product_manager.domain.repository;

import com.example.product_manager.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
