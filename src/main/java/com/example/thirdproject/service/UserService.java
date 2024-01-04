package com.example.thirdproject.service;

import com.example.thirdproject.constant.Department;
import com.example.thirdproject.constant.Role;
import com.example.thirdproject.dto.UserDto;
import com.example.thirdproject.entity.UserEntity;
import com.example.thirdproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
