package com.example.jwtvalidation.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;

@RestController
public class JwtTestController {

    private final SecretKey secretKey;

    public JwtTestController(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @GetMapping("/api/jwt/test-token")
    public Map<String, String> generateTestToken() {

        String token = Jwts.builder()
                .subject("chaitanya")
                .claim("roles", List.of("ROLE_USER"))
                .claim("name", "chaitanya")
                .claim("soa", "soa")
                .issuedAt(new Date())
                // Token expired one minute ago
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey)
                .compact();

        return Map.of("token", token);
    }
}