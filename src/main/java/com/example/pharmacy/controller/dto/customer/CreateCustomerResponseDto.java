package com.example.pharmacy.controller.dto.customer;

/**
 * Data Transfer Object for returning a newly created customer's details.
 */
public class CreateCustomerResponseDto {

    /** Unique identifier for the customer. */
    private int id;

    /** Customer's full name. */
    private String name;

    /** Customer's phone number. */
    private String phone;

    /** Customer's email address. */
    private String email;

    /** Default constructor. */
    public CreateCustomerResponseDto() {}

    /**
     * Constructs a response DTO with all customer fields.
     *
     * @param id the customer's ID
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email address
     */
    public CreateCustomerResponseDto(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the customer's ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the customer's ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the CreateCustomerResponseDto.
     *
     * @return a string describing the customer
     */
    @Override
    public String toString() {
        return "CreateCustomerResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
