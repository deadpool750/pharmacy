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

/**
 * Service responsible for creating and validating JWT tokens.
 */
@Service
public class JwtService {

    /**
     * Duration for which the JWT token remains valid (in milliseconds).
     * Configurable via `security.token.validity` property.
     */
    @Value("${security.token.validity}")
    private long JWT_VALIDITY;

    /**
     * Secret key used for signing and verifying JWT tokens.
     * Configurable via `security.token.secret` property.
     */
    @Value("${security.token.secret}")
    private String SECRET_KEY;

    /**
     * Creates a signed JWT token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return signed JWT token string
     */
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

    /**
     * Verifies if a token is valid and not expired.
     *
     * @param token the JWT token
     * @return true if token is valid, false if expired or invalid
     */
    public boolean verifyToken(String token) {
        return !isTokenExpired(token);
    }

    /**
     * Checks if the token has expired.
     *
     * @param token the JWT token
     * @return true if expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    /**
     * Extracts the username (subject) from the token.
     *
     * @param token the JWT token
     * @return username stored in token
     */
    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the user role from the token.
     *
     * @param token the JWT token
     * @return user role string
     */
    public String getUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    /**
     * Extracts the expiration date of the token.
     *
     * @param token the JWT token
     * @return expiration date
     */
    public Date getExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Parses the JWT token and returns all claims.
     *
     * @param token the JWT token
     * @return JWT claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts a specific claim using a resolver function.
     *
     * @param token          the JWT token
     * @param claimsResolver function to extract the desired claim
     * @param <T>            type of the returned claim
     * @return extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a secret key for HMAC signing based on the configured secret.
     *
     * @return secret key
     */
    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
