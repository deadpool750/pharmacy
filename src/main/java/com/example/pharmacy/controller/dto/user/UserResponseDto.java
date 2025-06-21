package com.example.pharmacy.controller.dto.user;

import java.math.BigDecimal;

public class UserResponseDto {
    private Long id;
    private String username;
    private String role;
    private BigDecimal balance;

    public UserResponseDto(Long id, String username, String role, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public String getRole() { return role; }

    public BigDecimal getBalance() { return balance; }
}
