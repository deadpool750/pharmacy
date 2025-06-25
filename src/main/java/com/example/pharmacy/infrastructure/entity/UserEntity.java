package com.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entity representing a user in the pharmacy system.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /** Unique identifier for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Username of the user. Must be unique and not null. */
    @Column(nullable = false, unique = true)
    private String username;

    /** Encrypted password of the user. Cannot be null. */
    @Column(nullable = false)
    private String password;

    /** Role of the user (e.g., CUSTOMER or ADMIN). Default is CUSTOMER. */
    @Column(nullable = false)
    private String role = "CUSTOMER"; // default role

    /** Current account balance of the user. Defaults to zero. */
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the encrypted password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the encrypted password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     * @return the role (e.g., "CUSTOMER" or "ADMIN")
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the account balance.
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the account balance.
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
