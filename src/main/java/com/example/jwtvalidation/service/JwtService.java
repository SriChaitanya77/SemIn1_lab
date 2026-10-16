package com.example.jwtvalidation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import com.example.jwtvalidation.util.JwtUtil;

@Service
public class JwtService {

    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public JwtUser validateToken(String token) {

        Claims claims = jwtUtil.validateToken(token);

        String username = claims.getSubject();

        List<String> roles = claims.get("roles", List.class);

        return new JwtUser(username, roles);
    }

    public record JwtUser(String username, List<String> roles) {
    }
}