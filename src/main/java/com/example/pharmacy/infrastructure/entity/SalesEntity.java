package com.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity representing a sale transaction in the pharmacy system.
 */
@Entity
@Table(name = "sales", schema = "drugstore_db", catalog = "")
public class SalesEntity {

    /** Unique identifier for the sale record. */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /** ID of the customer who made the purchase. */
    @Basic
    @Column(name = "customer_id")
    private Integer customerId;

    /** ID of the medication that was purchased. */
    @Basic
    @Column(name = "medication_id")
    private Integer medicationId;

    /** Quantity of the medication purchased. */
    @Basic
    @Column(name = "quantity")
    private int quantity;

    /** Total price paid for the sale. */
    @Basic
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /** Timestamp of when the sale occurred. */
    @Basic
    @Column(name = "sale_date")
    private Timestamp saleDate;

    /**
     * Gets the sale ID.
     *
     * @return the sale ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the sale ID.
     *
     * @param id the sale ID
     */
    public void setId(int id) {
        this.id = id;
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
     * @param customerId the customer ID
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
     * @param medicationId the medication ID
     */
    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    /**
     * Gets the quantity of medication sold.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of medication sold.
     *
     * @param quantity the quantity
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
     * @param totalPrice the total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the timestamp of the sale.
     *
     * @return the sale date
     */
    public Timestamp getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the timestamp of the sale.
     *
     * @param saleDate the sale date
     */
    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesEntity that = (SalesEntity) o;
        return id == that.id &&
                quantity == that.quantity &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(medicationId, that.medicationId) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(saleDate, that.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, medicationId, quantity, totalPrice, saleDate);
    }
}
