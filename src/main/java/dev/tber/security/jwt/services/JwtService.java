package dev.tber.security.jwt.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    Claims extractAllClaims(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String extractUsername(String token);
    Date extractExpiration(String token);
    Boolean isTokenExpired(String token);
    Boolean isTokenValid(String token, UserDetails userDetails);
    Key getSignKey();
    String generateToken(UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
}
