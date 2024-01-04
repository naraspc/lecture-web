package com.example.thirdproject.dto;

import com.example.thirdproject.constant.Category;
import lombok.Getter;

@Getter
public class LectureDto {
    public static class lectureSaveDto {
        private String title;
        private Long price;
        private String introduce;
        private Category category;
        private String instructorName;
    }

    public static class lectureUpdateDto {
        private String title;
        private Long price;
        private String introduce;
        private Category category;
    }
}
