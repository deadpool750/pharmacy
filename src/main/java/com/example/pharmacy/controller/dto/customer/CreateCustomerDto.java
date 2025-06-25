package com.example.pharmacy.controller.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object for creating a new customer.
 * Includes validation for name, phone number, and email.
 */
public class CreateCustomerDto {

    /** Customer's full name. Cannot be blank. */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Customer's phone number.
     * Must be exactly 9 digits.
     */
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{9}", message = "Phone number must be 9 digits")
    private String phone;

    /**
     * Customer's email address.
     * Must be a valid email format.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    /** Default constructor. */
    public CreateCustomerDto() {}

    /**
     * Constructs a new CreateCustomerDto with the given name, phone, and email.
     *
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email address
     */
    public CreateCustomerDto(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the customer's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the customer's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the CreateCustomerDto.
     *
     * @return a string describing the customer
     */
    @Override
    public String toString() {
        return "CreateCustomerDto{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
