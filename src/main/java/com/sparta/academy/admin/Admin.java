package com.sparta.academy.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private boolean manager;

    public Admin(SignupReuestDto adminReuestDto, String encodedPassword) {
        this.email = adminReuestDto.getEmail();
        this.password = encodedPassword;
        this.department = adminReuestDto.getDepartment();
        this.manager = adminReuestDto.getManager();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
    }

    public enum Department {
        CURRICULUM, DEVELOPMENT, MARKETING
    }
}
