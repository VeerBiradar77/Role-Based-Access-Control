package com.demo.rbac.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	// Secret used for signing the JWT
    private static final String SECRET = "5s7zA!hH3jE9fR2kL6uP0wX4yB8mN1qV";
    // Create the SecretKey instance from the raw secret bytes
    private final SecretKey SECRET_KEY = new SecretKeySpec(
        SECRET.getBytes(StandardCharsets.UTF_8), // Convert secret string to bytes
        SignatureAlgorithm.HS256.getJcaName() // Algorithm name for the key
    );

    // generetes a token for a given username
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(SECRET_KEY)
            .compact();
    }

    public String extractUserName(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

}