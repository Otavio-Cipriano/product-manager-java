package com.example.product_manager.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tb_sale_item")
public class SaleItem {
    @EmbeddedId
    private SaleItemId id;
    private LocalDateTime saleAt;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    private Sale sale;

    private int quantity;

    @Column(scale = 2)
    private BigDecimal unitaryPrice;
}
