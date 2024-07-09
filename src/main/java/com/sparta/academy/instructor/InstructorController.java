package com.sparta.academy.instructor;


import com.sparta.academy.course.CourseRegisterRequestDto;
import com.sparta.academy.course.CourseResponseDto;
import com.sparta.academy.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    /*
        token에 대한 검사는 filter에서 처리.
     */

    @PostMapping("/register")
    public ResponseEntity<String> register(InstructorRegisterRequestDto instructorRegisterRequestDto) {
        return instructorService.register(instructorRegisterRequestDto);
    }

    @PostMapping("/admin/update")
    public ResponseEntity<InstructorResponseDto> update(InstructorUpdateRequestDto instructorUpdateRequestDto) {
        return instructorService.update(instructorUpdateRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructor(@PathVariable Long id) {
        return instructorService.getInstructor(id);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByInstructor(@PathVariable Long instructorId) {
        return instructorService.getCoursesByInstructor(instructorId);
    }
}
