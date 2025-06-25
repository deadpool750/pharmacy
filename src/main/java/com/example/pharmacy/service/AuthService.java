package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.login.LoginRequestDto;
import com.example.pharmacy.controller.dto.login.LoginResponseDto;
import com.example.pharmacy.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling user authentication.
 */
@Service
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the AuthService with necessary dependencies.
     *
     * @param userRepository repository for accessing user data
     * @param jwtService service for generating JWT tokens
     * @param passwordEncoder encoder for verifying password hashes
     */
    public AuthService(IUserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user and returns a JWT token if successful.
     *
     * @param loginRequestDto DTO containing username and password
     * @return LoginResponseDto with JWT token and user role
     * @throws RuntimeException if user not found or password is incorrect
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        System.out.println("Attempting login for username: " + loginRequestDto.getUsername());

        var user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Password hash from DB: " + user.getPassword());

        boolean doPasswordsMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (!doPasswordsMatch) {
            throw new RuntimeException("Unauthorized: Incorrect password");
        }

        System.out.println("Login successful for user: " + user.getUsername());
        return new LoginResponseDto(jwtService.createToken(user), user.getRole());
    }
}
