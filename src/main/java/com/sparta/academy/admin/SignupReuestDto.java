package com.sparta.academy.admin;

import com.sparta.academy.enumerator.DepartmentEnum;
import com.sparta.academy.enumerator.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupReuestDto {
    private String email;
    private String password;
    private boolean manager;
    private Admin.Department department;

    public boolean getManager() {
        return manager;
    }
}
