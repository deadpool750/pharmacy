package com.example.pharmacy.controller.dto.suppliers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for creating a new supplier.
 * Includes validation for required fields.
 */
public class CreateSupplierDto {

    /** Name of the supplier. Cannot be blank. */
    @NotBlank(message = "Name is required")
    private String name;

    /** Phone number of the supplier. Cannot be blank. */
    @NotBlank(message = "Phone is required")
    private String phone;

    /** Email address of the supplier. Must be valid if provided. */
    @Email(message = "Email must be valid")
    private String email;

    /** Address of the supplier. Cannot be blank. */
    @NotBlank(message = "Address is required")
    private String address;

    /** Default constructor. */
    public CreateSupplierDto() {}

    /**
     * Constructs a CreateSupplierDto with all fields.
     *
     * @param name the supplier's name
     * @param phone the supplier's phone number
     * @param email the supplier's email address
     * @param address the supplier's address
     */
    public CreateSupplierDto(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Gets the supplier's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the supplier's phone number.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the supplier's email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the supplier's address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}
