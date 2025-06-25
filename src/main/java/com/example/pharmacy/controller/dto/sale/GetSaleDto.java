package com.example.pharmacy.controller.dto.sale;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Data Transfer Object for retrieving sale details.
 * Includes human-readable customer and medication names.
 */
public class GetSaleDto {

    /** Unique identifier of the sale. */
    private int id;

    /** Name of the customer who made the purchase. */
    private String customerName;

    /** Name of the medication that was purchased. */
    private String medicationName;

    /** Quantity of medication sold. */
    private int quantity;

    /** Total price of the sale. */
    private BigDecimal totalPrice;

    /** Timestamp of when the sale occurred. */
    private Timestamp saleDate;

    /** Default constructor. */
    public GetSaleDto() {}

    /**
     * Constructs a GetSaleDto with all sale information.
     *
     * @param id the sale ID
     * @param customerName the name of the customer
     * @param medicationName the name of the medication
     * @param quantity the quantity sold
     * @param totalPrice the total price
     * @param saleDate the date and time of the sale
     */
    public GetSaleDto(int id, String customerName, String medicationName, int quantity, BigDecimal totalPrice, Timestamp saleDate) {
        this.id = id;
        this.customerName = customerName;
        this.medicationName = medicationName;
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
     * Gets the customer's name.
     *
     * @return the customer's name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Gets the medication's name.
     *
     * @return the medication's name
     */
    public String getMedicationName() {
        return medicationName;
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
     * Gets the sale date and time.
     *
     * @return the sale timestamp
     */
    public Timestamp getSaleDate() {
        return saleDate;
    }
}
