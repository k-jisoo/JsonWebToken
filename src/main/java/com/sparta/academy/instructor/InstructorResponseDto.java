package com.sparta.academy.instructor;

import com.sparta.academy.course.Course;

public class InstructorResponseDto {
    private String name;
    private int experienceYears;
    private String company;
    private String phone;
    private String introduction;

    public InstructorResponseDto(Instructor instructor) {
        this.name = instructor.getName();
        this.experienceYears = instructor.getExperienceYears();
        this.company = instructor.getCompany();
        this.phone = instructor.getPhone();
        this.introduction = instructor.getIntroduction();
    }
}
