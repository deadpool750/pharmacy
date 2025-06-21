package com.example.pharmacy.controller.dto.user;

import java.math.BigDecimal;

public class CreateDepositRequestDto {
    private String cardNumber;
    private String expiryDate; // Format: MM/YY
    private String cvc;
    private BigDecimal amount;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getCvc() { return cvc; }
    public void setCvc(String cvc) { this.cvc = cvc; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
