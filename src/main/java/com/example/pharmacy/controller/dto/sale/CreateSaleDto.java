package com.example.pharmacy.controller.dto.sale;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Data Transfer Object for creating a new sale record.
 * Contains validation for all required fields.
 */
public class CreateSaleDto {

    /** ID of the customer making the purchase. Cannot be null. */
    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    /** ID of the medication being purchased. Cannot be null. */
    @NotNull(message = "Medication ID is required")
    private Integer medicationId;

    /** Quantity of medication purchased. Must be at least 1. */
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    /** Total price of the sale. Must be greater than 0. */
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private BigDecimal totalPrice;

    /** Default constructor. */
    public CreateSaleDto() {}

    /**
     * Constructs a CreateSaleDto with all required fields.
     *
     * @param customerId the ID of the customer
     * @param medicationId the ID of the medication
     * @param quantity the quantity purchased
     * @param totalPrice the total price of the sale
     */
    public CreateSaleDto(Integer customerId, Integer medicationId, int quantity, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the customer ID.
     *
     * @return the customer ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId the ID to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the medication ID.
     *
     * @return the medication ID
     */
    public Integer getMedicationId() {
        return medicationId;
    }

    /**
     * Sets the medication ID.
     *
     * @param medicationId the ID to set
     */
    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    /**
     * Gets the quantity purchased.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity purchased.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price of the sale.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the sale.
     *
     * @param totalPrice the price to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
