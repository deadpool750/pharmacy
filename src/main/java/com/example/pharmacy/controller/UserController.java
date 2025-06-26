package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.BuyMedicationRequestDto;
import com.example.pharmacy.controller.dto.user.CreateDepositRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserResponseDto;
import com.example.pharmacy.controller.dto.user.UserResponseDto;
import com.example.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public UserResponseDto getMe(Principal principal) {
        var user = userService.getUserByUsername(principal.getName());
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole(), user.getBalance());
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void deposit(@RequestBody CreateDepositRequestDto dto, Principal principal) {
        userService.depositMoney(principal, dto);
    }

    @PostMapping("/buy/{medicationId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void buyDrug(@PathVariable Long medicationId, @RequestBody BuyMedicationRequestDto dto, Principal principal) {
        userService.buyMedication(principal, medicationId, dto.getQuantity());
    }

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllCustomers() {
        return userService.getUsersByRole("CUSTOMER");
    }
}
