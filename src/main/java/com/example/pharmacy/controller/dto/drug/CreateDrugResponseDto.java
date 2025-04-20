package com.example.pharmacy.controller.dto.drug;

import java.math.BigDecimal;
import java.sql.Date;

public class CreateDrugResponseDto {

    private int id;
    private String name;

    private String manufacturer;

    private BigDecimal price;

    private Date expirationDate;

    private int stockQuantity;

    public CreateDrugResponseDto(int id, String name, String manufacturer, BigDecimal price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public CreateDrugResponseDto(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
