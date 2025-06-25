package com.example.pharmacy.controller.dto.user;

/**
 * Data Transfer Object for responding with user details after successful registration.
 */
public class CreateUserResponseDto {

    /** Unique identifier of the created user. */
    private Long id;

    /** Username of the created user. */
    private String username;

    /** Default constructor. */
    public CreateUserResponseDto() {
    }

    /**
     * Constructs a CreateUserResponseDto with user ID and username.
     *
     * @param id the user's ID
     * @param username the user's username
     */
    public CreateUserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
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
     * Sets the user ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
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
}
