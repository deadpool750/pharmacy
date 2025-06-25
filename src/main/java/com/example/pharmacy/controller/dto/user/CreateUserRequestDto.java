package com.example.pharmacy.controller.dto.user;

/**
 * Data Transfer Object for registering a new user.
 * Can be either CUSTOMER or ADMIN based on the 'role' field.
 */
public class CreateUserRequestDto {

    /** Username chosen by the user. */
    private String username;

    /** Password chosen by the user. */
    private String password;

    /** Role of the user (expected: "CUSTOMER" or "ADMIN"). */
    private String role;

    /** Default constructor. */
    public CreateUserRequestDto() {
    }

    /**
     * Constructs a CreateUserRequestDto with username, password, and role.
     *
     * @param username the username
     * @param password the password
     * @param role the user role
     */
    public CreateUserRequestDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Sets the user role.
     *
     * @param role the role to set (CUSTOMER or ADMIN)
     */
    public void setRole(String role) {
        this.role = role;
    }
}
