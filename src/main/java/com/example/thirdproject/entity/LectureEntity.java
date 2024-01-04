package com.example.thirdproject.entity;

import com.example.thirdproject.constant.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private double price;

    @Column
    private String introduce;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private InstructorEntity instructor;

    @Builder
    public LectureEntity(Long id, String title, double price, String introduce, Category category, InstructorEntity instructor) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.introduce = introduce;
        this.category = category;
        this.instructor = instructor;
    }

    public void updateLecture(String title, double price, String introduce, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.introduce = introduce;
    }
}
