package com.example.thirdproject.repository;

import com.example.thirdproject.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
}
