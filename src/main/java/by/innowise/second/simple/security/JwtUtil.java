package by.innowise.second.simple.security;

import by.innowise.second.simple.properties.JwtConstsProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final static String ROLES = "roles";

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

    public String generateAccessToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLES, roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration.toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
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

    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> rolesBody = (List<String>) claims.get(ROLES);
        if (rolesBody != null && rolesBody.size() != 0) {
            roles = rolesBody.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return roles;
    }
}
