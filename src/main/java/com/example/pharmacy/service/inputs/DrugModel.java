package com.example.pharmacy.service.inputs;

import com.example.pharmacy.service.valueObjects.Price;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model representing internal drug (medication) data used within the service layer.
 */
public class DrugModel {

    /** Unique identifier of the drug. */
    private Long id;

    /** Name of the drug. */
    private String name;

    /** Manufacturer of the drug. */
    private String manufacturer;

    /** Price value object representing the price of the drug. */
    private Price price;

    /** Expiration date of the drug. */
    private Date expirationDate;

    /** Quantity of the drug in stock. */
    private int stockQuantity;

    /**
     * Gets the drug ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the drug ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
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
     * @return the price as BigDecimal
     */
    public BigDecimal getPrice() {
        return price.getValue();
    }

    /**
     * Sets the price using a Price value object.
     *
     * @param price the price to set
     */
    public void setPrice(Price price) {
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
     * Gets the current stock quantity.
     *
     * @return the stock quantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity.
     *
     * @param stockQuantity the quantity to set
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Constructs a DrugModel with all fields.
     *
     * @param id the drug ID
     * @param name the drug name
     * @param manufacturer the drug manufacturer
     * @param price the price value object
     * @param expirationDate the expiration date
     * @param stockQuantity the quantity in stock
     */
    public DrugModel(Long id, String name, String manufacturer, Price price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }
}
