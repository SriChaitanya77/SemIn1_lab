package com.example.jwtvalidation.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jwtvalidation.service.JwtService;
import com.example.jwtvalidation.service.JwtService.JwtUser;

import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(
            @RequestBody Map<String, String> request) {

        try {
            String token = request.get("token");

            if (token == null || token.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Token is required"));
            }

            JwtUser user = jwtService.validateToken(token);

            return ResponseEntity.ok(Map.of(
                    "message", "Token Valid",
                    "username", user.username(),
                    "roles", user.roles() == null
                            ? java.util.List.of()
                            : user.roles()
            ));

        } catch (JwtException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid Token"));
        }
    }
}