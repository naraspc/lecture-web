package com.example.thirdproject.dto;

import lombok.Getter;

@Getter
public class InstructorDto {

    @Getter
    public static class InstructorSaveRequest {
        private String name;
        private String experience;
        private String company;
        private String phoneNumber;
        private String introduce;
    }

    @Getter
    public static class InstructorUpdateRequest {
        private String experience;
        private String company;
        private String phoneNumber;
        private String introduce;
    }
    @Getter
    public static class InstructorResponse {
        private Long id;
        private String name;
        private String experience;
        private String company;
        private String phoneNumber;
        private String introduce;

    }

}
