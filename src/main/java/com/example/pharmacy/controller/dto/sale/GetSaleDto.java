package com.example.pharmacy.controller.dto.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GetSaleDto {
    private int id;
    private String customerName;
    private String medicationName;
    private int quantity;
    private BigDecimal totalPrice;
    private Timestamp saleDate;

    public GetSaleDto() {}

    public GetSaleDto(int id, String customerName, String medicationName, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
        this.id = id;
        this.customerName = customerName;
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMedicationName() {
        return medicationName;
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
