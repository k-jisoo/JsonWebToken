package com.sparta.academy.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByInstructorId(Long instructorId);

    List<Course> findAllByCategory(Course.Category category);
}
