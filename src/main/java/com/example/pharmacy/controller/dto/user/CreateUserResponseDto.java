package com.example.pharmacy.controller.dto.user;

public class CreateUserResponseDto {
    public CreateUserResponseDto(long id) {
        this.id = id;
    }

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
