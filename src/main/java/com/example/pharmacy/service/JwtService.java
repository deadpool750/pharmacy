package com.example.pharmacy.service;

import com.example.pharmacy.infrastructure.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.token.validity}")
    private long JWT_VALIDITY;

    @Value("${security.token.secret}")
    private String SECRET_KEY;

    public String createToken(UserEntity user) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .issuedAt(new Date(now))
                .expiration(new Date(now + JWT_VALIDITY))
                .signWith(generateKey())
                .compact();
    }

    public boolean verifyToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Date getExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
