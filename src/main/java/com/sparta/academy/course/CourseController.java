package com.sparta.academy.course;

import com.sparta.academy.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDto> createCourse(CourseRegisterRequestDto courseRegisterRequestDto) {
        return courseService.createCourse(courseRegisterRequestDto);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, CourseUpdateRequestDto courseUpdateRequestDto) {
        return courseService.updateCourse(id, courseUpdateRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategory(@RequestParam Course.Category category) {
        return courseService.getCourse(category);
    }
}
