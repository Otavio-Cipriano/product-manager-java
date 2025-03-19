package com.example.product_manager.domain.repository;

import com.example.product_manager.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM tb_sale s WHERE s.saleAt BETWEEN :startDateTime AND :endDateTime")
    List<Sale> findSalesBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
