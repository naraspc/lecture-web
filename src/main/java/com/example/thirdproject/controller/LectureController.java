package com.example.thirdproject.controller;

import com.example.thirdproject.constant.Category;
import com.example.thirdproject.dto.LectureServiceDto;
import com.example.thirdproject.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<LectureServiceDto.LectureSaveResponse> saveLecture(
            @RequestBody @Valid LectureServiceDto.LectureSaveRequest request,
            @RequestHeader("Authorization") String token) {

        LectureServiceDto.LectureSaveResponse savedLecture = lectureService.saveLecture(request, token);

        return new ResponseEntity<>(savedLecture, HttpStatus.OK);
    }

    // 강의 수정
    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureServiceDto.LectureUpdateResponse> updateLecture(
            @RequestBody LectureServiceDto.LectureSaveRequest updateRequest,
            @PathVariable Long lectureId,
            @RequestHeader("Authorization") String token) {

        LectureServiceDto.LectureUpdateResponse updatedLecture = lectureService.updateLecture(updateRequest, lectureId, token);
        return ResponseEntity.ok(updatedLecture);
    }

    // 강의 단건조회
    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureServiceDto.LectureSaveResponse> findLectureById(
            @PathVariable Long lectureId,
            @RequestHeader("Authorization") String token) {

        LectureServiceDto.LectureSaveResponse lecture = lectureService.findLectureById(lectureId, token);
        return ResponseEntity.ok(lecture);
    }

    // 강사가 촬영한 강의목록 조회
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<LectureServiceDto.LectureSaveResponse>> getLecturesByInstructor(
            @PathVariable Long instructorId,
            @RequestHeader("Authorization") String token) {

        List<LectureServiceDto.LectureSaveResponse> lectures = lectureService.getLecturesByInstructor(instructorId, token);
        return ResponseEntity.ok(lectures);
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<LectureServiceDto.LectureSaveResponse>> getLecturesByCategory(
            @PathVariable Category category,
            @RequestHeader("Authorization") String token) {

        List<LectureServiceDto.LectureSaveResponse> lectures = lectureService.getLecturesByCategory(category, token);
        return ResponseEntity.ok(lectures);
    }

}