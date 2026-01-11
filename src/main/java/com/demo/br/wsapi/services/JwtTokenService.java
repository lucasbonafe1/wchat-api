package com.demo.br.wsapi.services;

import com.demo.br.wsapi.models.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private final Key secretKey;

    @Value("${jwt.expiration}")
    private final long expirationMillis;

    public JwtTokenService(String secret, long expirationMillis) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .addClaims(Map.of(
                        "userId", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail()
                ))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
