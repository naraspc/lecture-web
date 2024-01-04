package com.example.thirdproject.controller;

import com.example.thirdproject.dto.InstructorDto;
import com.example.thirdproject.mapper.InstructorMapper;
import com.example.thirdproject.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;

    public InstructorController(InstructorService instructorService, InstructorMapper instructorMapper) {
        this.instructorService = instructorService;
        this.instructorMapper = instructorMapper;
    }

    @PostMapping
    public ResponseEntity<InstructorDto> saveInstructor(
            @RequestBody InstructorDto.InstructorSaveRequest instructorSaveRequest,
            @RequestHeader("Authorization") String token) {

        InstructorDto response = instructorService.saveInstructor(instructorMapper.mapToSaveControllerDto(instructorSaveRequest), token);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<InstructorDto> updateInstructor(
            @RequestBody InstructorDto.InstructorUpdateRequest instructorUpdateRequest,
            @PathVariable Long instructorId,
            @RequestHeader("Authorization") String token) {

        InstructorDto response = instructorService.updateInstructor(instructorMapper.mapToUpdateControllerDto( instructorUpdateRequest), instructorId, token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorDto> findInstructor(
            @PathVariable Long instructorId,
            @RequestHeader("Authorization") String token) {

        InstructorDto response = instructorService.findInstructor(instructorId, token);
        return ResponseEntity.ok(response);
    }
}