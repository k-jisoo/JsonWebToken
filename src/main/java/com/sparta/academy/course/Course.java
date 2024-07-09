package com.sparta.academy.course;

import com.sparta.academy.instructor.Instructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private int price;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "instructorId", nullable = false)
    private Instructor instructor;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date registrationDate;

    public Course(CourseRegisterRequestDto courseRegisterRequestDto) {
        this.courseName = courseRegisterRequestDto.getCourseName();
        this.price = courseRegisterRequestDto.getPrice();
        this.description = courseRegisterRequestDto.getDescription();
        this.category = courseRegisterRequestDto.getCategory();
        this.instructor = courseRegisterRequestDto.getInstructor();
        this.registrationDate = courseRegisterRequestDto.getRegistrationDate();
    }

    public void update(CourseUpdateRequestDto courseRegisterRequestDto) {
        this.courseName = courseRegisterRequestDto.getCourseName();
        this.price = courseRegisterRequestDto.getPrice();
        this.description = courseRegisterRequestDto.getDescription();
        this.category = courseRegisterRequestDto.getCategory();

    }

    public enum Category {
        Spring, React, Node
    }
}
