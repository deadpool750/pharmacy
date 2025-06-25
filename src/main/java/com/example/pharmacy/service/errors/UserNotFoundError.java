package com.example.pharmacy.service.errors;

/**
 * Exception thrown when a user is not found in the system.
 * Supports different constructors for username, user ID, or a generic case.
 */
public class UserNotFoundError extends RuntimeException {

    /**
     * Constructs a UserNotFoundError with a message based on the given username.
     *
     * @param username the username that was not found
     */
    public UserNotFoundError(String username) {
        super("User not found with username: " + username);
    }

    /**
     * Constructs a UserNotFoundError with a message based on the given user ID.
     *
     * @param userId the user ID that was not found
     */
    public UserNotFoundError(Long userId) {
        super("User not found with ID: " + userId);
    }

    /**
     * Constructs a generic UserNotFoundError.
     */
    public UserNotFoundError() {
        super("User not found");
    }
}
