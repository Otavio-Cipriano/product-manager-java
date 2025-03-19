package com.example.product_manager.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tb_sale_item")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime saleAt;
    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id", insertable = false, updatable = false)
    @JsonIgnore
    private Sale sale;

    private int quantity;

    @Column(precision = 20, scale = 2)
    private BigDecimal unitaryPrice;

    public Long getId() {
        return id;
    }


    public LocalDateTime getSaleAt() {
        return saleAt;
    }

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
}
