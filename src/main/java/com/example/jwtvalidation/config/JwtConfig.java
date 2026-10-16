package com.example.jwtvalidation.config;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;

@Configuration
public class JwtConfig {

    @Bean
    public SecretKey jwtSecretKey() {
        return Jwts.SIG.HS256.key().build();
    }
}