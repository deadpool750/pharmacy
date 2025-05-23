package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.user.CreateUserRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserResponseDto;
import com.example.pharmacy.controller.dto.user.UserResponseDto;
import com.example.pharmacy.infrastructure.entity.UserEntity;
import com.example.pharmacy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto userDto) {
        var userEntity = new UserEntity();

        var hashedPassword = passwordEncoder.encode(userDto.getPassword());

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(hashedPassword);
        var savedUser = userRepository.save(userEntity);

        return new CreateUserResponseDto(savedUser.getId());
    }
    public UserResponseDto getUser(long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getUsername());
    }
}
