package com.example.product_manager.service;

import com.example.product_manager.domain.model.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    Sale create(Sale saleToCreate);
    Sale findById(Long id);

    List<Sale> getSalesByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
