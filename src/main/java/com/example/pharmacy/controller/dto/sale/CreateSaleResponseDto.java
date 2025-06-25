package com.example.pharmacy.controller.dto.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Data Transfer Object for responding with sale details after creation.
 */
public class CreateSaleResponseDto {

    /** Unique identifier of the sale. */
    private int id;

    /** ID of the customer who made the purchase. */
    private Integer customerId;

    /** ID of the medication that was purchased. */
    private Integer medicationId;

    /** Quantity of medication sold. */
    private int quantity;

    /** Total price of the sale. */
    private BigDecimal totalPrice;

    /** Timestamp of when the sale occurred. */
    private Timestamp saleDate;

    /** Default constructor. */
    public CreateSaleResponseDto() {}

    /**
     * Constructs a CreateSaleResponseDto with all sale details.
     *
     * @param id the sale ID
     * @param customerId the customer ID
     * @param medicationId the medication ID
     * @param quantity the quantity sold
     * @param totalPrice the total price
     * @param saleDate the timestamp of the sale
     */
    public CreateSaleResponseDto(int id, Integer customerId, Integer medicationId, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
        this.id = id;
        this.customerId = customerId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    /**
     * Gets the sale ID.
     *
     * @return the sale ID
     */
    public int getId() {
        return id;
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
     * Gets the medication ID.
     *
     * @return the medication ID
     */
    public Integer getMedicationId() {
        return medicationId;
    }

    /**
     * Gets the quantity sold.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
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
     * Gets the timestamp of the sale.
     *
     * @return the sale date
     */
    public Timestamp getSaleDate() {
        return saleDate;
    }
}
