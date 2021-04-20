package by.innowise.second.simple.filter;

import by.innowise.second.simple.security.JwtUtil;
import by.innowise.second.simple.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractJwtFromRequest(request);
        userService.setUser(jwtTokenUtil.getUsernameFromToken(jwtToken), "");
        if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
            super.doFilter(request, response, filterChain);
        } else {
            throw new BadCredentialsException("Wrong token!");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return "/auth".equals(request.getRequestURI());
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
