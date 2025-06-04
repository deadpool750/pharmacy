package com.example.pharmacy.service.errors;

public class UserNotFoundError extends RuntimeException {
    public UserNotFoundError(String username) {
        super("User not found with username: " + username);
    }

    public UserNotFoundError(Long userId) {
        super("User not found with ID: " + userId);
    }

    public UserNotFoundError() {
        super("User not found");
    }
}
