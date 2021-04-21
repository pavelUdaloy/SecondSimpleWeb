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
<<<<<<< HEAD:src/main/java/by/innowise/second/simple/controller/security/JwtUtil.java
    private final Duration jwtExpirationInMs;
    private final Duration refreshExpirationDateInMs;
=======
    private final Duration jwtExpiration;
    private final Duration refreshExpiration;
    private final String signatureAlgorithm;
>>>>>>> fix:src/main/java/by/innowise/second/simple/security/JwtUtil.java

    public JwtUtil(JwtConstsProperties jwtConstsProperties) {
        secret = jwtConstsProperties.getSecret();
        jwtExpiration = jwtConstsProperties.getAccessExpirationDateInMs();
        refreshExpiration = jwtConstsProperties.getRefreshExpirationDateInMs();
        signatureAlgorithm = jwtConstsProperties.getSignatureAlgorithm();
    }

    public String generateAccessToken(String username) {
<<<<<<< HEAD:src/main/java/by/innowise/second/simple/controller/security/JwtUtil.java
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs.toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs.toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
=======
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
>>>>>>> fix:src/main/java/by/innowise/second/simple/security/JwtUtil.java
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
