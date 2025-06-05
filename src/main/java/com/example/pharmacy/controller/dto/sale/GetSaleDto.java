package com.example.pharmacy.controller.dto.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GetSaleDto {

    private int id;
    private int customerId;
    private int medicationId;
    private int quantity;
    private BigDecimal totalPrice;
    private Timestamp saleDate;

    public GetSaleDto() {}

    public GetSaleDto(int id, int customerId, int medicationId, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
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

    public int getCustomerId() {
        return customerId;
    }

    public int getMedicationId() {
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
