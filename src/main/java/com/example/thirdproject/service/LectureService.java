// LectureService.java
package com.example.thirdproject.service;

import com.example.thirdproject.constant.Category;
import com.example.thirdproject.dto.LectureServiceDto;
import com.example.thirdproject.entity.InstructorEntity;
import com.example.thirdproject.entity.LectureEntity;
import com.example.thirdproject.exception.ServiceException;
import com.example.thirdproject.jwt.JwtUtil;
import com.example.thirdproject.mapper.LectureMapper;
import com.example.thirdproject.repository.InstructorRepository;
import com.example.thirdproject.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;
    private final JwtUtil jwtUtil;
    private final LectureMapper lectureMapper;

    public LectureService(LectureRepository lectureRepository, InstructorRepository instructorRepository, JwtUtil jwtUtil, LectureMapper lectureMapper) {
        this.lectureRepository = lectureRepository;
        this.instructorRepository = instructorRepository;
        this.jwtUtil = jwtUtil;
        this.lectureMapper = lectureMapper;
    }
    // 강의저장
    @Transactional
    public LectureServiceDto.LectureSaveResponse saveLecture(LectureServiceDto.LectureSaveRequest request, String token) {
        validateManagerPermission(token);

        InstructorEntity instructorEntity = instructorRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new ServiceException.EntityNotFoundException("Instructor not found"));

        LectureEntity savedLecture = lectureRepository.save(LectureEntity.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .introduce(request.getIntroduce())
                .category(request.getCategory())
                .instructor(instructorEntity)
                .build());

        return lectureMapper.mapToSaveResponse(savedLecture);
    }

    //강의 수정
    @Transactional
    public LectureServiceDto.LectureUpdateResponse updateLecture(
            LectureServiceDto.LectureSaveRequest updateRequest, Long lectureId, String token) {

        // 로그인한 사용자의 권한 확인
        validateManagerPermission(token);

        // 강의 엔티티 조회
        LectureEntity lectureEntity = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ServiceException.EntityNotFoundException("Lecture not found"));

        // 강의 정보 업데이트
        lectureEntity.updateLecture(updateRequest.getTitle(), updateRequest.getPrice(),
                updateRequest.getIntroduce(), updateRequest.getCategory());

        // 수정된 강의 엔티티 저장
        LectureEntity updatedLecture = lectureRepository.save(lectureEntity);

        // 엔티티를 DTO로 매핑하여 반환
        return lectureMapper.mapToUpdateResponse(updatedLecture);
    }


    // 강의 단건조회
    @Transactional
    public LectureServiceDto.LectureSaveResponse findLectureById(Long lectureId, String token) {
        validateAdminPermission(token);

        LectureEntity lectureEntity = lectureRepository.findById(lectureId).orElseThrow(() -> new ServiceException.EntityNotFoundException("Lecture not found"));
        return lectureMapper.mapToSaveResponse(lectureEntity);
    }

    // 강사가 촬영한 강의목록 조회
    public List<LectureServiceDto.LectureSaveResponse> getLecturesByInstructor(Long instructorId, String token) {
        validateManagerPermission(token);

        // 해당 강사가 촬영한 강의 목록을 조회하고 등록일 기준으로 내림차순으로 정렬
        List<LectureEntity> lectures = lectureRepository.findByInstructorOrderByCreatedDateDesc(new InstructorEntity(instructorId));

        // 강의 목록을 DTO로 매핑
        return lectures.stream()
                .map(lectureMapper::mapToSaveResponse)
                .collect(Collectors.toList());
    }

    // 카테고리별 강의 목록 조회
    public List<LectureServiceDto.LectureSaveResponse> getLecturesByCategory(Category category, String token) {
        validateManagerPermission(token);

        // 선택한 카테고리에 포함된 강의 목록을 조회하고 등록일 기준으로 내림차순으로 정렬
        List<LectureEntity> lectures = lectureRepository.findByCategoryOrderByCreatedDateDesc(category);

        // 강의 목록을 DTO로 매핑
        return lectures.stream()
                .map(lectureMapper::mapToSaveResponse)
                .collect(Collectors.toList());
    }


    private void validateManagerPermission(String token) {
        if (!jwtUtil.isManager(token)) {
            throw new ServiceException.UnauthorizedException("Access denied You don't have permission to access");
        }
    }
    private void validateAdminPermission(String token) {
        if (!jwtUtil.isAdmin(token)) {
            throw new ServiceException.UnauthorizedException("Access denied You don't have permission to access");
        }
    }
}
