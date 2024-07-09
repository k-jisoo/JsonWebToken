package com.sparta.academy.instructor;

import com.sparta.academy.course.Course;
import com.sparta.academy.course.CourseRepository;
import com.sparta.academy.course.CourseResponseDto;
import com.sparta.academy.jwt.JwtUtil;
import com.sparta.academy.utils.AcademyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    private final CourseRepository courseRepository;

    private final JwtUtil jwtUtil;

    public ResponseEntity<String> register(InstructorRegisterRequestDto instructorRegisterRequestDto) {
        Instructor instructor = new Instructor(instructorRegisterRequestDto);
        try {
            instructorRepository.save(instructor);
            return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Database error", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<InstructorResponseDto> update(InstructorUpdateRequestDto instructorUpdateRequestDto) {
        Instructor instructor = instructorRepository.findById(instructorUpdateRequestDto.getId().intValue()).orElse(null);

        if (instructor == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        instructor.update(
                instructorUpdateRequestDto.getName(),
                instructorUpdateRequestDto.getCompany(),
                instructorUpdateRequestDto.getPhone(),
                instructorUpdateRequestDto.getExperienceYears(),
                instructorUpdateRequestDto.getIntroduction()
        );

        Instructor savedInstructor = instructorRepository.save(instructor);

        return new ResponseEntity<>(new InstructorResponseDto(savedInstructor), HttpStatus.OK);
    }

    public ResponseEntity<InstructorResponseDto> getInstructor(Long id) {
        if (!AcademyUtil.bCanChangeLongToInteger(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Instructor instructor = instructorRepository.findById(id.intValue()).orElse(null);

        if (instructor == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(new InstructorResponseDto(instructor), HttpStatus.OK);
    }

    public ResponseEntity<List<CourseResponseDto>> getCoursesByInstructor(Long instructorId) {
        if (!AcademyUtil.bCanChangeLongToInteger(instructorId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<CourseResponseDto> courses =
                courseRepository.findAllByInstructorId(instructorId).stream().map(CourseResponseDto::new).
                        sorted(Comparator.comparing(CourseResponseDto::getRegistrationDate)).collect(Collectors.toList());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
