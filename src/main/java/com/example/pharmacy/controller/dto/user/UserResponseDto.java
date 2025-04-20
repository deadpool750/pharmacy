package com.example.pharmacy.controller.dto.user;

public class UserResponseDto {
    private long id;
    private String username;


    public UserResponseDto(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponseDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
