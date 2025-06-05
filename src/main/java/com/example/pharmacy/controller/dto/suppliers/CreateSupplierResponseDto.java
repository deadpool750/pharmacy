package com.example.pharmacy.controller.dto.suppliers;

public class CreateSupplierResponseDto {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public CreateSupplierResponseDto() {}

    public CreateSupplierResponseDto(int id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
