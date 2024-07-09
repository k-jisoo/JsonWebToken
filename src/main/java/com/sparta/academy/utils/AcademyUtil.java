package com.sparta.academy.utils;

import com.sparta.academy.enumerator.UserRoleEnum;
import com.sparta.academy.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AcademyUtil {

    public static boolean isManager(String tokenValue, JwtUtil jwtUtil){
        String token = jwtUtil.substringToken(tokenValue);

        if (!jwtUtil.validateToken(token))
            return false;

        Claims claims = jwtUtil.getUserInfoFromToken(token);
        return claims.get(JwtUtil.AUTHORIZATION_KEY).equals(UserRoleEnum.MANAGER.getAuthority());
    }

    public static boolean hasAuthority(String tokenValue, JwtUtil jwtUtil){
        String token = jwtUtil.substringToken(tokenValue);

        if (!jwtUtil.validateToken(token)) {
            return false;
        }

        Claims claims = jwtUtil.getUserInfoFromToken(token);
        return claims.get(JwtUtil.AUTHORIZATION_KEY).equals(UserRoleEnum.MANAGER.getAuthority()) || claims.get(JwtUtil.AUTHORIZATION_KEY).equals(UserRoleEnum.STAFF.getAuthority());
    }

    public static boolean bCanChangeLongToInteger(Long value){
        if(value > Integer.MAX_VALUE || value < Integer.MIN_VALUE)
            return false;

        return true;
    }
}
