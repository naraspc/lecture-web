package com.example.thirdproject.repository;

import com.example.thirdproject.constant.Category;
import com.example.thirdproject.entity.InstructorEntity;
import com.example.thirdproject.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
    List<LectureEntity> findByCategoryOrderByCreatedDateDesc(Category category);
    List<LectureEntity> findByInstructorOrderByCreatedDateDesc(InstructorEntity instruction);
}
