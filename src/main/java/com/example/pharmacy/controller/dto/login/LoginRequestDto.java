package com.example.pharmacy.controller.dto.login;

/**
 * Data Transfer Object for handling login requests.
 * Contains username and password fields.
 */
public class LoginRequestDto {

    /** Username provided by the user. */
    private String username;

    /** Password provided by the user. */
    private String password;

    /** Default constructor. */
    public LoginRequestDto() {
    }

    /**
     * Constructs a LoginRequestDto with username and password.
     *
     * @param username the user's username
     * @param password the user's password
     */
    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
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
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
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
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the login request.
     *
     * @return a string containing the username and password
     */
    @Override
    public String toString() {
        return "LoginRequestDto{username='" + username + "', password='" + password + "'}";
    }
}
