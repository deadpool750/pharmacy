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

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * REST Controller handling user-related operations in the pharmacy system.
 * Provides endpoints for user management, authentication, deposits, and medication purchases.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a new UserController with the required UserService dependency.
     * 
     * @param userService the service handling user-related business logic
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves user information by their ID.
     * 
     * @param id the unique identifier of the user
     * @return UserResponseDto containing user details
     */
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * Creates a new user account in the system.
     * 
     * @param user DTO containing the new user's information
     * @return CreateUserResponseDto containing the created user's ID and username
     */
    @PostMapping
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user);
    }

    /**
     * Retrieves the current authenticated user's information.
     * 
     * @param principal the currently authenticated user
     * @return UserResponseDto containing the user's details
     */
    @GetMapping("/me")
    public UserResponseDto getMe(Principal principal) {
        var user = userService.getUserByUsername(principal.getName());
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole(), user.getBalance());
    }

    /**
     * Processes a deposit to the user's account balance.
     * 
     * @param dto DTO containing deposit information including amount and card details
     * @param principal the currently authenticated user
     */
    @PostMapping("/deposit")
    public void deposit(@RequestBody CreateDepositRequestDto dto, Principal principal) {
        userService.depositMoney(principal, dto);
    }

    /**
     * Processes a medication purchase for the authenticated user.
     * 
     * @param medicationId the ID of the medication to purchase
     * @param dto DTO containing purchase details including quantity
     * @param principal the currently authenticated user
     */
    @PostMapping("/buy/{medicationId}")
    public void buyDrug(@PathVariable Long medicationId, @RequestBody BuyMedicationRequestDto dto, Principal principal) {
        userService.buyMedication(principal, medicationId, dto.getQuantity());
    }

    /**
     * Retrieves a list of all customers in the system.
     * Only accessible to users with ADMIN role.
     * 
     * @return List of UserResponseDto containing customer details
     */
    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllCustomers() {
        return userService.getUsersByRole("CUSTOMER");
    }
}