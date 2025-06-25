package com.example.pharmacy.controller.dto.suppliers;

/**
 * Data Transfer Object for responding with supplier details after creation.
 */
public class CreateSupplierResponseDto {

    /** Unique identifier of the supplier. */
    private int id;

    /** Name of the supplier. */
    private String name;

    /** Phone number of the supplier. */
    private String phone;

    /** Email address of the supplier. */
    private String email;

    /** Address of the supplier. */
    private String address;

    /** Default constructor. */
    public CreateSupplierResponseDto() {}

    /**
     * Constructs a CreateSupplierResponseDto with all fields.
     *
     * @param id the supplier ID
     * @param name the supplier name
     * @param phone the phone number
     * @param email the email address
     * @param address the address
     */
    public CreateSupplierResponseDto(int id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Gets the supplier ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the supplier name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the supplier phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the supplier email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the supplier address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}
