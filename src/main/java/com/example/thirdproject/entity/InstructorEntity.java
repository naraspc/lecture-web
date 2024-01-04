package com.example.thirdproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class InstructorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String experience;

    @Column
    private String company;

    @Column
    private String phoneNumber;

    @Column
    private String introduce;


    @Builder
    public InstructorEntity(String name, String experience, String company, String phoneNumber, String introduce) {
        this.name = name;
        this.experience = experience;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduce = introduce;
    }
    public void updateInstructor(String experience, String company, String phoneNumber, String introduce) {
        this.experience = experience;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduce = introduce;
    }
}
