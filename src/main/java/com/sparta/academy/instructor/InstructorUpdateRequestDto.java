package com.sparta.academy.instructor;

import lombok.Getter;

@Getter
public class InstructorUpdateRequestDto {
    private Long id;
    private String name;
    private int experienceYears;
    private String company;
    private String phone;
    private String introduction;
}
