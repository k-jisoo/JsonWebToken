package com.sparta.academy.course;

import com.sparta.academy.instructor.Instructor;
import lombok.Getter;

import java.util.Date;

@Getter
public class CourseResponseDto {
    private String courseName;
    private int price;
    private String description;
    private Course.Category category;
    private Instructor instructor;
    private Date registrationDate;

    public CourseResponseDto(Course course) {
        this.courseName = course.getCourseName();
        this.price = course.getPrice();
        this.description = course.getDescription();
        this.category = course.getCategory();
        this.instructor = course.getInstructor();
        this.registrationDate = course.getRegistrationDate();
    }
}
