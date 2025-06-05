package com.example.pharmacy.controller.dto.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CreateSaleResponseDto {

    private int id;
    private Integer customerId;
    private Integer medicationId;
    private int quantity;
    private BigDecimal totalPrice;
    private Timestamp saleDate;

    public CreateSaleResponseDto() {}

    public CreateSaleResponseDto(int id, Integer customerId, Integer medicationId, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
        this.id = id;
        this.customerId = customerId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    public int getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }
}
