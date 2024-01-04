package com.example.thirdproject.service;


import com.example.thirdproject.dto.InstructorDto;
import com.example.thirdproject.dto.InstructorServiceDto;
import com.example.thirdproject.entity.InstructorEntity;
import com.example.thirdproject.exception.ServiceException;
import com.example.thirdproject.jwt.JwtUtil;
import com.example.thirdproject.mapper.InstructorMapper;
import com.example.thirdproject.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final JwtUtil jwtUtil;
    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository, JwtUtil jwtUtil, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.jwtUtil = jwtUtil;
        this.instructorMapper = instructorMapper;
    }


    // 강사 등록
    @Transactional
    public InstructorDto saveInstructor(InstructorServiceDto.SaveInstructorRequest instructorRequest, String token) {
        isAdmin(token); // 관리자 권한 확인용 (시큐리티 적용 전)

        InstructorEntity savedInstructor =
                instructorRepository.save(InstructorEntity.builder()
                        .name(instructorRequest.getName())
                        .experience(instructorRequest.getExperience())
                        .company(instructorRequest.getCompany())
                        .phoneNumber(instructorRequest.getPhoneNumber())
                        .introduce(instructorRequest.getIntroduce())
                        .build());



        return instructorMapper.mapToInstructorDto(instructorMapper.mapToSaveInstructorResponse(savedInstructor));

    }

    //강사 정보 업데이트
    @Transactional
    public InstructorDto updateInstructor(InstructorServiceDto.SaveInstructorRequest request, Long instructorId, String token) {
        validateManagerPermission(token); // manager 권한 확인용 (시큐리티 적용 전)

        InstructorEntity instructorEntity = instructorRepository.findById(instructorId).orElseThrow(() -> new ServiceException.EntityNotFoundException("Instructor not found"));

        instructorEntity.updateInstructor(request.getExperience(), request.getCompany(), request.getPhoneNumber(), request.getIntroduce());

        InstructorEntity updatedInstructor = instructorRepository.save(instructorEntity);
        return instructorMapper.mapToInstructorDto(instructorMapper.mapToSaveInstructorResponse(updatedInstructor));
    }

    //강사 조회
    public InstructorDto findInstructor(Long instructorId, String token) {
        isAdmin(token); // 관리자 권한 확인용 (시큐리티 적용 전)

        InstructorEntity instructorEntity = instructorRepository.findById(instructorId).orElseThrow(() -> new ServiceException.EntityNotFoundException("Instructor not found"));

        return instructorMapper.mapToInstructorDto(instructorMapper.mapToSaveInstructorResponse(instructorEntity));
    }


    // 매니저 권한 확인
    private void validateManagerPermission(String token) {
        if (!jwtUtil.isManager(token)) {
            throw new ServiceException.UnauthorizedException("You don't have Permission ! ");
        }
    }
    private void isAdmin(String token) {
        if (!jwtUtil.isAdmin(token)) {
            throw new ServiceException.UnauthorizedException("You don't have Permission ! ");
        }
    }
}
