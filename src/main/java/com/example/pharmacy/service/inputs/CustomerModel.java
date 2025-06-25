package com.example.pharmacy.service.inputs;

/**
 * Model representing internal customer data used within the service layer.
 */
public class CustomerModel {

    /** Unique identifier of the customer. */
    private Long id;

    /** Name of the customer. */
    private String name;

    /** Phone number of the customer. */
    private String phone;

    /** Email address of the customer. */
    private String email;

    /**
     * Constructs a CustomerModel with all fields.
     *
     * @param id the customer's ID
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email address
     */
    public CustomerModel(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the customer ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the customer name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the customer email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }
}
