package com.example.pharmacy.controller.dto.user;

import java.math.BigDecimal;

/**
 * Data Transfer Object for processing a deposit request using simulated card data.
 */
public class CreateDepositRequestDto {

    /** Card number used for the deposit (e.g., 16-digit number). */
    private String cardNumber;

    /** Card expiry date in MM/YY format. */
    private String expiryDate;

    /** Card verification code (CVC). */
    private String cvc;

    /** Amount to be deposited. */
    private BigDecimal amount;

    /**
     * Gets the card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number.
     *
     * @param cardNumber the card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the expiry date.
     *
     * @return the expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     *
     * @param expiryDate the expiry date to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the CVC code.
     *
     * @return the CVC code
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * Sets the CVC code.
     *
     * @param cvc the CVC code to set
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    /**
     * Gets the deposit amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the deposit amount.
     *
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
