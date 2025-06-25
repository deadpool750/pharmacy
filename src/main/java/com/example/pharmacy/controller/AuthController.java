package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.login.LoginRequestDto;
import com.example.pharmacy.controller.dto.login.LoginResponseDto;
import com.example.pharmacy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling authentication-related requests.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /** Service for performing authentication logic. */
    private final AuthService authService;

    /**
     * Constructs the AuthController with the given AuthService.
     *
     * @param authService the authentication service
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles user login by validating credentials and returning a JWT token with role.
     *
     * @param loginRequestDto the login request containing username and password
     * @return a LoginResponseDto containing the JWT token and user role
     */
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println("Received login request: " + loginRequestDto);
        LoginResponseDto response = authService.login(loginRequestDto);
        System.out.println("LoginController: Returning token = " + response.getToken());
        return response;
    }
}
