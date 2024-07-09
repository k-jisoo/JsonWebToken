package com.sparta.academy.instructor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int experienceYears;

    private String company;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String introduction;

    public Instructor(InstructorRegisterRequestDto instructorRegisterRequestDto) {
        this.name = instructorRegisterRequestDto.getName();
        this.experienceYears = instructorRegisterRequestDto.getExperienceYears();
        this.company = instructorRegisterRequestDto.getCompany();
        this.phone = instructorRegisterRequestDto.getPhone();
        this.introduction = instructorRegisterRequestDto.getIntroduction();
    }

    public void update(String name, String company, String phone, int experienceYears, String introduction) {
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.experienceYears = experienceYears;
        this.introduction = introduction;
    }
}
