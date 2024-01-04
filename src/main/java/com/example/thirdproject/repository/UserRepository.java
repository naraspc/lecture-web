package com.example.thirdproject.repository;

import com.example.thirdproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email);
}
