package com.example.pharmacy.service.inputs;

/**
 * Model representing a supplier entity in the service layer.
 */
public class SupplierModel {

    /** Unique identifier of the supplier. */
    private Long id;

    /** Name of the supplier. */
    private String name;

    /** Contact phone number of the supplier. */
    private String phone;

    /** Email address of the supplier. */
    private String email;

    /** Physical address of the supplier. */
    private String address;

    /**
     * Constructs a new SupplierModel with specified details.
     *
     * @param id the supplier's ID
     * @param name the supplier's name
     * @param phone the supplier's phone number
     * @param email the supplier's email address
     * @param address the supplier's physical address
     */
    public SupplierModel(Long id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Returns the supplier's ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the supplier's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the supplier's phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the supplier's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the supplier's physical address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}
