package com.example.pharmacy.controller.dto.user;

import java.math.BigDecimal;

/**
 * Data Transfer Object for responding with full user details.
 * Includes ID, username, role, and account balance.
 */
public class UserResponseDto {

    /** Unique identifier of the user. */
    private Long id;

    /** Username of the user. */
    private String username;

    /** Role of the user (e.g., CUSTOMER, ADMIN). */
    private String role;

    /** Current account balance of the user. */
    private BigDecimal balance;

    /**
     * Constructs a UserResponseDto with all fields.
     *
     * @param id the user's ID
     * @param username the user's username
     * @param role the user's role
     * @param balance the user's balance
     */
    public UserResponseDto(Long id, String username, String role, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.balance = balance;
    }

    /**
     * Gets the user ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the user's balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the user ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the user role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the user's balance.
     *
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
