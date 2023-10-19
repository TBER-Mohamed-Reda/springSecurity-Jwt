package dev.tber.security.jwt.services.Impl;

import dev.tber.security.jwt.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String KEY_SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    @Override
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    @Override
    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
