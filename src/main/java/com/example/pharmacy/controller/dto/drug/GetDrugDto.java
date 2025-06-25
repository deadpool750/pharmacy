package com.example.pharmacy.controller.dto.drug;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object for retrieving drug information.
 */
public class GetDrugDto {

    /** Unique identifier for the drug. */
    private int id;

    /** Name of the drug. */
    private String name;

    /** Manufacturer of the drug. */
    private String manufacturer;

    /** Price of the drug. */
    private BigDecimal price;

    /** Expiration date of the drug. */
    private Date expirationDate;

    /** Availability status of the drug. */
    private boolean isAvailable;

    /** Quantity of the drug in stock. */
    private int stockQuantity;

    /**
     * Constructs a GetDrugDto with all drug details.
     *
     * @param id the drug ID
     * @param name the name of the drug
     * @param manufacturer the manufacturer of the drug
     * @param price the price of the drug
     * @param expirationDate the expiration date of the drug
     * @param isAvailable whether the drug is available
     * @param stockQuantity quantity in stock
     */
    public GetDrugDto(int id, String name, String manufacturer, BigDecimal price, Date expirationDate, boolean isAvailable, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.isAvailable = isAvailable;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Gets the stock quantity.
     *
     * @return the stock quantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity.
     *
     * @param stockQuantity the stock quantity to set
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /** Default constructor. */
    public GetDrugDto() {}

    /**
     * Gets the drug ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the drug ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the drug.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drug.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the manufacturer of the drug.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer of the drug.
     *
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the price of the drug.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the drug.
     *
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the expiration date of the drug.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the drug.
     *
     * @param expirationDate the expiration date to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Checks if the drug is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the drug.
     *
     * @param available the availability to set
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
