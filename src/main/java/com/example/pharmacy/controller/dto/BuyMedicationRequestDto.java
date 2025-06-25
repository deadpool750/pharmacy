package com.example.pharmacy.controller.dto;

/**
 * Data Transfer Object for requesting a purchase of medication.
 * Contains the quantity to be purchased.
 */
public class BuyMedicationRequestDto {

    /** Quantity of the medication to buy. */
    private int quantity;

    /**
     * Gets the quantity of medication to buy.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of medication to buy.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
