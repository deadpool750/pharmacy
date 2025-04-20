package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.login.LoginRequestDto;
import com.example.pharmacy.controller.dto.login.LoginResponseDto;
import com.example.pharmacy.repository.IUserRepository;
import io.jsonwebtoken.security.Password;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var doPasswordsMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if(!doPasswordsMatch){
            throw new RuntimeException("You're unauthorized buddy");
        }


        return new LoginResponseDto(jwtService.createToken(user));

    }
}
