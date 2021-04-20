package by.innowise.second.simple.security;

import by.innowise.second.simple.properties.JwtConstsProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret;
    private final Integer jwtExpirationInMs;
    private final Integer refreshExpirationDateInMs;

    public JwtUtil(JwtConstsProperties jwtConstsProperties) {
        secret = jwtConstsProperties.getSecret();
        jwtExpirationInMs = jwtConstsProperties.getAccessExpirationDateInMs();
        refreshExpirationDateInMs = jwtConstsProperties.getRefreshExpirationDateInMs();
    }

    public String generateAccessToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String authToken) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
        return true;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}