package com.example.pharmacy.controller.dto.drug;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Date;

public class CreateDrugDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Expiration date is required")
    @Future(message = "Expiration date must be in the future")
    private Date expirationDate;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    @JsonProperty("stock_quantity")
    private int stockQuantity;

    public CreateDrugDto(String name, String manufacturer, BigDecimal price, Date expirationDate, int stockQuantity) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public CreateDrugDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
