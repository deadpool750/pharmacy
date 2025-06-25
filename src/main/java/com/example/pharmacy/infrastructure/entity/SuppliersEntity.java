package com.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entity representing a supplier in the pharmacy system.
 */
@Entity
@Table(name = "suppliers", schema = "drugstore_db", catalog = "")
public class SuppliersEntity {

    /** Unique identifier for the supplier. */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /** Name of the supplier. */
    @Basic
    @Column(name = "name")
    private String name;

    /** Phone number of the supplier. */
    @Basic
    @Column(name = "phone")
    private String phone;

    /** Email address of the supplier. */
    @Basic
    @Column(name = "email")
    private String email;

    /** Physical address of the supplier. */
    @Basic
    @Column(name = "address")
    private String address;

    /**
     * Gets the supplier ID.
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the supplier ID.
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the supplier's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the supplier's name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the supplier's phone number.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the supplier's phone number.
     * @param phone the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the supplier's email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the supplier's email.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the supplier's address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the supplier's address.
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuppliersEntity that = (SuppliersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, address);
    }
}
