package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.user.CreateDepositRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserResponseDto;
import com.example.pharmacy.controller.dto.user.UserResponseDto;
import com.example.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/me")
    public UserResponseDto getMe(Principal principal) {
        var user = userService.getUserByUsername(principal.getName());
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole(), user.getBalance());
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody CreateDepositRequestDto dto, Principal principal) {
        userService.depositMoney(principal, dto);
    }

    @PostMapping("/buy/{medicationId}")
    public void buyDrug(@PathVariable Long medicationId, Principal principal) {
        userService.buyMedication(principal, medicationId);
    }

}