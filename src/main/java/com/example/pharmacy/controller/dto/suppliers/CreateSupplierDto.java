package com.example.pharmacy.controller.dto.suppliers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateSupplierDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    public CreateSupplierDto() {}

    public CreateSupplierDto(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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
