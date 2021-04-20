package by.innowise.second.simple.filter;

import by.innowise.second.simple.security.JwtUtil;
import by.innowise.second.simple.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class CustomJwtFilter extends HttpFilter {

    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

    @SneakyThrows
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        if (request.getRequestURI().equals("/auth")) {
            super.doFilter(request, response, chain);
        } else {
            String jwtToken = extractJwtFromRequest(request);
            userService.setUser(jwtTokenUtil.getUsernameFromToken(jwtToken), "");
            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
                super.doFilter(request, response, chain);
            } else {
                throw new BadCredentialsException("Wrong token!");
            }
        }
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
