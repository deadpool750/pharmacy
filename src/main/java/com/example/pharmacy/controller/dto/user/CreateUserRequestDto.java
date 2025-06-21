package com.example.pharmacy.controller.dto.user;

/**
 * DTO for registering a new user.
 * Can be either CUSTOMER or ADMIN based on 'role'.
 */
public class CreateUserRequestDto {
    private String username;
    private String password;
    private String role; // Expected: "CUSTOMER" or "ADMIN"

    public CreateUserRequestDto() {
    }

    public CreateUserRequestDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
