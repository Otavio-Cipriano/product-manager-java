package com.example.product_manager.service.impl;

import com.example.product_manager.domain.model.Product;
import com.example.product_manager.domain.model.Sale;
import com.example.product_manager.domain.model.SaleItem;
import com.example.product_manager.domain.model.SaleItemId;
import com.example.product_manager.domain.repository.ProductRepository;
import com.example.product_manager.domain.repository.SaleRepository;
import com.example.product_manager.service.SaleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    private final ProductRepository productRepository;

    public SaleServiceImpl(SaleRepository saleRepository,
                           ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Sale create(Sale saleToCreate) {
        Sale sale = new Sale();
        List<SaleItem> saleItems = new ArrayList<>();
        BigDecimal calculatedValueTotal = BigDecimal.ZERO;

        if(saleToCreate.getItems() == null || saleToCreate.getItems().isEmpty()){
            throw new IllegalArgumentException("There's no items on sale");
        }

        for (SaleItem item : saleToCreate.getItems()){
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            if(!product.getPrice().equals(item.getUnitaryPrice())){
                throw new IllegalArgumentException("Product's Price of" + product.getName() + "não coincide");
            }

            if (product.getQuantity() < item.getQuantity()){
                throw new IllegalArgumentException("Not enough items on stock for this sale");
            }

            calculatedValueTotal = calculatedValueTotal.add(item.getUnitaryPrice().multiply(new BigDecimal(item.getQuantity())));

            if (!calculatedValueTotal.equals(saleToCreate.getValueTotal())){
                throw new RuntimeException("Total value not match with items sum");
            }

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product);
            saleItem.setQuantity(item.getQuantity());
            saleItem.setUnitaryPrice(product.getPrice());

            productRepository.decreaseStock(item.getId(), item.getQuantity());
            saleItems.add(saleItem);
        }

        sale.setItems(saleItems);

        return saleRepository.save(sale);
    }

    @Override
    public Sale findById(Long id) {
        return saleRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Sale> getSalesByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return saleRepository.findSalesBetweenDateTimes(startDateTime, endDateTime);
    }
}
