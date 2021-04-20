package by.innowise.second.simple.security;

import by.innowise.second.simple.properties.JwtConstsProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret;
    private final Duration jwtExpiration;
    private final Duration refreshExpiration;
    private final String signatureAlgorithm;

    public JwtUtil(JwtConstsProperties jwtConstsProperties) {
        secret = jwtConstsProperties.getSecret();
        jwtExpiration = jwtConstsProperties.getAccessExpirationDateInMs();
        refreshExpiration = jwtConstsProperties.getRefreshExpirationDateInMs();
        signatureAlgorithm = jwtConstsProperties.getSignatureAlgorithm();
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration.toMillis()))
                .signWith(SignatureAlgorithm.forName(signatureAlgorithm), secret).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration.toMillis()))
                .signWith(SignatureAlgorithm.forName(signatureAlgorithm), secret).compact();
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
