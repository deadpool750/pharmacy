package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.user.CreateUserRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserResponseDto;
import com.example.pharmacy.controller.dto.user.UserResponseDto;
import com.example.pharmacy.infrastructure.entity.UserEntity;
import com.example.pharmacy.repository.IUserRepository;
import com.example.pharmacy.service.errors.UserNotFoundError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto userDto) {
        logger.info("Creating new user: {}", userDto.getUsername());

        var hashedPassword = passwordEncoder.encode(userDto.getPassword());
        logger.debug("Generated password hash: {}", hashedPassword);

        var userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(hashedPassword);

        var savedUser = userRepository.save(userEntity);
        logger.info("User saved with ID: {}", savedUser.getId());

        return new CreateUserResponseDto(savedUser.getId());
    }

    public UserResponseDto getUser(long id) {
        logger.info("Fetching user by ID: {}", id);

        var user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return new UserNotFoundError(id);
                });

        return new UserResponseDto(user.getId(), user.getUsername());
    }

    public UserResponseDto getByUsername(String username) {
        logger.info("Fetching user by username: {}", username);

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("User not found with username: {}", username);
                    return new UserNotFoundError(username);
                });

        return new UserResponseDto(user.getId(), user.getUsername());
    }
}
