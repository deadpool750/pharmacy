package com.example.pharmacy.service.inputs;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Model representing a sale transaction within the service layer.
 */
public class SaleModel {

    /** Unique identifier of the sale. */
    private Long id;

    /** ID of the customer who made the purchase. */
    private int customerId;

    /** ID of the medication that was sold. */
    private int medicationId;

    /** Quantity of the medication sold. */
    private int quantity;

    /** Total price of the sale transaction. */
    private BigDecimal totalPrice;

    /** Timestamp of when the sale was made. */
    private Timestamp saleDate;

    /**
     * Constructs a SaleModel with all fields.
     *
     * @param id the sale ID
     * @param customerId the ID of the customer
     * @param medicationId the ID of the medication
     * @param quantity the quantity sold
     * @param totalPrice the total price of the sale
     * @param saleDate the timestamp of the sale
     */
    public SaleModel(Long id, int customerId, int medicationId, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
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
    public Long getId() {
        return id;
    }

    /**
     * Gets the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the medication ID.
     *
     * @return the medication ID
     */
    public int getMedicationId() {
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
