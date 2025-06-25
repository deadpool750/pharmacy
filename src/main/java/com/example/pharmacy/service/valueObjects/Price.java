package com.example.pharmacy.service.valueObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Value object representing a monetary price with precision handling.
 */
public class Price {
    /** The monetary value, rounded to 2 decimal places. */
    private final BigDecimal value;

    /**
     * Private constructor to enforce use of factory method.
     * @param value the validated and rounded price
     */
    private Price(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the price.
     * @return the BigDecimal price value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Creates a new Price object from a BigDecimal.
     * Rounds the value to 2 decimal places using HALF_UP rounding.
     *
     * @param value the price to be wrapped as a Price object
     * @return a new Price instance with validated and rounded value
     * @throws IllegalArgumentException if the value is negative
     */
    public static Price create(BigDecimal value){
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        // Round to 2 decimal places
        BigDecimal rounded = value.setScale(2, RoundingMode.HALF_UP);
        return new Price(rounded);
    }
}
