package com.example.thirdproject.dto;

import lombok.Getter;

public class InstructorServiceDto {
    @Getter
    public static class SaveInstructorRequest {
        private String name;
        private String experience;
        private String company;
        private String phoneNumber;
        private String introduce;


    }

    @Getter
    public static class SaveInstructorResponse {
        private Long id;
        private String name;
        private String experience;
        private String company;
        private String phoneNumber;
        private String introduce;

        public SaveInstructorResponse(Long id, String name, String experience, String company, String phoneNumber, String introduce) {
        }


    }


}
