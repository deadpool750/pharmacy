package com.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entity representing a customer in the pharmacy system.
 */
@Entity
@Table(name = "customers", schema = "drugstore_db", catalog = "")
public class CustomersEntity {

    /** Unique identifier for the customer (primary key). */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /** Customer's full name. */
    @Basic
    @Column(name = "name")
    private String name;

    /** Customer's phone number. */
    @Basic
    @Column(name = "phone")
    private String phone;

    /** Customer's email address. */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * Returns the customer's ID.
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
     * Returns the customer's name.
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
     * Returns the customer's phone number.
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
     * Returns the customer's email address.
     *
     * @return the email
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
     * Checks equality based on ID, name, phone, and email.
     *
     * @param o the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersEntity that = (CustomersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    /**
     * Returns a hash code for the customer entity.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email);
    }
}
