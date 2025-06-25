package com.example.pharmacy.controller.dto.drug;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object for responding with drug details after creation.
 */
public class CreateDrugResponseDto {

    /** Unique identifier of the drug. */
    private int id;

    /** Name of the drug. */
    private String name;

    /** Manufacturer of the drug. */
    private String manufacturer;

    /** Price of the drug. */
    private BigDecimal price;

    /** Expiration date of the drug. */
    private Date expirationDate;

    /** Quantity of the drug in stock. */
    private int stockQuantity;

    /**
     * Constructs a response DTO with all drug details.
     *
     * @param id the drug ID
     * @param name the name of the drug
     * @param manufacturer the manufacturer of the drug
     * @param price the price of the drug
     * @param expirationDate the expiration date of the drug
     * @param stockQuantity the quantity in stock
     */
    public CreateDrugResponseDto(int id, String name, String manufacturer, BigDecimal price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    /** Default constructor. */
    public CreateDrugResponseDto() {}

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
     * Gets the drug name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the drug name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the manufacturer name.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer name.
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
}
