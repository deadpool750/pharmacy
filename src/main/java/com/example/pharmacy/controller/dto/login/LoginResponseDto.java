package com.example.pharmacy.controller.dto.login;

/**
 * Data Transfer Object for responding to a successful login.
 * Contains the JWT token and user role.
 */
public class LoginResponseDto {

    /** JWT token returned after successful authentication. */
    private String token;

    /** Role of the authenticated user (e.g., ADMIN, CUSTOMER). */
    private String role;

    /** Default constructor. */
    public LoginResponseDto() {
    }

    /**
     * Constructs a LoginResponseDto with a token and role.
     *
     * @param token the JWT token
     * @param role the user's role
     */
    public LoginResponseDto(String token, String role) {
        this.token = token;
        this.role = role;
    }

    /**
     * Gets the JWT token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the user's role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
