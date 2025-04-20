package com.example.pharmacy.service;

import com.example.pharmacy.infrastructure.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.token.validity}")
    private long JWT_VALIDITY;

    @Value("${security.token.secret}")
    private String SECRET_KEY;

    public String createToken(UserEntity user) {
        long now = System.currentTimeMillis();
        var token = Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId())
                .issuedAt(new Date(now))
                .expiration(new Date(now + JWT_VALIDITY))
                .signWith(generateKey())
                .compact();

        return token;
    }

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
