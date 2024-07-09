package com.sparta.academy.instructor;

import lombok.Getter;

@Getter
public class InstructorRegisterRequestDto {
    private String name;
    private int experienceYears;
    private String company;
    private String phone;
    private String introduction;
}