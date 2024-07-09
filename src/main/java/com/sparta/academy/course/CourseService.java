package com.sparta.academy.course;

import com.sparta.academy.jwt.JwtUtil;
import com.sparta.academy.utils.AcademyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private final JwtUtil jwtUtil;

    public ResponseEntity<CourseResponseDto> createCourse(CourseRegisterRequestDto courseRegisterRequestDto) {
        Course course = new Course(courseRegisterRequestDto);
        try {
            courseRepository.save(course);
            return new ResponseEntity<>(new CourseResponseDto(course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<CourseResponseDto> updateCourse(Long id, CourseUpdateRequestDto courseUpdateRequestDto) {

        if (!AcademyUtil.bCanChangeLongToInteger(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Course course = courseRepository.findById(id.intValue()).orElse(null);

        if (course == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        course.update(courseUpdateRequestDto);

        return new ResponseEntity<>(new CourseResponseDto(course), HttpStatus.OK);
    }

    public ResponseEntity<CourseResponseDto> getCourse(Long id) {
        if (!AcademyUtil.bCanChangeLongToInteger(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Course course = courseRepository.findById(id.intValue()).orElse(null);

        if (course == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(new CourseResponseDto(course), HttpStatus.OK);
    }


    public ResponseEntity<List<CourseResponseDto>> getCourse(Course.Category category) {
        List<CourseResponseDto> courses =
                courseRepository.findAllByCategory(category).stream().map(CourseResponseDto::new).
                        sorted(Comparator.comparing(CourseResponseDto::getRegistrationDate).reversed()).collect(Collectors.toList());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
