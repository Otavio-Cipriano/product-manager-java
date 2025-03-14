package com.example.product_manager.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tb_sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime saleAt;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleItem> items;
    @Column(scale = 2)
    private BigDecimal valueTotal;

    public Long getId() {
        return id;
    }

    public LocalDateTime getSaleAt() {
        return saleAt;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public BigDecimal getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(BigDecimal valueTotal) {
        this.valueTotal = valueTotal;
    }
}
