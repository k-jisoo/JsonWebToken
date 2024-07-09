package com.sparta.academy.course;

import com.sparta.academy.instructor.Instructor;
import lombok.Getter;

import java.util.Date;

@Getter
public class CourseRegisterRequestDto {
    private String courseName;
    private int price;
    private String description;
    private Course.Category category;
    private Instructor instructor;
    private Date registrationDate;
}
