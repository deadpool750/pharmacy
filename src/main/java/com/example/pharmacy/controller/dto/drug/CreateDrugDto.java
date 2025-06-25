package com.example.pharmacy.controller.dto.drug;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object for creating a new drug entry.
 * Includes validation for required fields and constraints.
 */
public class CreateDrugDto {

    /** Name of the drug. Cannot be blank. */
    @NotBlank(message = "Name is required")
    private String name;

    /** Manufacturer of the drug. Cannot be blank. */
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    /**
     * Price of the drug. Must be greater than 0.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    /**
     * Expiration date of the drug. Must be in the future.
     */
    @NotNull(message = "Expiration date is required")
    @Future(message = "Expiration date must be in the future")
    private Date expirationDate;

    /**
     * Quantity of the drug in stock. Cannot be negative.
     */
    @Min(value = 0, message = "Stock quantity cannot be negative")
    @JsonProperty("stock_quantity")
    private int stockQuantity;

    /**
     * Constructs a CreateDrugDto with all fields.
     *
     * @param name the drug name
     * @param manufacturer the drug manufacturer
     * @param price the price of the drug
     * @param expirationDate the expiration date
     * @param stockQuantity the stock quantity
     */
    public CreateDrugDto(String name, String manufacturer, BigDecimal price, Date expirationDate, int stockQuantity) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    /** Default constructor. */
    public CreateDrugDto() {}

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
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date.
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
