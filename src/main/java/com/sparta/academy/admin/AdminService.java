package com.sparta.academy.admin;

import com.sparta.academy.enumerator.UserRoleEnum;
import com.sparta.academy.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> signup(SignupReuestDto adminReuestDto) {
        String email = adminReuestDto.getEmail();
        String password = adminReuestDto.getPassword();
        boolean manager = adminReuestDto.getManager();
        Admin.Department department = adminReuestDto.getDepartment();

        if (!email.matches( "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            return new ResponseEntity<>("Email error", HttpStatus.BAD_REQUEST);

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,15}$"))
            return new ResponseEntity<>("Password error", HttpStatus.BAD_REQUEST);

        if (!(manager == (department == Admin.Department.CURRICULUM || department == Admin.Department.DEVELOPMENT)))
            return new ResponseEntity<>("Department error", HttpStatus.BAD_REQUEST);

        String encodedPassword = passwordEncoder.encode(adminReuestDto.getPassword());
        Admin admin = new Admin(adminReuestDto, encodedPassword);

        try {
            adminRepository.save(admin);
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Database error", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Admin foundAdmin = adminRepository.findByEmail(loginRequestDto.getEmail()).orElse(null);

        if (foundAdmin == null) {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), foundAdmin.getPassword())) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
        }

        String token = jwtUtil.createToken(foundAdmin.getEmail(), UserRoleEnum.MANAGER);
        response.addHeader(HttpHeaders.AUTHORIZATION, JwtUtil.BEARER_PREFIX + token);

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }
}
