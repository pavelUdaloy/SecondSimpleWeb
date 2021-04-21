package by.innowise.second.simple.filter;

import by.innowise.second.simple.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtTokenUtil;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractJwtFromRequest(request);
        if (jwtToken == null || jwtToken.isEmpty() || !jwtTokenUtil.validateToken(jwtToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken),
                "", new HashSet<>());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEARER.length());
        } else {
            return null;
        }
    }
}
