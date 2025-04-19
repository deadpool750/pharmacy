package com.example.pharmacy.service.valueObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price {
    private final BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public static Price create(BigDecimal value){
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        // Round to 2 decimal places
        BigDecimal rounded = value.setScale(2, RoundingMode.HALF_UP);
        return new Price(rounded);
    }
}
