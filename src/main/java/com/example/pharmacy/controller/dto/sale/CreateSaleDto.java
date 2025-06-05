package com.example.pharmacy.controller.dto.sale;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CreateSaleDto {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotNull(message = "Medication ID is required")
    private Integer medicationId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private BigDecimal totalPrice;

    public CreateSaleDto() {}

    public CreateSaleDto(Integer customerId, Integer medicationId, int quantity, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
