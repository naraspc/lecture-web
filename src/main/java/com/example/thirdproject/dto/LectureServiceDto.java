package com.example.thirdproject.dto;

import com.example.thirdproject.constant.Category;
import lombok.Builder;
import lombok.Getter;

public class LectureServiceDto {

    @Getter
    @Builder
    public static class LectureSaveRequest {
        private String title;
        private double price;
        private String introduce;
        private Category category;
        private Long instructorId; // 강의를 촬영하는 강사의 ID
    }

    @Getter
    public static class LectureSaveResponse {
        private final Long id;
        private final String title;
        private final double price;
        private final String introduce;
        private final Category category;
        private final String instructorName;

        // 생성자
        public LectureSaveResponse(Long id, String title, double price, String introduce, Category category, String instructorName) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.introduce = introduce;
            this.category = category;
            this.instructorName = instructorName;
        }
    }


    @Getter
    public static class LectureUpdateResponse {
        private final Long id;
        private final String title;
        private final double price;
        private final String introduce;
        private final Category category;

        // 생성자
        public LectureUpdateResponse(Long id, String title, double price, String introduce, Category category) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.introduce = introduce;
            this.category = category;
        }
    }
}