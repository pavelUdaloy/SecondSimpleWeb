package by.innowise.second.simple.filter;

import by.innowise.second.simple.controller.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class CustomJwtFilter extends HttpFilter {

    private final JwtUtil jwtTokenUtil;

    @SneakyThrows
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        if (request.getRequestURI().equals("/auth")) {
            super.doFilter(request, response, chain);
        } else {
            String jwtToken = extractJwtFromRequest(request);
            UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken),
                    "", new HashSet<>());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()));
            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
                super.doFilter(request, response, chain);
            } else {
                throw new BadCredentialsException("Wrong token!");
            }
        }
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }
}
