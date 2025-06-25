package com.example.pharmacy.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Entity representing a medication in the pharmacy system.
 */
@Entity
@Table(name = "medications", schema = "drugstore_db")
public class MedicationsEntity {

    /** Unique identifier for the medication (primary key). */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /** Name of the medication. */
    @Basic
    @Column(name = "name")
    private String name;

    /** Manufacturer of the medication. */
    @Basic
    @Column(name = "manufacturer")
    private String manufacturer;

    /** Price of the medication. */
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    /** Stock quantity available. */
    @JsonProperty("stock_quantity")
    @Basic
    @Column(name = "stock_quantity")
    private int stockQuantity;

    /** Expiration date of the medication. */
    @JsonProperty("expiration_date")
    @Basic
    @Column(name = "expiration_date")
    private Date expirationDate;

    /**
     * Gets the medication ID.
     *
     * @return the medication ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the medication ID.
     *
     * @param id the medication ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the medication.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medication.
     *
     * @param name the name
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
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the price of the medication.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the medication.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the stock quantity available.
     *
     * @return the stock quantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity.
     *
     * @param stockQuantity the stock quantity
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
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
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
